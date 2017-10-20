package br.com.accountmanager.entity;

import br.com.accountmanager.util.TransactionTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date transactionDate;

    @Column(name = "VALUE", nullable = false)
    private Double value;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, length = 50)
    private TransactionTypeEnum type;

    @Column(name = "REVERSED", nullable = false)
    private Boolean reversed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID_IN")
    private AccountEntity accountIn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID_OUT")
    private AccountEntity accountOut;

    public TransactionEntity() {
    }

    public TransactionEntity(
        Long id,
        Date transactionDate,
        Double value,
        TransactionTypeEnum type,
        Boolean reversed,
        AccountEntity accountIn, AccountEntity accountOut) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.value = value;
        this.type = type;
        this.reversed = reversed;
        this.accountIn = accountIn;
        this.accountOut = accountOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TransactionTypeEnum getType() {
        return type;
    }

    public void setType(TransactionTypeEnum type) {
        this.type = type;
    }

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    public AccountEntity getAccountIn() {
        return accountIn;
    }

    public void setAccountIn(AccountEntity accountIn) {
        this.accountIn = accountIn;
    }

    public AccountEntity getAccountOut() {
        return accountOut;
    }

    public void setAccountOut(AccountEntity accountOut) {
        this.accountOut = accountOut;
    }

    @Override public String toString() {
        return "TransactionEntity{" +
            "id=" + id +
            ", transactionDate=" + transactionDate +
            ", value=" + value +
            ", type=" + type +
            ", reversed=" + reversed +
            ", accountIn=" + accountIn +
            ", accountOut=" + accountOut +
            '}';
    }
}
