package br.com.accountmanager.service;

import br.com.accountmanager.entity.AccountEntity;
import br.com.accountmanager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountEntity> getAll() {
        return accountRepository.findAll();
    }

}
