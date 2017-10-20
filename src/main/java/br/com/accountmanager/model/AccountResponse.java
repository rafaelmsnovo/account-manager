package br.com.accountmanager.model;

import java.util.Date;

public class AccountResponse {

    private Long id;

    private String accountName;

    private Date createDate;

    private String status;

    private Double balance;

    private Long personId;

    private Long accountParentId;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String accountName, Date createDate, String status, Double balance, Long personId, Long accountParentId) {
        this.id = id;
        this.accountName = accountName;
        this.createDate = createDate;
        this.status = status;
        this.balance = balance;
        this.personId = personId;
        this.accountParentId = accountParentId;
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getAccountParentId() {
        return accountParentId;
    }

    public void setAccountParentId(Long accountParentId) {
        this.accountParentId = accountParentId;
    }

    @Override public String toString() {
        return "AccountResponse{" +
            "id=" + id +
            ", accountName='" + accountName + '\'' +
            ", createDate=" + createDate +
            ", status=" + status +
            ", balance=" + balance +
            ", personId=" + personId +
            ", accountParentId=" + accountParentId +
            '}';
    }
}
