package main.gui.components.dailog;

import javax.swing.JOptionPane;

public class ExeptionDialog {

    private static final String ERROR_DEFAULT_MSG = "Ошибка при работе программы";
    private static final String ERROR_DEFAULT_TITLE = "Ошибка";

    public static void showDefaultDialog() {
        JOptionPane.showMessageDialog(null, ERROR_DEFAULT_MSG,
                ERROR_DEFAULT_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    public static void showDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, ERROR_DEFAULT_TITLE,
                JOptionPane.ERROR_MESSAGE);
    }
}
