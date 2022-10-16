package main.model;

import java.util.Date;

public abstract class AbstractAccountDoc {

    private AccountDocType type;
    private String number;
    private Date date;
    private String user;
    private float summ;

    private boolean isMarkedForDelete;

    public AbstractAccountDoc() {
        this.type = getInitializedType();
    }

    protected abstract AccountDocType getInitializedType();

    public AccountDocType getType() {
        return type;
    }

    public void setType(AccountDocType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public float getSumm() {
        return summ;
    }

    public void setSumm(float summ) {
        this.summ = summ;
    }

    public boolean isMarkedForDelete() {
        return isMarkedForDelete;
    }

    public void setMarkedForDelete(boolean isMarkedForDelete) {
        this.isMarkedForDelete = isMarkedForDelete;
    }
}
