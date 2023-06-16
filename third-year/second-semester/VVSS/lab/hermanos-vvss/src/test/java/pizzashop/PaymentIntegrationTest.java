package pizzashop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

class PaymentIntegrationTest {
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
        Payment payment = new Payment(3, PaymentType.CARD, 17.5);
        int sizeBefore = service.getPayments().size();
        paymentRepository.add(payment);
        assertEquals(sizeBefore + 1, service.getPayments().size());
        assertEquals(sizeBefore + 1, paymentRepository.getAll().size());
    }

    @Test
    void getTotalAmount() {
        Payment payment1 = new Payment(3, PaymentType.CASH, 10.0d);
        Payment payment2 = new Payment(3, PaymentType.CARD, 30.0d);
        Payment payment3 = new Payment(3, PaymentType.CASH, 20.0d);
        paymentRepository.add(payment1);
        paymentRepository.add(payment2);
        paymentRepository.add(payment3);
        assertEquals(30, service.getTotalAmount(PaymentType.CASH));
        double totalAmount = paymentRepository.getAll()
                .stream()
                .filter(p -> p.getType() == PaymentType.CASH)
                .map(Payment::getAmount)
                .reduce(0.0d, Double::sum);

        assertEquals(30, totalAmount);
    }
}
