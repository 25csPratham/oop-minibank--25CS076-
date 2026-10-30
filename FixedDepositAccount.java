public class FixedDepositAccount extends Account {


    public FixedDepositAccount(String name, long balance) {
        super(name, balance);
    }


    public double yearlyInterest() {
        return balance * 0.07;
    }


    public boolean canWithdraw(long amount) {
        return false;
    }
}