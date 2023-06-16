package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.MenuRepositoryImpl;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryImpl;

import java.security.InvalidParameterException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GetTotalAmountTest {
    private PizzaService service;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepo = new MenuRepositoryImpl();
        paymentRepository = new PaymentRepositoryImpl("data/payments.txt");
        service = new PizzaServiceImpl(menuRepo, paymentRepository);
    }

    @AfterEach
    void tearDown() {
        paymentRepository.deleteAll();
    }

    @Test
    void WBT_TC1() throws Exception {
        assertEquals(0.0, service.getTotalAmount(PaymentType.CASH));
    }

    @Test
    void WBT_TC2() {
        service.addPayment(1, PaymentType.CEC, 50d);
        try {
            service.getTotalAmount(PaymentType.CEC);
            fail();
        } catch (Exception exception) {
            assert(exception.getClass() == IllegalArgumentException.class);
            assert(Objects.equals(exception.getMessage(), "Unexpected type"));
        }
    }

    @Test
    void WBT_TC4() throws Exception {
        service.addPayment(1, PaymentType.CASH, 50.5d);
        assertEquals(0.0, service.getTotalAmount(PaymentType.CARD));
    }

    @Test
    void WBT_TC5() throws Exception {
        service.addPayment(1, PaymentType.CASH, 50.5d);
        assertEquals(50.5d, service.getTotalAmount(PaymentType.CASH));
    }
}