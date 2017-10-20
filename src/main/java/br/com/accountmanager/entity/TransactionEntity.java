package br.com.accountmanager.entity;

import br.com.accountmanager.util.TransactionTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

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

    @Column(name = "TYPE", nullable = false, length = 50)
    private TransactionTypeEnum type;

    @Column(name = "REVERSED", nullable = false)
    private Boolean reversed;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ACCOUNT_TRANSACTION", joinColumns = { @JoinColumn(name = "TRANSACTION_ID") }, inverseJoinColumns = {
        @JoinColumn(name = "ACCOUNT_ID_IN") })
    private List<AccountEntity> accountIn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ACCOUNT_TRANSACTION", joinColumns = { @JoinColumn(name = "TRANSACTION_ID") }, inverseJoinColumns = {
        @JoinColumn(name = "ACCOUNT_ID_OUT") })
    private List<AccountEntity> accountOut;

    public TransactionEntity() {
    }

    public TransactionEntity(
        Long id,
        Date transactionDate,
        Double value,
        TransactionTypeEnum type,
        Boolean reversed,
        List<AccountEntity> accountIn, List<AccountEntity> accountOut) {
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

    public List<AccountEntity> getAccountIn() {
        return accountIn;
    }

    public void setAccountIn(List<AccountEntity> accountIn) {
        this.accountIn = accountIn;
    }

    public List<AccountEntity> getAccountOut() {
        return accountOut;
    }

    public void setAccountOut(List<AccountEntity> accountOut) {
        this.accountOut = accountOut;
    }

    @Override public String toString() {
        return "TransactionEntity{" +
            "id=" + id +
            ", transactionDate=" + transactionDate +
            ", value=" + value +
            ", type='" + type + '\'' +
            ", reversed=" + reversed +
            ", accountIn=" + accountIn +
            ", accountOut=" + accountOut +
            '}';
    }
}
