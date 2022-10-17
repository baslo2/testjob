package main.gui.components.dailog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import main.Storage;
import main.gui.components.CustomJButton;
import main.gui.components.CustomJPanel;
import main.gui.components.CustomJPanel.PanelType;
import main.model.AbstractAccountDoc;
import main.model.AbstractAccountDocWithCurrency;
import main.model.Invoice;
import main.model.PaymentOrder;
import main.model.RequestForPayment;

@SuppressWarnings("serial")
public class DialogView extends AbstractDialog {

    private static final int EMPTY_BORDER_SIZE = 10;

    private static final int SCROLL_PANE_PREFERRED_SIZE_WIDHT = 300;
    private static final int SCROLL_PANE_PREFERRED_SIZE_HEIDHT = 300;

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private final JTable table;
    private CustomJButton btnOk;

    public DialogView(String title, int widht, int height, JTable table) {
        super(title, widht, height);
        this.table = table;
    }

    public void buildDialog() {
        init();

        AbstractAccountDoc doc = Storage.INSTANCE.getAll()
                .get(table.getSelectedRow());
        StringBuilder sb = new StringBuilder();

        sb.append("Номер: " + doc.getNumber() + "\nДата: " + doc.getDate()
                + "\nПользователь: " + doc.getUser()
                + "\nСумма: " + doc.getSumm());

        if (doc instanceof AbstractAccountDocWithCurrency) {
            AbstractAccountDocWithCurrency currencyDoc = (AbstractAccountDocWithCurrency) doc;
            sb.append("\nВалюта: " + currencyDoc.getCurrency()
                    + "\nКурс валюты: " + currencyDoc.getRate());
        }

        if (doc instanceof Invoice) {
            setTitle("Накладная");
            Invoice inv = (Invoice) doc;
            sb.append("\nТовар: " + inv.getGoods() + "\nКоличество: "
                    + inv.getAmount());
        } else if (doc instanceof RequestForPayment) {
            setTitle("Заявка на оплату");
            RequestForPayment rfp = (RequestForPayment) doc;
            sb.append("\nКонтрагент: " + rfp.getPartner() + "\nКоммисия: "
                    + rfp.getCommission());
        } else {
            setTitle("Платёжка");
            sb.append("\nСотрудник: " + ((PaymentOrder) doc).getEmployee());
        }

        textArea = new JTextArea();
        textArea.append(sb.toString());

        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(
                new Dimension(SCROLL_PANE_PREFERRED_SIZE_WIDHT,
                        SCROLL_PANE_PREFERRED_SIZE_HEIDHT));

        btnOk = CustomJButton.create("OK");
        btnOk.addActionListener(actionEvent -> dispose());

        CustomJPanel textPanel = CustomJPanel
                .create(PanelType.DIALOG_TEXT_FIELD);
        textPanel.setLayout(new FlowLayout());
        textPanel.setBorder(new EmptyBorder(EMPTY_BORDER_SIZE,
                EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE));
        textPanel.add(scrollPane);

        CustomJPanel btnPanel = CustomJPanel.create(PanelType.DIALOG_BUTTON);
        btnPanel.add(btnOk);

        getContentPane().add(textPanel, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
