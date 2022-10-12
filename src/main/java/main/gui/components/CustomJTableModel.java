package main.gui.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import main.Constants;
import main.Property;
import main.model.AbstractAccountDoc;
import main.model.Invoice;
import main.model.PaymentOrder;

@SuppressWarnings("serial")
public class CustomJTableModel extends AbstractTableModel {

    private static final int COLUMNS_COUNT = 2;

    public static final int VALUE_COL_IDX = 0;
    public static final int DEL_MARK_COL_IDX = 1;

    private final ArrayList<AbstractAccountDoc> docs;

    public CustomJTableModel(ArrayList<AbstractAccountDoc> docs) {
        this.docs = docs;
    }

    @Override
    public int getRowCount() {
        return docs.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS_COUNT;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case VALUE_COL_IDX:
                return "Документ";

            case DEL_MARK_COL_IDX:
                return "Маркер на удаление";

            default:
                return Constants.BLANK_STRING;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AbstractAccountDoc doc = docs.get(rowIndex);
        String str = new String();
        switch (columnIndex) {

            case VALUE_COL_IDX:
                if (doc instanceof Invoice) {
                    str = "Накладная от "
                            + Property.dateFormate.format(doc.getDate())
                                    .toString()
                            + " номер " + doc.getNumber();
                } else if (doc instanceof PaymentOrder) {
                    str = "Платежка от "
                            + Property.dateFormate.format(doc.getDate())
                                    .toString()
                            + " номер " + doc.getNumber();
                } else {
                    str = "Заявка на оплату от "
                            + Property.dateFormate.format(doc.getDate())
                                    .toString()
                            + " номер " + doc.getNumber();
                }
                return str;

            case DEL_MARK_COL_IDX:
                return doc.isMarkedForDelete();

            default:
                return Constants.BLANK_STRING;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == DEL_MARK_COL_IDX ? Boolean.class
                : super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == DEL_MARK_COL_IDX;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        docs.get(rowIndex).setMarkedForDelete((Boolean) aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

}
