package pizzashop.repository;

import pizzashop.model.Payment;

import java.util.List;

public interface PaymentRepository {
    void add(Payment payment);

    void deleteAll();

    List<Payment> getAll();
    void writeAll();
}
