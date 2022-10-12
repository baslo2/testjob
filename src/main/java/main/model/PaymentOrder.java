package main.model;

public final class PaymentOrder extends AbstractAccountDoc {

    private static final long serialVersionUID = 1611601339914314163L;

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
