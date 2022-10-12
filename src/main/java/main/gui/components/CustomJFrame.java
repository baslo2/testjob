package main.gui.components;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public final class CustomJFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int BORDER_LAYOUT_SIZE = 2;

    private CustomJFrame(String title) {
        super(title);
    }

    public static CustomJFrame create(String title) {

        CustomJFrame frame = new CustomJFrame(title);

        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(
                new BorderLayout(BORDER_LAYOUT_SIZE, BORDER_LAYOUT_SIZE));

        return frame;
    }

}
