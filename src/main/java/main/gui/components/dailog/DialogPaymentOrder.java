package main.gui.components.dailog;

import java.awt.TextField;

import javax.swing.JTable;

import main.Storage;
import main.gui.Gui;
import main.gui.components.CustomJLabel;
import main.model.PaymentOrder;

@SuppressWarnings("serial")
public class DialogPaymentOrder extends AbctacrtDialogAccountDoc {

    private CustomJLabel lbEmployee;
    private TextField tfEmployee;

    public DialogPaymentOrder(String title, int widht, int height,
            JTable table) {
        super(title, widht, height, table);
    }

    @Override
    protected void createDataComponents() {
        super.createDataComponents();
        lbEmployee = CustomJLabel.create("Сотрудник");
        tfEmployee = new TextField();
    }

    @Override
    protected void createPanels() {
        super.createPanels();
        textPanel.add(lbEmployee);
        textPanel.add(tfEmployee);
    }

    @Override
    protected void fillAccountDoc() {
        super.fillAccountDoc();
        ((PaymentOrder) doc).setEmployee(tfEmployee.getText());

        Storage.INSTANCE.getAll().add(doc);
        Gui.INSTANCE.refreshTable();
    }

    @Override
    protected void createAccountDoc() {
        doc = new PaymentOrder();
    }
}
