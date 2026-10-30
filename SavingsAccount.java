public class SavingsAccount extends Account {

    private long minimumBalance;


    public SavingsAccount(String name, long balance, long minimumBalance) {
        super(name, balance);
        this.minimumBalance = minimumBalance;
    }


    public double yearlyInterest() {
        return balance * 0.04;
    }


    public boolean canWithdraw(long amount) {
        return (balance - amount) >= minimumBalance;
    }
}