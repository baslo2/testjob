package main.model;

public enum AccountDocType {

    INVOICE("invoice"),
    PAYMENT_ORDER("payment_order"),
    REQUEST_FOR_PAYMENT("request_for_payment");

    private String type;

    AccountDocType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static AccountDocType getAccountDocType(String type) {
        for (AccountDocType accountDocType : AccountDocType.values()) {
            if (type.trim().equalsIgnoreCase(accountDocType.getType())) {
                return accountDocType;
            }
        }
        return null;
    }
}
