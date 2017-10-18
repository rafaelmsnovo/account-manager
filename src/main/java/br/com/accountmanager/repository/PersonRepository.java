package br.com.accountmanager.repository;

import br.com.accountmanager.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

}
