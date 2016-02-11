package pl.jangrot.lnksmgmt;

import com.github.fakemongo.junit.FongoRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static pl.jangrot.lnksmgmt.TestUtils.createLink;
import static pl.jangrot.lnksmgmt.TestUtils.json;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestApplicationConfig.class})
@WebAppConfiguration
public class LinkControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private LinkRepository repository;

    @Rule
    public FongoRule fongoRule = new FongoRule();

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private List<Link> links;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(context).build();

        links = new ArrayList<>();

        links.add(repository.save(createLink("http://siteone.com", false)));
        links.add(repository.save(createLink("http://sitetwo.com", true)));
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void readsLinks() throws Exception {
        mockMvc.perform(get("/api/links"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(links.get(0).getId())))
                .andExpect(jsonPath("$[0].url", is("http://siteone.com")))
                .andExpect(jsonPath("$[0].watched", is(false)))
                .andExpect(jsonPath("$[1].id", is(links.get(1).getId())))
                .andExpect(jsonPath("$[1].url", is("http://sitetwo.com")))
                .andExpect(jsonPath("$[1].watched", is(true)));
    }

    @Test
    public void readsSingleLink() throws Exception {
        mockMvc.perform(get("/api/links/" + links.get(1).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(links.get(1).getId())))
                .andExpect(jsonPath("$.url", is("http://sitetwo.com")))
                .andExpect(jsonPath("$.watched", is(true)));
    }

    @Test
    public void createsLink() throws Exception {
        Link siteThree = new Link();
        siteThree.setUrl("http://sitethree.com");

        byte[] linkJson = json(siteThree);

        mockMvc.perform(post("/api/links")
                .contentType(contentType)
                .content(linkJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void doesNotCreateLinkWhenIDSetUp() throws Exception {
        Link linkWithId = new Link();
        linkWithId.setId(UUID.randomUUID().toString());
        linkWithId.setUrl("http://sitefour.com");

        byte[] linkJson = json(linkWithId);

        mockMvc.perform(post("/api/links")
                .contentType(contentType)
                .content(linkJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void doesNotCreateLinkWhenUrlEmpty() throws Exception {
        Link linkWithId = new Link();
        linkWithId.setId(UUID.randomUUID().toString());

        byte[] linkJson = json(linkWithId);

        mockMvc.perform(post("/api/links")
                .contentType(contentType)
                .content(linkJson))
                .andExpect(status().isBadRequest());
    }

}