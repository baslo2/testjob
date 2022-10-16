package main.model;

public final class PaymentOrder extends AbstractAccountDoc {

    private String employee;

    @Override
    protected AccountDocType getInitializedType() {
        return AccountDocType.PAYMENT_ORDER;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
