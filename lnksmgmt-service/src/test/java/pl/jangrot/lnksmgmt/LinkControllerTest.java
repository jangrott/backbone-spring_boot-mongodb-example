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
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private Link siteOne, siteTwo;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(context).build();

        siteOne = repository.save(createLink("http://siteone.com", false));
        siteTwo = repository.save(createLink("http://sitetwo.com", true));
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
                .andExpect(jsonPath("$[0].id", is(siteOne.getId())))
                .andExpect(jsonPath("$[0].url", is("http://siteone.com")))
                .andExpect(jsonPath("$[0].watched", is(false)))
                .andExpect(jsonPath("$[1].id", is(siteTwo.getId())))
                .andExpect(jsonPath("$[1].url", is("http://sitetwo.com")))
                .andExpect(jsonPath("$[1].watched", is(true)));
    }

    @Test
    public void readsSingleLink() throws Exception {
        mockMvc.perform(get("/api/links/" + siteTwo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(siteTwo.getId())))
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
        Link link = new Link();
        link.setUrl("");

        byte[] linkJson = json(link);

        mockMvc.perform(post("/api/links")
                .contentType(contentType)
                .content(linkJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void doesNotCreateLinkWhenUrlNotValid() throws Exception {
        Link link = new Link();
        link.setUrl("http:not,valid.link");

        byte[] linkJson = json(link);

        mockMvc.perform(post("/api/links")
                .contentType(contentType)
                .content(linkJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletesLink() throws Exception {
        mockMvc.perform(delete("/api/links/" + siteTwo.getId())).andExpect(status().isOk());
    }

    @Test
    public void updatesLink() throws Exception {
        siteOne.setWatched(true);

        byte[] linkJson = json(siteOne);

        mockMvc.perform(put("/api/links")
                .contentType(contentType)
                .content(linkJson))
                .andExpect(status().isOk());
    }

    @Test
    public void createsLinkWhenUpdateAndIDNotSetUp() throws Exception {
        siteOne.setId(null);

        byte[] linkJson = json(siteOne);

        mockMvc.perform(put("/api/links")
                .contentType(contentType)
                .content(linkJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void doesNotUpdateLinkWhenUrlNotValid() throws Exception {
        Link link = new Link();
        link.setUrl("http:not,valid.link");

        byte[] linkJson = json(link);

        mockMvc.perform(put("/api/links")
                .contentType(contentType)
                .content(linkJson))
                .andExpect(status().isBadRequest());
    }

}