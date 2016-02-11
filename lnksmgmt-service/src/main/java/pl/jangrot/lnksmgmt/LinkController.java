package pl.jangrot.lnksmgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @RequestMapping(value = "links", method = RequestMethod.POST)
    public ResponseEntity<Void> createLink(@RequestBody Link link) {
        if (link.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new link cannot have an ID").build();
        }

        if (link == null || link.getUrl().isEmpty()) {
            return ResponseEntity.badRequest().header("Failure", "A new link cannot have an empty URL").build();
        }

        Link added = repository.save(link);

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(added.getId()).toUri()).build();
    }
}
