package main.file.xml;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import main.Property;
import main.file.Tag;
import main.gui.components.dailog.ExeptionDialog;
import main.model.AbstractAccountDoc;
import main.model.AbstractAccountDocWithCurrency;
import main.model.AccountDocType;
import main.model.Invoice;
import main.model.PaymentOrder;
import main.model.RequestForPayment;

public final class XmlImporter {

    public ArrayList<AbstractAccountDoc> ImportDocsFromFile(
            FileInputStream fileInputStream)
            throws IllegalAccessException {
        try (XmlReaderAutoCloseWrapper xmlReaderAutoCloseWrapper = new XmlReaderAutoCloseWrapper(
                XMLInputFactory.newInstance()
                        .createXMLStreamReader(fileInputStream))) {
            XMLStreamReader reader = xmlReaderAutoCloseWrapper.reader();

            ArrayList<AbstractAccountDoc> docs = new ArrayList();
            AbstractAccountDoc doc = null;

            while (reader.hasNext()) {
                int currentParseEvent = reader.next();
                if (XMLStreamConstants.START_ELEMENT == currentParseEvent) {
                    String tagName = reader.getName().getLocalPart();

                    Tag tag = Tag.getTag(tagName);
                    if (tag == null) {
                        throw new IllegalArgumentException(
                                "Wrong start of tag name: " + tagName);
                    }

                    switch (tag) {

                        case ACCOUNTING_DOCUMENTS:
                        case TYPE:
                            break;

                        case DOCUMENT:
                            checkAttribut(reader);
                            doc = createAccountDoc(reader);
                            break;

                        case NUMBER:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            doc.setNumber(reader.getText());
                            break;

                        case DATE:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            Date date = Property.dateFormate
                                    .parse(reader.getText());
                            doc.setDate(date);
                            break;

                        case USER:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            doc.setUser(reader.getText());
                            break;

                        case SUMM:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            float summ = Float.parseFloat(reader.getText());
                            doc.setSumm(summ);
                            break;

                        case CURRENCY:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            if (doc instanceof AbstractAccountDocWithCurrency) {
                                ((AbstractAccountDocWithCurrency) doc)
                                        .setCurrency(reader.getText());
                            }
                            break;

                        case RATE:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            float rate = Float.parseFloat(reader.getText());
                            if (doc instanceof AbstractAccountDocWithCurrency) {
                                ((AbstractAccountDocWithCurrency) doc)
                                        .setRate(rate);
                            }
                            break;

                        case GOODS:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            if (doc instanceof Invoice) {
                                ((Invoice) doc).setGoods(reader.getText());
                            }
                            break;

                        case AMOUNT:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            float amount = Float.parseFloat(reader.getText());
                            if (doc instanceof Invoice) {
                                ((Invoice) doc).setAmount(amount);
                            }
                            break;

                        case EMPLOYEE:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            if (doc instanceof PaymentOrder) {
                                ((PaymentOrder) doc)
                                        .setEmployee(reader.getText());
                            }
                            break;

                        case PARTNER:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            if (doc instanceof RequestForPayment) {
                                ((RequestForPayment) doc)
                                        .setPartner(reader.getText());
                            }
                            break;

                        case COMMISSION:
                            currentParseEvent = reader.next();
                            checkEvent(currentParseEvent, reader);
                            float commission = Float
                                    .parseFloat(reader.getText());
                            if (doc instanceof RequestForPayment) {
                                ((RequestForPayment) doc)
                                        .setCommission(commission);
                            }
                            break;

                        default:
                            throw new IllegalAccessException(
                                    "There is no processing for " + tagName);
                    }
                } else if (XMLStreamConstants.END_ELEMENT == currentParseEvent) {

                    String tagName = reader.getName().getLocalPart();

                    Tag tag = Tag.getTag(tagName);
                    if (tag == null) {
                        throw new IllegalAccessException(
                                "Wrong end of tag name: " + tagName);
                    }

                    if (Tag.DOCUMENT == tag) {
                        docs.add(doc);
                    }
                }
            }
            return docs;
        } catch (Exception | FactoryConfigurationError e) {
            ExeptionDialog.showDefaultDialog();
            e.printStackTrace();
        }
        return null;
    }

    private void checkAttribut(XMLStreamReader resder) {
        int attrCount = resder.getAttributeCount();
        if (attrCount > 1) {
            throw new IllegalArgumentException(
                    "Wrong attribute count int tag [%s] : %d"
                            .formatted(resder.getName(), attrCount));
        }

        String attrName = resder.getAttributeLocalName(0).toString();
        if (!Tag.TYPE.getTagName().equals(attrName)) {
            throw new IllegalArgumentException(
                    "Incorrect atribute name for tag [%s] : %s"
                            .formatted(resder.getName(), attrName));
        }
    }

    private AbstractAccountDoc createAccountDoc(XMLStreamReader reader) {
        String type = reader.getAttributeValue(0);

        AccountDocType docType = AccountDocType.getAccountDocType(type);
        if (docType == null) {
            throw new IllegalArgumentException(
                    "Wrong document type received: " + docType);
        }
        switch (docType) {
            case INVOICE:
                return new Invoice();

            case PAYMENT_ORDER:
                return new PaymentOrder();

            case REQUEST_FOR_PAYMENT:
                return new RequestForPayment();
        }
        return null;
    }

    private void checkEvent(int currentParseEvent, XMLStreamReader reader)
            throws XMLStreamException {
        if (XMLStreamConstants.CHARACTERS != currentParseEvent) {
            throw new IllegalArgumentException(
                    "Broken xml file: text area error in tag [%s]"
                            .formatted(reader.getName()));
        }
    }
}
