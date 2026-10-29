public class MiniBank {

    public static void main(String[] args) {

        Customer c1 = new Customer(
                "Pratham Patel",
                "pratham@gmail.com",
                "9876543210"
        );

        System.out.println("Customer Details");
        System.out.println("---------------------------");
        System.out.println("ID : " + c1.getCustomerId());
        System.out.println("Name : " + c1.getName());
        System.out.println("Email : " + c1.getEmail());
        System.out.println("Mobile : " + c1.getMobile());

        System.out.println();

        Account[] accounts = new Account[3];

        accounts[0] = new Account("Rahul", 10000);
        accounts[1] = new Account("Priya", 5000);
        accounts[2] = new Account("Amit");

        accounts[0].deposit(2000);
        accounts[0].withdraw(1500);

        accounts[1].deposit(3000);
        accounts[1].withdraw(1000);

        accounts[2].deposit(5000);
        accounts[2].withdraw(2000);

        System.out.println("Account Details");
        System.out.println("--------------------------------------");

        for (Account account : accounts) {

            System.out.println("Account Number : " + account.getAccountNumber());
            System.out.println("Owner Name     : " + account.getOwnerName());
            System.out.println("Balance        : ₹" + account.getBalance());
            System.out.println("Active         : " + account.isActive());

            System.out.println("--------------------------------------");
        }
    }
}