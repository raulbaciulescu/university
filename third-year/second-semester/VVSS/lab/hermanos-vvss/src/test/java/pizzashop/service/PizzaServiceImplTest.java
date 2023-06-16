package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.MenuRepositoryImpl;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryImpl;

import java.security.InvalidParameterException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceImplTest {
    private PizzaService service;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepo = new MenuRepositoryImpl();
        paymentRepository = new PaymentRepositoryImpl();
        service = new PizzaServiceImpl(menuRepo, paymentRepository);
    }

    @AfterEach
    void tearDown() {
        paymentRepository.deleteAll();
    }

    @RepeatedTest(1)
    @DisplayName("Valid test")
    void TC1_ECP() {
        int lengthBefore = service.getPayments().size();
        service.addPayment(1, PaymentType.CASH, 20.4);
        int lengthAfter = service.getPayments().size();
        assertEquals(lengthBefore + 1, lengthAfter);
    }

    @DisplayName("Invalid table test")
    @Test
    void TC2_ECP() {
        try {
            service.addPayment(15, PaymentType.CARD, 17.5);
            fail();
        } catch (Exception exception) {
            assert(exception.getClass() == InvalidParameterException.class);
            assert(Objects.equals(exception.getMessage(), "Invalid table number!"));
        }
    }

    @DisplayName("Invalid table test")
    @Test
    @Order(1)
    void TC3_ECP() {
        try {
            service.addPayment(-5, PaymentType.CARD, 17.5);
            fail();
        } catch (Exception exception) {
            assert(exception.getClass() == InvalidParameterException.class);
            assert(Objects.equals(exception.getMessage(), "Invalid table number!"));
        }
    }

    @DisplayName("Invalid amount test")
    @Test
    @Order(2)
    void TC4_ECP() {
        try {
            service.addPayment(6, PaymentType.CASH, -5);
            fail();
        } catch (Exception exception) {
            assert(exception.getClass() == InvalidParameterException.class);
            assert(Objects.equals(exception.getMessage(), "Invalid amount!"));
        }
    }

    @DisplayName("Valid table test")
    @Test
    void TC5_BVA() {
        int lengthBefore = service.getPayments().size();
        service.addPayment(1, PaymentType.CASH, 100.02);
        int lengthAfter = service.getPayments().size();
        assertEquals(lengthBefore + 1, lengthAfter);
    }

    @DisplayName("Invalid table test")
    @Test
    void TC6_BVA() {
        try {
            service.addPayment(0, PaymentType.CASH, 50.3);
            fail();
        } catch (Exception exception) {
            assert(exception.getClass() == InvalidParameterException.class);
            assert(Objects.equals(exception.getMessage(), "Invalid table number!"));
        }
    }

    @DisplayName("Valid amount test")
    @Test
    @Tag("development")
    void TC7_BVA() {
        int lengthBefore = service.getPayments().size();
        service.addPayment(5, PaymentType.CASH, Double.MAX_VALUE);
        int lengthAfter = service.getPayments().size();
        assertEquals(lengthBefore + 1, lengthAfter);
    }

    @DisplayName("Valid amount test")
    @Test
    @Disabled
    void TC8_BVA() {
        try {
            service.addPayment(5, PaymentType.CASH, Double.MAX_VALUE * 2);
            fail();
        } catch (Exception exception) {
            assert(exception.getClass() == InvalidParameterException.class);
            assert(Objects.equals(exception.getMessage(), "Invalid amount!"));
        }
    }
}