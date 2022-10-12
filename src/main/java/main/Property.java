package main;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Property {

    public static SimpleDateFormat dateFormate = new SimpleDateFormat(
            "dd.MM.yyyy");

    public static JFileChooser fillAndCreateXmlChooser(JFileChooser chooser) {
        chooser = new JFileChooser(new File("."));
        chooser.setFileFilter(
                new FileNameExtensionFilter("XML file (*.xml)", "xml"));
        return chooser;
    }

}
