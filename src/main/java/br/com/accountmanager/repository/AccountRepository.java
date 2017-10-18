package br.com.accountmanager.repository;

import br.com.accountmanager.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

}
