package main.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import main.Property;
import main.Storage;
import main.file.xml.XmlImporter;
import main.gui.Gui;
import main.gui.components.dailog.ExeptionDialog;
import main.model.AbstractAccountDoc;

public class ButtonLoadActionListener implements ActionListener {

    JFileChooser chooser;

    @Override
    public void actionPerformed(ActionEvent event) {
        chooser = Property.fillAndCreateXmlChooser(chooser);

        int ret = chooser.showDialog(null, "Загрузить файл");
        XmlImporter importer = new XmlImporter();
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(file))) {
                ArrayList<AbstractAccountDoc> docs = importer
                        .ImportDocsFromFile(reader);
                for (AbstractAccountDoc doc : docs) {
                    Storage.INSTANCE.getAll().add(doc);
                    Gui.INSTANCE.refreshTable();
                }
            } catch (IllegalAccessException | IOException e) {
                ExeptionDialog.showDefaultDialog();
                e.printStackTrace();
            }
        }
    }
}
