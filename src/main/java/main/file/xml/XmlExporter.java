package main.file.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import main.Property;
import main.Storage;
import main.file.Tag;
import main.gui.components.dailog.ExeptionDialog;
import main.model.AbstractAccountDoc;
import main.model.AbstractAccountDocWithCurrency;
import main.model.Invoice;
import main.model.PaymentOrder;
import main.model.RequestForPayment;

public class XmlExporter {

    private static final String XML_VERSION = "1.0";
    private static final String XML_INDENT = "yes";
    private static final String XML_STANDALONE = "yes";

    public String exportAllObjsToString() {
        try (ByteArrayOutputStream objsOutputStream = new ByteArrayOutputStream()) {
            writeObjsToXmlOutput(objsOutputStream);
            String xml = new String(objsOutputStream.toByteArray(),
                    StandardCharsets.UTF_8);
            return formatXML(xml);
        } catch (IOException e) {
            ExeptionDialog.showDefaultDialog();
            e.printStackTrace();
        }
        return null;
    }

    private void writeObjsToXmlOutput(OutputStream objsOutputStream) {
        try (XmlWriterAutoCloseWrapper xmlWriterAutoCloseWrapper = new XmlWriterAutoCloseWrapper(
                XMLOutputFactory
                        .newInstance().createXMLStreamWriter(objsOutputStream,
                                StandardCharsets.UTF_8.name()))) {
            XMLStreamWriter xmlWriter = xmlWriterAutoCloseWrapper.writer();
            xmlWriter.writeStartDocument(StandardCharsets.UTF_8.name(),
                    XML_VERSION);
            xmlWriter.writeStartElement(Tag.ACCOUNTING_DOCUMENTS.getTagName());

            for (AbstractAccountDoc doc : Storage.INSTANCE.getAll()) {
                addObjToXmlwriter(xmlWriter, doc);
            }
            xmlWriter.writeEndDocument();
        } catch (XMLStreamException e) {
            ExeptionDialog.showDefaultDialog();
            e.printStackTrace();
        } catch (FactoryConfigurationError e) {
            ExeptionDialog.showDefaultDialog();
            e.printStackTrace();
        } catch (Exception e) {
            ExeptionDialog.showDefaultDialog();
            e.printStackTrace();
        }
    }

    private void addObjToXmlwriter(XMLStreamWriter xmlWriter,
            AbstractAccountDoc doc) throws XMLStreamException {
        xmlWriter.writeStartElement(Tag.DOCUMENT.getTagName());
        xmlWriter.writeAttribute(Tag.TYPE.getTagName(),
                doc.getType().toString());

        xmlWriter.writeStartElement(Tag.NUMBER.getTagName());
        xmlWriter.writeCharacters(doc.getNumber());
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement(Tag.DATE.getTagName());
        xmlWriter.writeCharacters(Property.dateFormate.format(doc.getDate()));
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement(Tag.USER.getTagName());
        xmlWriter.writeCharacters(doc.getUser());
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement(Tag.SUMM.getTagName());
        xmlWriter.writeCharacters(((Float) doc.getSumm()).toString());
        xmlWriter.writeEndElement();

        if (doc instanceof PaymentOrder) {
            xmlWriter.writeStartElement(Tag.EMPLOYEE.getTagName());
            xmlWriter.writeCharacters(((PaymentOrder) doc).getEmployee());
            xmlWriter.writeEndElement();
        } else {
            AbstractAccountDocWithCurrency currencyDoc = (AbstractAccountDocWithCurrency) doc;
            xmlWriter.writeStartElement(Tag.CURRENCY.getTagName());
            xmlWriter.writeCharacters(currencyDoc.getCurrency());
            xmlWriter.writeEndElement();

            xmlWriter.writeStartElement(Tag.RATE.getTagName());
            xmlWriter.writeCharacters(
                    ((Float) currencyDoc.getRate()).toString());
            xmlWriter.writeEndElement();
        }

        if (doc instanceof Invoice) {
            Invoice invoice = (Invoice) doc;
            xmlWriter.writeStartElement(Tag.GOODS.getTagName());
            xmlWriter.writeCharacters(invoice.getGoods());
            xmlWriter.writeEndElement();

            xmlWriter.writeStartElement(Tag.AMOUNT.getTagName());
            xmlWriter.writeCharacters(((Float) invoice.getAmount()).toString());
            xmlWriter.writeEndElement();
        }

        if (doc instanceof RequestForPayment) {
            RequestForPayment payment = (RequestForPayment) doc;
            xmlWriter.writeStartElement(Tag.PARTNER.getTagName());
            xmlWriter.writeCharacters(payment.getPartner());
            xmlWriter.writeEndElement();
            xmlWriter.writeStartElement(Tag.COMMISSION.getTagName());
            xmlWriter.writeCharacters(
                    ((Float) payment.getCommission()).toString());
            xmlWriter.writeEndElement();
        }

        xmlWriter.writeEndElement();
    }

    private String formatXML(String xml) {
        try (StringReader inputReader = new StringReader(xml);
                StringWriter outputWriter = new StringWriter()) {
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, XML_INDENT);
            transformer.setOutputProperty(OutputKeys.STANDALONE,
                    XML_STANDALONE);

            StreamSource streamSource = new StreamSource(inputReader);
            transformer.transform(streamSource, new StreamResult(outputWriter));
            outputWriter.flush();

            return outputWriter.toString();

        } catch (IOException e) {
            ExeptionDialog.showDefaultDialog();
            e.printStackTrace();
        } catch (TransformerException e) {
            ExeptionDialog.showDefaultDialog();
            e.printStackTrace();
        }
        return null;
    }
}
