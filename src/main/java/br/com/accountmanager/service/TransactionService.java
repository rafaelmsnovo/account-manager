package br.com.accountmanager.service;

import br.com.accountmanager.entity.TransactionEntity;
import br.com.accountmanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Set<TransactionEntity> getAll() {
        return new HashSet<TransactionEntity>((Collection<? extends TransactionEntity>) transactionRepository.findAll());
    }
}
