package br.com.accountmanager.entity;

import br.com.accountmanager.util.AccountStatusEnum;

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
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ACCOUNT_NAME", nullable = false, length = 255)
    private String accountName;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 50)
    private AccountStatusEnum status;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_PARENT_ID")
    private AccountEntity accountParent;

    public AccountEntity() {
    }

    public AccountEntity(
        Long id,
        String accountName,
        Date createDate,
        AccountStatusEnum status,
        Double balance,
        PersonEntity person,
        AccountEntity accountParent) {
        this.id = id;
        this.accountName = accountName;
        this.createDate = createDate;
        this.status = status;
        this.balance = balance;
        this.person = person;
        this.accountParent = accountParent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public AccountStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AccountStatusEnum status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public AccountEntity getAccountParent() {
        return accountParent;
    }

    public void setAccountParent(AccountEntity accountParent) {
        this.accountParent = accountParent;
    }

    @Override public String toString() {
        return "AccountEntity{" +
            "id=" + id +
            ", accountName='" + accountName + '\'' +
            ", createDate=" + createDate +
            ", status=" + status +
            ", balance=" + balance +
            ", person=" + person +
            ", accountParent=" + accountParent +
            '}';
    }
}
