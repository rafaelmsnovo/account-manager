package br.com.accountmanager.service;

import br.com.accountmanager.entity.PersonEntity;
import br.com.accountmanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Set<PersonEntity> getAll() {
        return new HashSet<PersonEntity>((Collection<? extends PersonEntity>) personRepository.findAll());
    }

}
