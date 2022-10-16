package main.file.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

public class XmlExporterTest extends AbstrectFileTest {

    @Test
    void exportAllObjToStringTest() throws Exception {
        XmlExporter exporter = new XmlExporter();

        String expected;

        String lineSeparator = System.lineSeparator();
        try (BufferedReader reader = Files.newBufferedReader(
                getFilePath(), StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(lineSeparator);
            }
            expected = sb.toString();
        }

        String actual = new String(exporter.exportAllObjsToString());

        System.out.println("expected: ");
        System.out.println(expected.trim());
        System.out.println("actual: ");
        System.out.println(actual.trim());

        assertEquals(expected.trim(), actual.trim());
    }
}
