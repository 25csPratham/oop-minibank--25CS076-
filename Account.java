public abstract class Account {


    protected String holderName;

    protected long balance;



    public Account(String holderName,long balance){

        this.holderName = holderName;
        this.balance = balance;

    }



    public void withdraw(long amount)
    throws InsufficientFundsException, InvalidAmountException {


        if(amount <= 0){

            throw new InvalidAmountException(
                "Withdrawal amount must be positive"
            );

        }


        if(amount > balance){


            long shortfall = amount - balance;


            throw new InsufficientFundsException(shortfall);

        }


        balance -= amount;

    }



    public void deposit(long amount)
    throws InvalidAmountException {


        if(amount <= 0){

            throw new InvalidAmountException(
                "Deposit amount must be positive"
            );

        }


        balance += amount;

    }




    public void transfer(Account to,long amount)
    throws BankException {


        try{


            this.withdraw(amount);

            to.deposit(amount);


        }
        catch(BankException e){


            throw e;

        }
        finally{


            System.out.println("Transfer operation completed");

        }


    }



    public abstract double yearlyInterest();



    public String toString(){

        return holderName +
        " Balance = " + balance;

    }

}