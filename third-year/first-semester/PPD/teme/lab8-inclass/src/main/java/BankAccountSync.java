import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountSync {
    protected double amount;
    protected final Lock lock;

    public BankAccountSync(double amount) {
        this.amount = amount;
        lock = new ReentrantLock();
    }

    public synchronized double getAmount() {
        return amount;
    }

    public synchronized boolean deposit(double amount) {
        this.amount += amount;
        return true;
    }

    public synchronized boolean withdraw(double sum) {
        if (amount < sum)
            return false;
        amount -= sum;
        return true;
    }

    public boolean transfer(BankAccountSync toAccount, double sum) {
        synchronized (this){
            synchronized (toAccount) {
                if (amount < sum)
                    return false;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                toAccount.deposit(sum);
                amount -= sum;
                return true;
            }
        }
    }

    public boolean transfer2(BankAccountSync toAccount, double sum) {
        synchronized (toAccount){
            synchronized (this) {
                if (amount < sum)
                    return false;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                toAccount.deposit(sum);
                amount -= sum;
                return true;
            }
        }
    }
}
