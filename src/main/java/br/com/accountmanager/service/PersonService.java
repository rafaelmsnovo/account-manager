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

    public PersonEntity get(Long id) {
        return personRepository.getOne(id);
    }

    public void remove(Long personId) {
        personRepository.delete(personId);
    }

    public void save(PersonEntity personEntity)
        throws Exception {

        if ((personEntity.getCpf() != null && !personEntity.getCpf().isEmpty()) &&
            (personEntity.getFullName() != null && !personEntity.getFullName().isEmpty()) &&
            (personEntity.getBirthDate() != null)) {
            personEntity.setCnpj(null);
            personEntity.setFantasyName(null);
            personEntity.setCompanyName(null);
        } else {
            if ((personEntity.getCnpj() != null && !personEntity.getCnpj().isEmpty()) &&
                (personEntity.getFantasyName() != null && !personEntity.getFantasyName().isEmpty()) &&
                (personEntity.getCompanyName() != null && !personEntity.getCompanyName().isEmpty())) {
                personEntity.setCpf(null);
                personEntity.setFullName(null);
                personEntity.setBirthDate(null);
            } else {
               throw new Exception("Invalid Paramters");
            }
        }
        personRepository.save(personEntity);
    }

    public void update(PersonEntity personEntity) {
        if (personEntity.getId() != null) {
            personRepository.save(personEntity);
        }
    }

}
