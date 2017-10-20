package br.com.accountmanager.model;

import java.util.Date;

public class TransactionResponse {

    private Long id;

    private Date transactionDate;

    private Double value;

    private String type;

    private Boolean reversed;

    private Long accountInId;

    private Long accountOutId;

    private String transactionKey;

    public TransactionResponse() {
    }

    public TransactionResponse(
        Long id,
        Date transactionDate,
        Double value,
        String type,
        Boolean reversed,
        Long accountInId,
        Long accountOutId,
        String transactionKey) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.value = value;
        this.type = type;
        this.reversed = reversed;
        this.accountInId = accountInId;
        this.accountOutId = accountOutId;
        this.transactionKey = transactionKey;
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

    public Long getAccountInId() {
        return accountInId;
    }

    public void setAccountInId(Long accountInId) {
        this.accountInId = accountInId;
    }

    public Long getAccountOutId() {
        return accountOutId;
    }

    public void setAccountOutId(Long accountOutId) {
        this.accountOutId = accountOutId;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public void setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
    }

    @Override public String toString() {
        return "TransactionResponse{" +
            "id=" + id +
            ", transactionDate=" + transactionDate +
            ", value=" + value +
            ", type='" + type + '\'' +
            ", reversed=" + reversed +
            ", accountInId=" + accountInId +
            ", accountOutId=" + accountOutId +
            ", transactionKey='" + transactionKey + '\'' +
            '}';
    }
}
