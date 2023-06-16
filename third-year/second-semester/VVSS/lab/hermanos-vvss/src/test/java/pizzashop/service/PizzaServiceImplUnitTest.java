package pizzashop.service;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class PizzaServiceImplUnitTest {
    @InjectMocks
    private PizzaServiceImpl service;
    @Mock
    private MenuRepository menuRepo;
    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addPayment() {
        Payment payment = new Payment(3, PaymentType.CARD, 17.5);
        assertEquals(0, service.getPayments().size());
        Mockito.when(paymentRepository.getAll()).thenReturn(List.of(payment));
        service.addPayment(3, PaymentType.CARD, 17.5);
        assertEquals(1, service.getPayments().size());
    }

    @Test
    void getTotalAmount() {
        Payment payment1 = new Payment(3, PaymentType.CASH, 10.0d);
        Payment payment2 = new Payment(3, PaymentType.CARD, 30.0d);
        Payment payment3 = new Payment(3, PaymentType.CASH, 20.0d);
        Mockito.when(paymentRepository.getAll()).thenReturn(List.of(payment1, payment2, payment3));
        assertEquals(30, service.getTotalAmount(PaymentType.CASH));
    }
}