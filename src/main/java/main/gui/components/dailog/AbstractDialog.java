package main.gui.components.dailog;

import java.awt.BorderLayout;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public abstract class AbstractDialog extends JDialog {

    private static final int COMPONENTS_GAP = 2;

    private final String title;
    private final int widht;
    private final int height;

    public AbstractDialog(String title, int widht, int height) {
        this.title = title;
        this.widht = widht;
        this.height = height;
    }

    public void init() {
        setTitle(title);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(COMPONENTS_GAP, COMPONENTS_GAP));
        setSize(widht, height);
        setModal(true);
    }
}
