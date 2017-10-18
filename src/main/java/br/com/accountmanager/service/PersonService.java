package br.com.accountmanager.service;

import br.com.accountmanager.entity.PersonEntity;
import br.com.accountmanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonEntity> getAll() {
        return personRepository.findAll();
    }

}
