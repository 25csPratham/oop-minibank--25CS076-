import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Transactable, InterestBearing, Serializable, Comparable<Account> {
    private static final long serialVersionUID = 1L;
    private static long accountCounter = 1;

    @Id
    protected final String accountNumber;

    protected String ownerName;

    @Positive(message = "must be > 0")
    protected long balance;

    protected boolean active;

    private static synchronized String generateAccountNumber() {
        return String.format("AC%04d", accountCounter++);
    }

    public Account(String ownerName, long balance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive: " + amount);
        }
        balance += amount;
    }

    @Override
    public synchronized void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive: " + amount);
        }
        if (!canWithdraw(amount)) {
            long shortfall = amount - balance;
            throw new InsufficientFundsException(shortfall > 0 ? shortfall : amount);
        }
        balance -= amount;
    }

    public synchronized void transfer(Account to, long amount) throws BankException {
        if (to == null) {
            throw new BankException("Destination account cannot be null");
        }
        try {
            this.withdraw(amount);
            to.deposit(amount);
            System.out.println("Transfer successful: Rs." + amount + " moved from " + this.accountNumber + " to " + to.getAccountNumber());
        } catch (BankException e) {
            throw e;
        } finally {
            System.out.println("Transfer operation completed.");
        }
    }

    public abstract boolean canWithdraw(long amount);

    @Override
    public abstract double interestRate();

    @Override
    public double yearlyInterest() {
        return (balance * interestRate()) / 100.0;
    }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | Rs." + balance + (active ? " (Active)" : " (Inactive)");
    }
}