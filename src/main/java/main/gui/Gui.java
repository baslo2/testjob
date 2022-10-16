package main.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import main.Constants;
import main.Storage;
import main.gui.components.CustomJButton;
import main.gui.components.CustomJFrame;
import main.gui.components.CustomJPanel;
import main.gui.components.CustomJPanel.PanelType;
import main.gui.components.CustomJTableModel;
import main.gui.components.dailog.DialogInvoice;
import main.gui.components.dailog.DialogPaymentOrder;
import main.gui.components.dailog.DialogRequestForPayment;
import main.gui.components.dailog.DialogView;
import main.gui.listeners.ButtonLoadActionListener;
import main.gui.listeners.ButtonSaveActionListener;
import main.model.AbstractAccountDoc;

public enum Gui {

    INSTANCE;

    private JTable table;
    private JScrollPane scrollPane;
    private AbstractTableModel tableModel;

    private JButton btnInvoice;
    private JButton btnPaymentOrder;
    private JButton btnRequestForPayment;
    private JButton btnSave;
    private JButton btnLoad;
    private JButton btnView;
    private JButton btnDel;
    private JButton btnExit;

    private JPanel tablePanel;
    private JPanel btnPanel;

    private CustomJFrame frame;

    public void refreshTable() {
        tableModel.fireTableDataChanged();
    }

    public void buildGui() {
        createTable();
        createButtons();
        createPanels();
        createFrame();
    }

    private void createTable() {
        table = new JTable();
        table.setModel(new CustomJTableModel(Storage.INSTANCE.getAll()));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        scrollPane = new JScrollPane(table);
        tableModel = (AbstractTableModel) table.getModel();
    }

    private void createButtons() {
        btnInvoice = CustomJButton.create(Constants.INVOICE);
        btnInvoice.addActionListener(
                actionEvent -> new DialogInvoice(Constants.INVOICE,
                        Constants.DOC_WIDHT, Constants.DOC_HEIDHT,
                        table).buildDialog());

        btnPaymentOrder = CustomJButton.create(Constants.PAYMENT_ORDER);
        btnPaymentOrder
                .addActionListener(
                        actionEvent -> new DialogPaymentOrder(
                                Constants.PAYMENT_ORDER,
                                Constants.DOC_WIDHT, Constants.DOC_HEIDHT,
                                table).buildDialog());

        btnRequestForPayment = CustomJButton
                .create(Constants.REQUEST_FOR_ORDER);
        btnRequestForPayment.addActionListener(
                actionEvent -> new DialogRequestForPayment(
                        Constants.REQUEST_FOR_ORDER,
                        Constants.DOC_WIDHT, Constants.DOC_HEIDHT, table)
                                .buildDialog());

        btnSave = CustomJButton.create(Constants.SAVE);
        btnSave.addActionListener(new ButtonSaveActionListener());

        btnLoad = CustomJButton.create(Constants.LOAD);
        btnLoad.addActionListener(new ButtonLoadActionListener());

        btnView = CustomJButton.create(Constants.VIEW);
        btnView.addActionListener(
                actionEvent -> {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(frame,
                                "Не выбран файл для просмотра");
                        return;
                    }
                    new DialogView(null, Constants.VIEW_WIDHT,
                            Constants.VIEW_HEIDHT,
                            table).buildDialog();
                });

        btnDel = CustomJButton.create(Constants.DELETE);
        btnDel.addActionListener(actionEvent -> {
            List<AbstractAccountDoc> docsForDel = Storage.INSTANCE.getAll()
                    .stream()
                    .filter(doc -> doc.isMarkedForDelete())
                    .collect(Collectors.toList());
            for (AbstractAccountDoc docForDel : docsForDel) {
                Storage.INSTANCE.getAll().remove(docForDel);
            }
            Gui.INSTANCE.refreshTable();
        });

        btnExit = CustomJButton.create(Constants.EXIT);
        btnExit.addActionListener(actionEvent -> System.exit(0));
    }

    private void createPanels() {
        tablePanel = CustomJPanel.create(PanelType.FRAME_TEXT_FIELD);
        tablePanel.setLayout(new GridLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        btnPanel = CustomJPanel.create(PanelType.FRAME_BUTTON);
        btnPanel.add(btnInvoice);
        btnPanel.add(btnPaymentOrder);
        btnPanel.add(btnRequestForPayment);
        btnPanel.add(btnSave);
        btnPanel.add(btnLoad);
        btnPanel.add(btnView);
        btnPanel.add(btnDel);
        btnPanel.add(btnExit);
    }

    private void createFrame() {
        frame = CustomJFrame.create("Test");

        frame.getContentPane().add(tablePanel, BorderLayout.CENTER);
        frame.getContentPane().add(btnPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}
