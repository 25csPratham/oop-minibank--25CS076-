public class Main {

    public static void main(String args[]) {


        Account[] accounts = {

            new SavingsAccount("Rahul",10000,1000),

            new CurrentAccount("Priya",5000,3000),

            new FixedDepositAccount("Amit",50000)

        };


        for(Account account : accounts) {

            System.out.println(account);

            System.out.println(
                "Interest = " + account.yearlyInterest()
            );

            System.out.println();

        }



        WithdrawRule rule1 = new WithdrawRule() {

            public boolean allow(Account account,long amount) {

                return account.canWithdraw(amount);

            }

        };


        System.out.println(
            "Rahul Withdraw 500 : "
            + rule1.allow(accounts[0],500)
        );



        WithdrawRule rule2 =
            (account,amount) -> account.canWithdraw(amount);



        System.out.println(
            "Priya Withdraw 1000 : "
            + rule2.allow(accounts[1],1000)
        );

    }
}