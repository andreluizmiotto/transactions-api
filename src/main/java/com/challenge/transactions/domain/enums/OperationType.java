package com.challenge.transactions.domain.enums;

public enum OperationType {
    NORMAL_PURCHASE(1, "Normal Purchase"),
    INSTALLMENT_PURCHASE(2, "Purchase with installments"),
    WITHDRAWAL(3, "Withdrawal"),
    CREDIT_VOUCHER(4, "Credit Voucher");

    private final int code;
    private final String description;

    OperationType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OperationType fromCode(int code) {
        for (OperationType type : OperationType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid OperationType code: " + code);
    }

}
