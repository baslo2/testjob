package main.file.xml;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import main.Storage;
import main.model.AbstractAccountDoc;
import main.model.Invoice;
import main.model.PaymentOrder;
import main.model.RequestForPayment;

public class AbstrectFileTest {

    private static final ArrayList<AbstractAccountDoc> docs = new ArrayList<>();

    protected Path getFilePath() throws URISyntaxException {
        return Paths.get(getClass().getResource("test.xml").toURI())
                .toAbsolutePath();
    }

    @BeforeAll
    static void fillStorage() {

        Date date = new Date(122, 9, 12);

        Invoice invoice = new Invoice();
        invoice.setNumber("НК15");
        invoice.setDate(date);
        invoice.setUser("Васильев");
        invoice.setSumm((float) 1_437.74);
        invoice.setCurrency("USD");
        invoice.setRate((float) 54.17);
        invoice.setGoods("товар");
        invoice.setAmount((float) 74.12);

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setNumber("178");
        paymentOrder.setDate(date);
        paymentOrder.setUser("Галиев");
        paymentOrder.setSumm((float) 57_789.15);
        paymentOrder.setEmployee("Яровая");

        RequestForPayment forPayment = new RequestForPayment();
        forPayment.setNumber("НК15");
        forPayment.setDate(date);
        forPayment.setUser("Васильев");
        forPayment.setSumm((float) 1_437.74);
        forPayment.setCurrency("USD");
        forPayment.setRate((float) 54.17);
        forPayment.setPartner("Антошин");
        forPayment.setCommission((float) 150.00);

        docs.add(invoice);
        docs.add(paymentOrder);
        docs.add(forPayment);

        Storage.INSTANCE.getAll().add(invoice);
        Storage.INSTANCE.getAll().add(paymentOrder);
        Storage.INSTANCE.getAll().add(forPayment);
    }

    @AfterAll
    static void cleanStorage() {
        Storage.INSTANCE.getAll().clear();
    }
}
