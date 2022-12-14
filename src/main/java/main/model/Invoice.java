package main.model;

public final class Invoice extends AbstractAccountDocWithCurrency {

    private String goods;
    private float amount;

    @Override
    protected AccountDocType getInitializedType() {
        return AccountDocType.INVOICE;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
