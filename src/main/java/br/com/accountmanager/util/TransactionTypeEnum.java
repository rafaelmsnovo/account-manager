package br.com.accountmanager.util;

public enum TransactionTypeEnum {

    TRANSFER("ATIVA"),
    CONTRIBUTION("APORTE");

    private String value;

    TransactionTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
