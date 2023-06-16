package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.List;

public interface PizzaService {
    List<MenuDataModel> getMenuData();
    List<Payment> getPayments();
    void addPayment(int table, PaymentType type, double amount);
    double getTotalAmount(PaymentType type) throws Exception;
}
