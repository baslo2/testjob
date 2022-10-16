package main.file.xml;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
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
        Date date = Date.from(LocalDate.of(2022, 10, 12)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        Invoice invoice = new Invoice();
        invoice.setNumber("NK15");
        invoice.setDate(date);
        invoice.setUser("Vasiliev");
        invoice.setSumm((float) 1_437.74);
        invoice.setCurrency("USD");
        invoice.setRate((float) 54.17);
        invoice.setGoods("good");
        invoice.setAmount((float) 74.12);

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setNumber("178");
        paymentOrder.setDate(date);
        paymentOrder.setUser("Galiev");
        paymentOrder.setSumm((float) 57_789.15);
        paymentOrder.setEmployee("Yarovay");

        RequestForPayment forPayment = new RequestForPayment();
        forPayment.setNumber("NK15");
        forPayment.setDate(date);
        forPayment.setUser("Vasiliev");
        forPayment.setSumm((float) 1_437.74);
        forPayment.setCurrency("USD");
        forPayment.setRate((float) 54.17);
        forPayment.setPartner("Antoshin");
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
