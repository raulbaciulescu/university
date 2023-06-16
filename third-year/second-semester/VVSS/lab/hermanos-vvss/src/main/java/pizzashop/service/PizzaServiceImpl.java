package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.security.InvalidParameterException;
import java.util.List;

public class PizzaServiceImpl implements PizzaService {
    private final MenuRepository menuRepo;
    private final PaymentRepository payRepo;

    public PizzaServiceImpl(MenuRepository menuRepo, PaymentRepository payRepo) {
        this.menuRepo = menuRepo;
        this.payRepo = payRepo;
    }

    @Override
    public List<MenuDataModel> getMenuData() {
        return menuRepo.getMenu();
    }

    @Override
    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    @Override
    public void addPayment(int table, PaymentType type, double amount) {
        if (table < 1 || table > 8)
            throw new InvalidParameterException("Invalid table number!");

        if (amount <= 0 || Double.isInfinite(amount))
            throw new InvalidParameterException("Invalid amount!");

        Payment payment = new Payment(table, type, amount);
        payRepo.add(payment);
    }

    @Override
    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;                // 1
        List<Payment> l = getPayments();    // 2
        if ((l == null) || (l.isEmpty()))   // 3
            return total;                   // 4
        if(type != PaymentType.CARD && type != PaymentType.CASH) // 5
            throw new IllegalArgumentException("Unexpected type"); // 6
        for (Payment p : l) {               // 7
            if (p.getType().equals(type))   // 8
                total += p.getAmount();     // 9
        }
        return total;                       // 10
    }                                       // 11
}
