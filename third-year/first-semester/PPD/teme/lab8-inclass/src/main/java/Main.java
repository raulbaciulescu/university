

public class Main {
    public static void main(String[] args) {
//        BankAccount firstBank = new BankAccount(100000.0);
//        BankAccount secondBank = new BankAccount(1000000.0);

        BankAccountSync firstBank = new BankAccountSync(100000.0);
        BankAccountSync secondBank = new BankAccountSync(1000000.0);

        Thread t1 = new Thread(() -> firstBank.withdraw(50));
        Thread t2 = new Thread(() -> firstBank.deposit(1000));
        Thread t3 = new Thread(() -> firstBank.transfer(secondBank, 10));
        Thread t4 = new Thread(() -> secondBank.withdraw(50));
        Thread t5 = new Thread(() -> secondBank.transfer(firstBank, 100));
        Thread t6 = new Thread(() -> secondBank.transfer2(firstBank, 100));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("first bank " + firstBank.getAmount());
        System.out.println("second bank " + secondBank.getAmount());
    }
}
