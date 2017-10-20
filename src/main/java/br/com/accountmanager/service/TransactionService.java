package br.com.accountmanager.service;

import br.com.accountmanager.entity.TransactionEntity;
import br.com.accountmanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionEntity> getAll() {
        return transactionRepository.findAll();
    }

    public void remove(Long personId) {
        transactionRepository.delete(personId);
    }

    public void save(TransactionEntity transactionEntity) {
        transactionRepository.save(transactionEntity);
    }

    public void update(TransactionEntity transactionEntity) {
        if (transactionEntity.getId() != null) {
            transactionRepository.save(transactionEntity);
        }
    }
}
