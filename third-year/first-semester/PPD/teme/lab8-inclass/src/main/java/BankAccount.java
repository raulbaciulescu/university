import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    protected double amount;
    protected final Lock lock;

    public BankAccount(double amount) {
        this.amount = amount;
        lock = new ReentrantLock();
    }

    public double getAmount() {
        return amount;
    }

    public boolean deposit(double amount) {
        lock.lock();
        this.amount += amount;
        lock.unlock();
        return true;
    }

    public boolean withdraw(double sum) {
        lock.lock();
        if (amount < sum) {
            lock.unlock();
            return false;
        }
        amount -= sum;
        lock.unlock();
        return true;
    }

    public boolean transfer(BankAccount toAccount, double sum) {
        toAccount.lock.lock();
        lock.lock();
        if (amount < sum) {
            lock.unlock();
            toAccount.lock.unlock();
            return false;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        toAccount.deposit(sum);
        amount -= sum;
        lock.unlock();
        toAccount.lock.unlock();
        return true;
    }
}
