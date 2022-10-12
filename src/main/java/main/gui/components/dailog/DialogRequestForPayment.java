package main.gui.components.dailog;

import javax.swing.JTable;
import javax.swing.JTextField;

import main.Storage;
import main.gui.Gui;
import main.gui.components.CustomJLabel;
import main.model.RequestForPayment;

@SuppressWarnings("serial")
public class DialogRequestForPayment
        extends AbctacrtDialogAccountDocWithCurrency {

    private CustomJLabel lbPartner;
    private CustomJLabel lbCommission;

    private JTextField tfPartner;
    private JTextField tfCommission;

    public DialogRequestForPayment(String title, int widht, int heidht,
            JTable table) {
        super(title, widht, heidht, table);
    }

    @Override
    protected void createDataComponents() {
        super.createDataComponents();

        lbPartner = CustomJLabel.create("Контрагент");
        lbCommission = CustomJLabel.create("Комиссия");

        tfPartner = new JTextField();
        tfCommission = new JTextField();
    }

    @Override
    protected void createPanels() {
        super.createPanels();

        textPanel.add(lbPartner);
        textPanel.add(tfPartner);
        textPanel.add(lbCommission);
        textPanel.add(tfCommission);
    }

    @Override
    protected void fillAccountDoc() {
        super.fillAccountDoc();

        ((RequestForPayment) doc).setPartner(tfPartner.getText());
        ((RequestForPayment) doc)
                .setCommission(Float.parseFloat(tfCommission.getText()));

        Storage.INSTANCE.getAll().add(doc);
        Gui.INSTANCE.refreshTable();
    }

    @Override
    protected void createAccountDoc() {
        doc = new RequestForPayment();
    }
}
