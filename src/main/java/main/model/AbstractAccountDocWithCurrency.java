package main.model;

public abstract class AbstractAccountDocWithCurrency
        extends AbstractAccountDoc {

    private static final long serialVersionUID = 5933044740838650081L;

    private String currency;
    private float rate;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
