package main.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AccountDocTypeTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "invoice",
            "payment_order",
            "request_for_payment",

            // check ignore case
            "iNvoice",
            "payMent_Order",
            "REQUEST_FOR_PAYMENT"

    })
    void existedType(String type) {
        assertNotNull(AccountDocType.getAccountDocType(type));
    }
}
