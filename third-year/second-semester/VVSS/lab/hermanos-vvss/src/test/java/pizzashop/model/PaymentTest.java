package pizzashop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {


    @Test
    void getTableNumber() {
        Payment payment = new Payment(1, PaymentType.CASH, 12.0d);
        assertEquals(1, payment.getTableNumber());
    }

    @Test
    void setTableNumber() {
        Payment payment = new Payment(1, PaymentType.CASH, 12.0d);
        assertEquals(1, payment.getTableNumber());
        payment.setTableNumber(12);
        assertEquals(12, payment.getTableNumber());
    }
}