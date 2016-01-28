package pl.jangrot.lnksmgmt;

import com.github.fakemongo.junit.FongoRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static pl.jangrot.lnksmgmt.TestUtils.randomStringUUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestApplicationConfig.class})
@WebAppConfiguration
public class LinkRepositoryTest {

    @Autowired
    private LinkRepository repository;

    @Rule
    public FongoRule fongoRule = new FongoRule();

    private List<Link> links;

    @Before
    public void setUp() {
        links = new ArrayList<>();

        links.add(repository.save(new Link(randomStringUUID(), "http://siteone.pl", false)));
        links.add(repository.save(new Link(randomStringUUID(), "http://sitetwo.pl", false)));
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void findsLinks() {
        List<Link> links = repository.findAll();

        assertThat(links, hasSize(2));

    }

    @Test
    public void findsLinkById() {
        Link siteone = links.get(0);

        Link link = repository.findOne(siteone.getId());

        assertThat(link, is(siteone));
    }

    @Test
    public void savesLink() {
        Link siteThree = new Link(randomStringUUID(), "http://sitethree.pl", false);

        repository.save(siteThree);

        Link link = repository.findOne(siteThree.getId());

        assertThat(link, is(siteThree));
    }

    @Test
    public void deletesLinkById() {
        Link siteone = links.get(0);

        repository.delete(siteone.getId());

        Link link = repository.findOne(siteone.getId());

        assertThat(link, is(nullValue()));
    }

    @Test
    public void deletesLink() {
        Link sitetwo = links.get(1);

        repository.delete(sitetwo);

        Link link = repository.findOne(sitetwo.getId());

        assertThat(link, is(nullValue()));
    }

    @Test
    public void deletesLinks() {
        repository.deleteAll();

        List<Link> links = repository.findAll();

        assertThat(links, hasSize(0));
    }

}
