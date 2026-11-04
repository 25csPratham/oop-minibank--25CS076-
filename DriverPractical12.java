import java.util.List;

public class DriverPractical12 {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("  Practical 12: MiniBank Collections Framework & Sorting");
        System.out.println("==========================================================");

        AccountStore store = new AccountStore();
        Account a1 = new SavingsAccount("Rohan Verma", 5000, 1000);
        Account a2 = new CurrentAccount("Sneha Roy", 2000, 1000);
        Account a3 = new FixedDepositAccount("Amitabh Sen", 9000);

        store.add(a1);
        store.add(a2);
        store.add(a3);

        System.out.println("--- 1. Map Lookup by Account Number ---");
        System.out.println("Lookup " + a2.getAccountNumber() + ": " + store.get(a2.getAccountNumber()));

        System.out.println("\n--- 2. Accounts Sorted by Balance (Highest First) ---");
        List<Account> sorted = store.sortedByBalance();
        for (Account acc : sorted) {
            System.out.println(" - " + acc);
        }

        System.out.println("\n--- 3. Natural Ordering via Comparable<Account> ---");
        System.out.println("Comparing " + a1.getAccountNumber() + " with " + a2.getAccountNumber() + ": " + a1.compareTo(a2));

        System.out.println("\n--- 4. Transaction History in ArrayList ---");
        for (String record : store.getTransactionHistory()) {
            System.out.println(" * " + record);
        }
        System.out.println("==========================================================\n");
    }
}