public class CurrentAccount extends Account {

    private long overdraftLimit;


    public CurrentAccount(String name, long balance, long overdraftLimit) {
        super(name, balance);
        this.overdraftLimit = overdraftLimit;
    }


    public double yearlyInterest() {
        return balance * 0.02;
    }


    public boolean canWithdraw(long amount) {
        return amount <= balance + overdraftLimit;
    }
}