public abstract class Account {

    protected String holderName;
    protected long balance;


    public Account(String holderName, long balance) {
        this.holderName = holderName;
        this.balance = balance;
    }


    public abstract double yearlyInterest();

    public abstract boolean canWithdraw(long amount);


    public String toString() {
        return holderName + " Account Balance = " + balance;
    }
}