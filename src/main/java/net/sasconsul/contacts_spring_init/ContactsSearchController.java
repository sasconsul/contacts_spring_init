package net.sasconsul.contacts_spring_init;

/**
 * Created by sasconsul on 7/25/16.
 */

import net.sasconsul.contacts_spring_init.respository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactsSearchController {
    private final ContactRepository repository;

    @Autowired
    public ContactsSearchController(ContactRepository repository) {
        this.repository = repository;
    }

//    @RequestMapping(findByEmployedFalse="/findAll", method = RequestMethod.GET)
//    public Iterable findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
//                            @RequestParam(value = "count", defaultValue = "5", required = false) int count,
//                            @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
//                            @RequestParam(value = "sort", defaultValue = "employed", required = false) String sortProperty) {
//        Page result = repository.findAll(new PageRequest(page, count, new Sort(direction, sortProperty)));
//        return result.getContent();
//    }


    //FIXME contact objects don't have REST metadata and are not pretty-printed.
    @RequestMapping(path="/api/search/contacts/findByEmployedFalse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable findByEmployedFalse(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                            @RequestParam(value = "count", defaultValue = "5", required = false) int count,
                            @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
                            @RequestParam(value = "sort", defaultValue = "employed", required = false) String sortProperty) {
        Page result = repository.findByEmployedFalse(new PageRequest(page, count, new Sort(direction, sortProperty)));
        return result.getContent();
    }


}
