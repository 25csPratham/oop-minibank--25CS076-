public abstract class Account {


    @Id
    private long accountNumber;


    @Positive
    protected long balance;


    @MaxLength(value = 20)
    protected String holderName;



    public Account(String holderName,long balance){

        this.holderName = holderName;
        this.balance = balance;

    }



    public abstract double yearlyInterest();


    public abstract boolean canWithdraw(long amount);



    @Override
    public String toString(){

        return holderName + " Balance = " + balance;

    }



    @Override
    public boolean equals(Object obj){

        if(this==obj)
            return true;

        if(obj instanceof Account){

            Account a=(Account)obj;

            return holderName.equals(a.holderName);

        }

        return false;

    }



    @Override
    public int hashCode(){

        return holderName.hashCode();

    }

}