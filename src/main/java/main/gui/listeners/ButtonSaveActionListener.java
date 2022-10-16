package main.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import main.Property;
import main.file.xml.XmlExporter;
import main.gui.components.dailog.ExeptionDialog;

public class ButtonSaveActionListener implements ActionListener {

    private JFileChooser chooser;

    @Override
    public void actionPerformed(ActionEvent event) {
        chooser = Property.fillAndCreateXmlChooser(chooser);

        int ret = chooser.showDialog(null, "Сохранить файл");
        XmlExporter exporter = new XmlExporter();
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file))) {
                writer.write(exporter.exportAllObjsToString());
                writer.flush();
                JOptionPane.showMessageDialog(chooser, "Файл успешно записан");
            } catch (IOException e) {
                ExeptionDialog.showDefaultDialog();
                e.printStackTrace();
            }
        }
    }
}
