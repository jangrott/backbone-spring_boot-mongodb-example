package pl.jangrot.lnksmgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/link")
public class LinkController {

    @Autowired
    private LinkService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Link> getAll() {
        return service.findAll();
    }
}
