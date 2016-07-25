package net.sasconsul.contacts_spring_init.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sasconsul on 7/23/16.
 */

@Data
@Entity
public class Contact {
    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String profession;
    private Boolean employed;

    private Contact() {}

    public Contact(String pName, String pEmail, String pProfession, Boolean pEmployed) {
        this.name = pName;
        this.email = pEmail;
        this.profession = pProfession;
        this.employed = pEmployed;
    }

}
