package main.gui.components.dailog;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import main.gui.components.CustomJButton;
import main.gui.components.CustomJLabel;
import main.gui.components.CustomJPanel;
import main.gui.components.CustomJPanel.PanelType;
import main.model.AbstractAccountDoc;

@SuppressWarnings("serial")
public class AbctacrtDialogAccountDoc extends AbstractDialog {

    private CustomJLabel lbNumber;
    private CustomJLabel lbDate;
    private CustomJLabel lbUser;
    private CustomJLabel lbSumm;

    private JTextField tfNumber;
    private JXDatePicker tfDate;
    private JTextField tfUser;
    private JTextField tfSumm;

    private CustomJButton btnOk;
    private CustomJButton btnCancel;

    protected CustomJPanel textPanel;
    private CustomJPanel buttonPanel;

    protected AbstractAccountDoc doc;
    protected JTable table;

    public AbctacrtDialogAccountDoc(String title, int widht, int height,
            JTable table) {
        super(title, widht, height);
        this.table = table;
        ;
    }

    public void buildDialog() {
        init();

        createAccountDoc();

        createDataComponents();
        createButtons();
        createPanels();
        createDialog();
    }

    protected void createAccountDoc() {
    }

    protected void createDataComponents() {
        lbNumber = CustomJLabel.create("Номер");
        lbDate = CustomJLabel.create("Дата");
        lbUser = CustomJLabel.create("Пользователь");
        lbSumm = CustomJLabel.create("Сумма");
        tfNumber = new JTextField();
        tfDate = new JXDatePicker(new Date());
        tfUser = new JTextField();
        tfSumm = new JTextField();
    }

    protected void createButtons() {
        btnOk = CustomJButton.create("Ok");
        btnOk.addActionListener(actionEvent -> {
            fillAccountDoc();
            dispose();
        });

        btnCancel = CustomJButton.create("Cancel");
        btnCancel.addActionListener(actionEvent -> dispose());
    }

    protected void createPanels() {
        textPanel = CustomJPanel.create(PanelType.FRAME_TEXT_FIELD);
        textPanel.add(lbNumber);
        textPanel.add(tfNumber);
        textPanel.add(lbDate);
        textPanel.add(tfDate);
        textPanel.add(lbUser);
        textPanel.add(tfUser);
        textPanel.add(lbSumm);
        textPanel.add(tfSumm);

        buttonPanel = CustomJPanel.create(PanelType.DIALOG_BUTTON);
        buttonPanel.add(btnOk);
        buttonPanel.add(btnCancel);
    }

    protected void createDialog() {
        getContentPane().add(textPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    protected void fillAccountDoc() {
        doc.setNumber(tfNumber.getText());
        doc.setDate(tfDate.getDate());
        doc.setUser(tfUser.getText());
        doc.setSumm(Float.parseFloat(tfSumm.getText()));
    }
}
