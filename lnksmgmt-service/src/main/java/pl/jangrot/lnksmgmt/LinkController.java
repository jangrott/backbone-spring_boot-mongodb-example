package pl.jangrot.lnksmgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LinkController {

    @Autowired
    private LinkRepository repository;

    @RequestMapping(value = "links", method = RequestMethod.GET)
    public List<Link> getLinks() {
        return repository.findAll();
    }

    @RequestMapping(value = "links/{linkId}", method = RequestMethod.GET)
    public Link getLink(@PathVariable("linkId") String linkId) {
        return repository.findOne(linkId);
    }
}
