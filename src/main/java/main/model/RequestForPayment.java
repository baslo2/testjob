package main.model;

public final class RequestForPayment extends AbstractAccountDocWithCurrency {

    private String partner;
    private float commission;

    @Override
    protected AccountDocType getInitializedType() {
        return AccountDocType.REQUEST_FOR_PAYMENT;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }
}
