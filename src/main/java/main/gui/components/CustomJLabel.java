package main.gui.components;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public final class CustomJLabel extends JLabel {

    private CustomJLabel(String text) {
        super(text);
    }

    public static CustomJLabel create(String text) {
        CustomJLabel label = new CustomJLabel(text);
        return label;
    }
}
