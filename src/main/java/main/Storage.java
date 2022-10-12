package main;

import java.util.ArrayList;

import main.model.AbstractAccountDoc;

public enum Storage {

    INSTANCE;

    private final ArrayList<AbstractAccountDoc> docs = new ArrayList<>();

    public ArrayList<AbstractAccountDoc> getAll() {
        return docs;
    }
}
