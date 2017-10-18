package br.com.accountmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Date transaction_date;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Boolean reversed;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID_IN", nullable = false)
    private AccountEntity accountIn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID_OUT", nullable = false)
    private AccountEntity accountOut;

    public TransactionEntity() {
    }

    public TransactionEntity(
        Long id,
        Date transaction_date,
        Double value,
        String type,
        Boolean reversed,
        AccountEntity accountIn,
        AccountEntity accountOut) {
        this.id = id;
        this.transaction_date = transaction_date;
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

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
        return "ServiceOrderEntity{" +
            "id=" + id +
            ", transaction_date=" + transaction_date +
            ", value=" + value +
            ", type='" + type + '\'' +
            ", reversed=" + reversed +
            ", accountIn=" + accountIn +
            ", accountOut=" + accountOut +
            '}';
    }
}
