package net.sasconsul.contacts_spring_init;

import net.sasconsul.contacts_spring_init.domain.Contact;
import net.sasconsul.contacts_spring_init.respository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by sasconsul on 7/23/16.
 */

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ContactRepository repository;

    @Autowired
    public DatabaseLoader(ContactRepository pRepository) {
        this.repository = pRepository;
    }

    @Override
    public void run(String... pStrings) throws Exception {
        this.repository.save(new Contact("John Doe", "jDoe@example.com", "Gentleman", true));
        this.repository.save(new Contact("Penny Hofstadter", "pHofstadter@example.com", "Waitress", true));
        this.repository.save(new Contact("Peter Parker", "pParker@example.com", "Photographer", false));
        this.repository.save(new Contact("Leonard Leakey Hofstadter", "lHofstadter@example.com", "Experimental Physicist", true));
        this.repository.save(new Contact("Sheldon Lee Cooper", "sCooper@example.com", "Theoretical Physicist", true));
        this.repository.save(new Contact("Marvin Mutant", "mMutant@example.com", "Theoretical Being", false));
        this.repository.save(new Contact("Able Atom", "aAtom@example.com", "Matter building block", false));
        this.repository.save(new Contact("Silver Surfer", "sSurfer@example.com", "Post human", false));
        this.repository.save(new Contact("Howard Humble", "hHumble@example.com", "Engineer", true));
        this.repository.save(new Contact("Mother Humble", "mHumble@example.com", "Mother", false));
    }
}

