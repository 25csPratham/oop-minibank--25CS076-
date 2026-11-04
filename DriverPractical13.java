import java.util.ArrayList;

public class DriverPractical13 {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("  Practical 13: MiniBank Generic Repository & Reflection");
        System.out.println("==========================================================");

        // 1. Generic Repository for Account
        System.out.println("--- 1. Generic Repository<Account> ---");
        Repository<Account> accountRepo = new Repository<>();
        Account a1 = new SavingsAccount("Manish Rawat", 12000, 1500);
        Account a2 = new CurrentAccount("Kavita Jain", 30000, 5000);
        Account a3 = new FixedDepositAccount("Deepak Gupta", 75000);

        accountRepo.saveAll(a1, a2, a3);
        System.out.println("Total saved accounts: " + accountRepo.count());
        System.out.println("Finding account by ID (" + a2.getAccountNumber() + "): " + accountRepo.findById(a2.getAccountNumber()));

        ArrayList<Account> allAccounts = accountRepo.findAll();
        for (Account acc : allAccounts) {
            System.out.println(" - " + acc);
        }

        // 2. Generic Repository for Customer
        System.out.println("\n--- 2. Reusing Repository<Customer> for Different Type ---");
        Repository<Customer> customerRepo = new Repository<>();
        Customer c1 = new Customer("Manish Rawat", "manish@gmail.com", "9876543210");
        Customer c2 = new Customer("Kavita Jain", "kavita@yahoo.com", "9123456789");
        customerRepo.save(c1);
        customerRepo.save(c2);

        System.out.println("Total saved customers: " + customerRepo.count());
        System.out.println("Found customer (" + c1.getCustomerId() + "): " + customerRepo.findById(c1.getCustomerId()));

        // 3. Generic Bounded Method: average()
        System.out.println("\n--- 3. Bounded Generic Method: average() ---");
        Long[] balances = {a1.getBalance(), a2.getBalance(), a3.getBalance()};
        System.out.printf("Average Account Balance: Rs. %.2f\n", Repository.average(balances));
        System.out.println("==========================================================\n");
    }
}