package br.com.accountmanager.util;

public enum TransactionTypeEnum {

    TRANSFER("TRANSFERÊCIA"),
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

    public static TransactionTypeEnum fromString(String text) {
        for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
