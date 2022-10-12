package main.gui.components.dailog;

import javax.swing.JTable;
import javax.swing.JTextField;

import main.Storage;
import main.gui.Gui;
import main.gui.components.CustomJLabel;
import main.model.Invoice;

@SuppressWarnings("serial")
public class DialogInvoice extends AbctacrtDialogAccountDocWithCurrency {

    private CustomJLabel lbGood;
    private CustomJLabel lbAmount;

    private JTextField tfGood;
    private JTextField tfAmount;

    public DialogInvoice(String title, int widht, int heidht, JTable table) {
        super(title, widht, heidht, table);
    }

    @Override
    protected void createDataComponents() {
        super.createDataComponents();
        lbGood = CustomJLabel.create("Товар");
        lbAmount = CustomJLabel.create("Количество");
        tfGood = new JTextField();
        tfAmount = new JTextField();
    }

    @Override
    protected void createPanels() {
        super.createPanels();
        textPanel.add(lbGood);
        textPanel.add(tfGood);
        textPanel.add(lbAmount);
        textPanel.add(tfAmount);
    }

    @Override
    protected void fillAccountDoc() {
        super.fillAccountDoc();
        ((Invoice) doc).setGoods(tfGood.getText());
        ((Invoice) doc).setAmount(Float.parseFloat(tfAmount.getText()));

        Storage.INSTANCE.getAll().add(doc);
        Gui.INSTANCE.refreshTable();
    }

    @Override
    protected void createAccountDoc() {
        doc = new Invoice();
    }
}
