package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepositoryImpl implements PaymentRepository {
    private String filename = "data/payments.txt";
    private final List<Payment> paymentList;

    public PaymentRepositoryImpl(String filename) {
        this.filename = filename;
        this.paymentList = new ArrayList<>();
        readPayments();
    }

    public PaymentRepositoryImpl() {
        this.paymentList = new ArrayList<>();
        readPayments();
    }

    @Override
    public void add(Payment payment) {
        paymentList.add(payment);
        writeAll();
    }

    @Override
    public void deleteAll() {
        paymentList.clear();
        File file = new File(filename);
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> getAll() {
        return paymentList;
    }

    @Override
    public void writeAll() {
        File file = new File(filename);

        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p : paymentList) {
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readPayments() {
        File file = new File(filename);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                Payment payment = getPayment(line);
                paymentList.add(payment);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line) {
        Payment item;
        if (line == null || line.equals("")) return null;
        StringTokenizer st = new StringTokenizer(line, ",");
        int tableNumber = Integer.parseInt(st.nextToken());
        String type = st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }
}
