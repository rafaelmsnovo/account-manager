package br.com.accountmanager.util;

public enum AccountStatusEnum {

    ACTIVE("ATIVA"),
    BLOCKED("BLOQUEADA"),
    CANCELED("CANCELADA");

    private String value;

    AccountStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
