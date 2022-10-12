package main.gui.components.dailog;

import javax.swing.JTable;
import javax.swing.JTextField;

import main.gui.components.CustomJLabel;
import main.model.AbstractAccountDocWithCurrency;

@SuppressWarnings("serial")
public abstract class AbctacrtDialogAccountDocWithCurrency
        extends AbctacrtDialogAccountDoc {

    private CustomJLabel lbCurrency;
    private CustomJLabel lbRate;

    private JTextField tfCurrency;
    private JTextField tfRate;

    public AbctacrtDialogAccountDocWithCurrency(String title, int widht,
            int heidht, JTable tablerea) {
        super(title, widht, heidht, tablerea);
    }

    @Override
    protected void createDataComponents() {
        super.createDataComponents();
        lbCurrency = CustomJLabel.create("Валюта");
        lbRate = CustomJLabel.create("Курс валюты");
        tfCurrency = new JTextField();
        tfRate = new JTextField();
    }

    @Override
    protected void createPanels() {
        super.createPanels();
        textPanel.add(lbCurrency);
        textPanel.add(tfCurrency);
        textPanel.add(lbRate);
        textPanel.add(tfRate);
    }

    @Override
    protected void fillAccountDoc() {
        super.fillAccountDoc();
        ((AbstractAccountDocWithCurrency) doc)
                .setCurrency(tfCurrency.getText());
        ((AbstractAccountDocWithCurrency) doc)
                .setRate(Float.parseFloat(tfRate.getText()));
    }
}
