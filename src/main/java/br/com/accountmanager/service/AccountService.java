package br.com.accountmanager.service;

import br.com.accountmanager.entity.AccountEntity;
import br.com.accountmanager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Set<AccountEntity> getAll() {
        return new HashSet<AccountEntity>((Collection<? extends AccountEntity>) accountRepository.findAll());
    }

}
