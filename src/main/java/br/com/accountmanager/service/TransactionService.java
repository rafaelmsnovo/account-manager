package br.com.accountmanager.service;

import br.com.accountmanager.entity.AccountEntity;
import br.com.accountmanager.entity.TransactionEntity;
import br.com.accountmanager.model.AccountResponse;
import br.com.accountmanager.model.TransactionResponse;
import br.com.accountmanager.repository.TransactionRepository;
import br.com.accountmanager.util.AccountStatusEnum;
import br.com.accountmanager.util.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Value("${transaction.key}")
    private String key;

    @Transactional
    public void register(TransactionResponse transactionResponse)
        throws Exception {

        if (transactionResponse.getType().equals(TransactionTypeEnum.TRANSFER.getValue())) {
            AccountResponse aIn = accountService.get(transactionResponse.getAccountInId());
            AccountResponse aOut = accountService.get(transactionResponse.getAccountOutId());

            if (!aIn.getStatus().equals(AccountStatusEnum.ACTIVE.getValue()) || !aOut.getStatus().equals(AccountStatusEnum.ACTIVE.getValue())) {
                throw new Exception("A(s) Conta(s) não esta(estão) ativa(s).");
            }
            if (!aOut.getAccountParentId().equals(aIn.getId())) {
                throw new Exception("A conta destino não é filha da conta origem");
            }
            aIn.setBalance(aIn.getBalance() - transactionResponse.getValue());
            aOut.setBalance(aOut.getBalance() + transactionResponse.getValue());
            accountService.update(aIn);
            accountService.update(aOut);

            transactionResponse.setTransactionDate(new Date());
            transactionResponse.setReversed(false);
            this.save(transactionResponse);
        } else {
            if (transactionResponse.getTransactionKey() == null || !key.equals(transactionResponse.getTransactionKey())) {
                throw new Exception("Código de Aporte incorreto");
            }
            AccountResponse aOut = accountService.get(transactionResponse.getAccountOutId());
            if (aOut.getAccountParentId() != null) {
                throw new Exception("A conta destino não é matriz.");
            }
            aOut.setBalance(aOut.getBalance() + transactionResponse.getValue());
            accountService.update(aOut);
            transactionResponse.setTransactionDate(new Date());
            transactionResponse.setReversed(false);
            this.save(transactionResponse);
        }
    }

    @Transactional
    public void reverse(TransactionResponse transactionResponse)
        throws Exception {

        if (transactionResponse.getType().equals(TransactionTypeEnum.TRANSFER.getValue())) {
            AccountResponse aIn = accountService.get(transactionResponse.getAccountInId());
            AccountResponse aOut = accountService.get(transactionResponse.getAccountOutId());

            if (!aIn.getStatus().equals(AccountStatusEnum.ACTIVE.getValue()) || !aOut.getStatus().equals(AccountStatusEnum.ACTIVE.getValue())) {
                throw new Exception("A(s) Conta(s) não esta(estão) ativa(s).");
            }

            aIn.setBalance(aIn.getBalance() + transactionResponse.getValue());
            aOut.setBalance(aOut.getBalance() - transactionResponse.getValue());
            accountService.update(aIn);
            accountService.update(aOut);

            transactionResponse.setReversed(true);
            this.update(transactionResponse);
        } else {
            if (transactionResponse.getTransactionKey() == null || !key.equals(transactionResponse.getTransactionKey())) {
                throw new Exception("Código de Aporte incorreto");
            }
            AccountResponse aOut = accountService.get(transactionResponse.getAccountOutId());
            aOut.setBalance(aOut.getBalance() - transactionResponse.getValue());
            accountService.update(aOut);
            transactionResponse.setReversed(true);
            this.update(transactionResponse);
        }
    }

    public List<TransactionResponse> getAll() {
        List<TransactionResponse> responseList = new ArrayList<TransactionResponse>();
        List<TransactionEntity> transactions = transactionRepository.findAll();
        for (TransactionEntity transaction : transactions) {
            responseList.add(parseTransactionRespose(transaction));
        }
        return responseList;
    }

    public TransactionResponse get(Long id) {
        return parseTransactionRespose(transactionRepository.getOne(id));
    }

    public void remove(Long personId) {
        transactionRepository.delete(personId);
    }

    public void save(TransactionResponse transactionResponse) {
        transactionRepository.save(parseTransactionEntity(transactionResponse));
    }

    public void update(TransactionResponse transactionResponse) {
        if (transactionResponse.getId() != null) {
            TransactionEntity transactionEntity = parseTransactionEntity(transactionResponse);
            transactionRepository.save(transactionEntity);
        }
    }

    private TransactionResponse parseTransactionRespose(TransactionEntity t) {
        return new TransactionResponse(
            t.getId(),
            t.getTransactionDate(),
            t.getValue(),
            t.getType().getValue(),
            t.getReversed(),
            t.getAccountIn() == null ? null : t.getAccountIn().getId(),
            t.getAccountOut() == null ? null : t.getAccountOut().getId(),
            "");
    }

    private TransactionEntity parseTransactionEntity(TransactionResponse t) {
        AccountEntity aIn = null;
        AccountEntity aOut = null;
        if (t.getAccountInId() != null) {
            aIn = new AccountEntity();
            aIn.setId(t.getAccountInId());
        }
        if (t.getAccountOutId() != null) {
            aOut = new AccountEntity();
            aOut.setId(t.getAccountOutId());
        }
        return new TransactionEntity(
            t.getId(),
            t.getTransactionDate(),
            t.getValue(),
            TransactionTypeEnum.fromString(t.getType()),
            t.getReversed(),
            aIn,
            aOut);
    }
}
