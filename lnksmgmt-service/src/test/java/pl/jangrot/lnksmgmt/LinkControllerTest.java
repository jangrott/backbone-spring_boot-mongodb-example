package pl.jangrot.lnksmgmt;

import com.github.fakemongo.junit.FongoRule;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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

    private List<Link> links = new ArrayList<>();

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(context).build();

        links.add(repository.save(new Link(randomStringUUID(), "http://siteone.com", false)));
        links.add(repository.save(new Link(randomStringUUID(), "http://sitetwo.com", true)));
    }

    @Test
    public void readLinks() throws Exception {
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

    private String randomStringUUID() {
        return UUID.randomUUID().toString();
    }

}