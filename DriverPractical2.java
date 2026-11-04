public class DriverPractical2 {
    public static void main(String[] args) {
        System.out.println("=== Practical 02: Customer & Account Encapsulation ===");

        Customer cust1 = new Customer("Riya Patel", "riya@charusat.edu.in", "9876543210");
        Customer cust2 = new Customer("Arjun Shah", "arjun@charusat.edu.in", "9123456780");
        System.out.println("Created: " + cust1);
        System.out.println("Created: " + cust2);

        Account[] accounts = {
            new SavingsAccount("Riya Patel", 5000, 1000),
            new CurrentAccount("Arjun Shah", 10000, 3000),
            new FixedDepositAccount("Suresh Patel", 25000)
        };

        System.out.println("\nInitial Account Details:");
        for (Account a : accounts) {
            System.out.println("Account [" + a.getAccountNumber() + "] Owner: " + a.getOwnerName() + " | Balance: Rs." + a.getBalance());
        }

        System.out.println("\nTesting Deposit and Withdraw Operations on " + accounts[0].getAccountNumber() + ":");
        try {
            accounts[0].deposit(2000);
            System.out.println("After deposit of Rs.2000: Rs." + accounts[0].getBalance());
            accounts[0].withdraw(3000);
            System.out.println("After withdrawal of Rs.3000: Rs." + accounts[0].getBalance());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}