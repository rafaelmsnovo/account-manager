package br.com.accountmanager.service;

import br.com.accountmanager.entity.AccountEntity;
import br.com.accountmanager.entity.PersonEntity;
import br.com.accountmanager.model.AccountResponse;
import br.com.accountmanager.repository.AccountRepository;
import br.com.accountmanager.util.AccountStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountResponse> getAll() {
        List<AccountResponse> responseList = new ArrayList<AccountResponse>();
        List<AccountEntity> accounts = accountRepository.findAll();
        for (AccountEntity account : accounts) {
            responseList.add(parseAccountRespose(account));
        }
        return responseList;
    }

    public AccountResponse get(Long id) {
        AccountResponse accountResponse = parseAccountRespose(accountRepository.getOne(id));
        return accountResponse;
    }

    public void remove(Long personId) {
        accountRepository.delete(personId);
    }

    public void save(AccountResponse accountResponse) {
        accountResponse.setCreateDate(new Date());
        accountResponse.setBalance(0.0);
        accountRepository.save(parseAccountEntity(accountResponse));
    }

    public void update(AccountResponse accountResponse) {
        if (accountResponse.getId() != null) {
            accountRepository.save(parseAccountEntity(accountResponse));
        }
    }

    private AccountResponse parseAccountRespose(AccountEntity a) {
        return new AccountResponse(
            a.getId(),
            a.getAccountName(),
            a.getCreateDate(),
            a.getStatus().getValue(),
            a.getBalance(),
            a.getPerson() == null ? null : a.getPerson().getId(),
            a.getAccountParent() == null ? null : a.getAccountParent().getId());
    }

    private AccountEntity parseAccountEntity(AccountResponse a) {
        PersonEntity person = null;
        AccountEntity parent = null;
        if (a.getPersonId() != null) {
            person = new PersonEntity();
            person.setId(a.getPersonId());
        }
        if (a.getAccountParentId() != null) {
            parent = new AccountEntity();
            parent.setId(a.getAccountParentId());
        }

        return new AccountEntity(
            a.getId(),
            a.getAccountName(),
            a.getCreateDate(),
            AccountStatusEnum.fromString(a.getStatus()),
            a.getBalance(),
            person,
            parent);
    }

}
