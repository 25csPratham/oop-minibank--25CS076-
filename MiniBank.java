public class MiniBank {

    public static void main(String[] args) {

        Account[] accounts = {

            new SavingsAccount(
                    "Rahul",
                    10000,
                    1000),

            new CurrentAccount(
                    "Priya",
                    5000,
                    3000),

            new FixedDepositAccount(
                    "Amit",
                    50000)
        };

        System.out.println("Interest Rates");

        System.out.println("---------------------");

        for (Account account : accounts) {

            System.out.println(account);

            System.out.println("Interest : "
                    + account.interestRate()
                    + "%");

            if (account instanceof SavingsAccount ) {

                System.out.println(
                        "Savings Account detected");

            }

            System.out.println();
        }

        System.out.println("Withdrawal Test");

        System.out.println("---------------------");

        for (Account account : accounts) {

            boolean result =
                    account.withdraw(2000);

            System.out.println(
                    account.getAccountNumber()
                            + " -> "
                            + result
                            + " Balance : ₹"
                            + account.getBalance());
        }

    }
}