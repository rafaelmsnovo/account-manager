package br.com.accountmanager.model;

import br.com.accountmanager.entity.PersonEntity;

public class PersonRequest {

    private PersonEntity person;

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
