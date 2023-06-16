package pizzashop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.MenuRepositoryImpl;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryImpl;
import pizzashop.service.PizzaServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PaymentRepositoryIntegrationTest {
    private PizzaServiceImpl service;
    private MenuRepository menuRepo;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        menuRepo = new MenuRepositoryImpl();
        paymentRepository = new PaymentRepositoryImpl("data/payments.txt");
        service = new PizzaServiceImpl(menuRepo, paymentRepository);
    }

    @AfterEach
    void tearDown() {
        paymentRepository.deleteAll();
    }

    @Test
    void addPayment() {
        Payment p = mock(Payment.class);
        int sizeBefore = service.getPayments().size();
        paymentRepository.add(p);
        assertEquals(sizeBefore + 1, service.getPayments().size());
    }

    @Test
    void getTotalAmount() {
        Payment p1 = mock(Payment.class);
        Payment p2 = mock(Payment.class);
        Payment p3 = mock(Payment.class);
        Mockito.when(p1.getAmount()).thenReturn(10.d);
        Mockito.when(p1.getType()).thenReturn(PaymentType.CASH);
        Mockito.when(p2.getAmount()).thenReturn(30.d);
        Mockito.when(p2.getType()).thenReturn(PaymentType.CARD);
        Mockito.when(p3.getAmount()).thenReturn(20.d);
        Mockito.when(p3.getType()).thenReturn(PaymentType.CASH);

        paymentRepository.add(p1);
        paymentRepository.add(p2);
        paymentRepository.add(p3);
        assertEquals(30, service.getTotalAmount(PaymentType.CASH));
    }
}
