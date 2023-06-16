package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.quality.Strictness;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PizzaServiceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryImplTest {
    @InjectMocks
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepositoryImpl("data/payments.txt");
        paymentRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        paymentRepository.deleteAll();
    }

    @Test
    void add() {
        Payment p = mock(Payment.class);
        assertEquals(0, paymentRepository.getAll().size());
        paymentRepository.add(p);
        Mockito.when(p.toString()).thenReturn("1,CASH,20.4");
        assertEquals(1, paymentRepository.getAll().size());
    }

    @Test
    void deleteAll() {
        Payment p = mock(Payment.class);
        assertEquals(0, paymentRepository.getAll().size());
        paymentRepository.add(p);
        assertEquals(1, paymentRepository.getAll().size());
        paymentRepository.deleteAll();
        assertEquals(0, paymentRepository.getAll().size());
    }

    @Test
    void d() {
        Payment p = mock(Payment.class);
        Mockito.when(p.getTableNumber()).thenReturn(12);
        System.out.println(p.getTableNumber());
    }
}