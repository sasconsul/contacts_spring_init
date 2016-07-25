package net.sasconsul.contacts_spring_init.respository;

import net.sasconsul.contacts_spring_init.domain.Contact;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by sasconsul on 7/23/16.
 */
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

// TODO: impl "Search recent 5 contacts added who are unemployed (Should have sorting and pagination support)"
    @Query("select c from Contact c where c.employed = false")
    Page searchLast5Unemployed(Pageable pageable);


    Page<Contact> findByEmployedFalse(Pageable pageable);
}
