package br.com.accountmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private Long id;

    @Column(nullable = false)
    private String account_name;

    @Column(nullable = false)
    private Date create_date;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private PersonEntity person;

    public AccountEntity() {
    }

    public AccountEntity(Long id, String account_name, Date create_date, String status, Double balance, PersonEntity person) {
        this.id = id;
        this.account_name = account_name;
        this.create_date = create_date;
        this.status = status;
        this.balance = balance;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    @Override public String toString() {
        return "AccountEntity{" +
            "id=" + id +
            ", account_name='" + account_name + '\'' +
            ", create_date=" + create_date +
            ", status='" + status + '\'' +
            ", balance=" + balance +
            ", person=" + person +
            '}';
    }
}
