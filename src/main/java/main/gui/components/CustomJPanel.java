package main.gui.components;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public final class CustomJPanel extends JPanel {

    public enum PanelType {
        FRAME_TEXT_FIELD,
        FRAME_BUTTON,
        DIALOG_TEXT_FIELD,
        DIALOG_BUTTON,
    }

    private static final int FRAME_BUTTON_WIDTH = 200;
    private static final int FRAME_BUTTON_HEIGHT = 200;
    private static final int FRAME_BUTTON_GAP = 10;
    private static final int FRAME_BUTTON_ROWS = 8;
    private static final int FRAME_BUTTON_COL = 1;

    private static final int FRAME_TEXT_WIDTH = 200;
    private static final int FRAME_TEXT_HEIGHT = 200;
    private static final int FRAME_TEXT_GRIND_LAYOUT_SIZE = 10;

    private static final int DIALOG_TEXT_FIELD_WIDTH = 500;
    private static final int DIALOG_TEXT_FIELD_HIEGHT = 500;
    private static final int DIALOG_TEXT_GRIND_LAYOUT_SIZE = 10;

    private static final int EMPTY_BORDER_SIZE = 10;

    private static final int DIALOG_BUTTON_WIDTH = 200;
    private static final int DIALOG_BUTTON_HEIGHT = 200;
    private static final int DIALOG_BUTTON_FLOW_LAYOUT_HGAP = 10;
    private static final int DIALOG_BUTTON_FLOW_LAYOUT_VGAP = 2;

    private CustomJPanel() {
    }

    public static CustomJPanel create(PanelType panelType) {
        CustomJPanel panel = new CustomJPanel();
        panel.setPanelParams(panel, panelType);
        return panel;
    }

    private void setPanelParams(CustomJPanel panel, PanelType panelType) {
        String panelName = panelType.name();
        switch (panelType) {
            case FRAME_TEXT_FIELD:
                panel.setPanelParams(panel, panelName, FRAME_TEXT_WIDTH,
                        FRAME_TEXT_HEIGHT,
                        new GridLayout(FRAME_TEXT_GRIND_LAYOUT_SIZE,
                                FRAME_TEXT_GRIND_LAYOUT_SIZE,
                                FRAME_TEXT_GRIND_LAYOUT_SIZE,
                                FRAME_TEXT_GRIND_LAYOUT_SIZE));
                break;

            case FRAME_BUTTON:
                panel.setPanelParams(panel, panelName, FRAME_BUTTON_WIDTH,
                        FRAME_BUTTON_HEIGHT,
                        new GridLayout(FRAME_BUTTON_ROWS, FRAME_BUTTON_COL,
                                FRAME_BUTTON_GAP, FRAME_BUTTON_GAP));
                break;
            case DIALOG_TEXT_FIELD:
                panel.setPanelParams(panel, panelName, DIALOG_TEXT_FIELD_WIDTH,
                        DIALOG_TEXT_FIELD_HIEGHT,
                        new GridLayout(DIALOG_TEXT_GRIND_LAYOUT_SIZE,
                                DIALOG_TEXT_GRIND_LAYOUT_SIZE,
                                DIALOG_TEXT_GRIND_LAYOUT_SIZE,
                                DIALOG_TEXT_GRIND_LAYOUT_SIZE));
                break;
            case DIALOG_BUTTON:
                panel.setPanelParams(panel, panelName, DIALOG_BUTTON_WIDTH,
                        DIALOG_BUTTON_HEIGHT,
                        new FlowLayout(FlowLayout.CENTER,
                                DIALOG_BUTTON_FLOW_LAYOUT_HGAP,
                                DIALOG_BUTTON_FLOW_LAYOUT_VGAP));
        }
    }

    private void setPanelParams(CustomJPanel panel, String name, int wigth,
            int height, LayoutManager layout) {
        panel.setName(name);
        panel.setSize(wigth, height);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE,
                EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE));
    }
}
