public class DriverPractical8 {
    public static void main(String[] args) {
        System.out.println("=== Practical 08: Custom Exception Hierarchy & Fault Tolerance ===");

        Account rahul = new SavingsAccount("Rahul", 10000, 1000);
        Account priya = new CurrentAccount("Priya", 5000, 3000);

        // Test 1: Insufficient funds exception with shortfall
        try {
            System.out.println("Attempting withdrawal of Rs.15000 from Rahul (Balance: Rs.10000)...");
            rahul.withdraw(15000);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught Expected Error: " + e.getMessage() + " | Shortfall = Rs." + e.getShortfall());
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Withdrawal attempt finished.");
        }

        // Test 2: Transfer with try-catch-finally
        try {
            System.out.println("\nTransferring Rs.2000 from Rahul to Priya...");
            rahul.transfer(priya, 2000);
            System.out.println("Transfer successful!");
        } catch (BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        } finally {
            System.out.println("Transfer process ended.");
        }

        // Test 3: AutoCloseable try-with-resources
        System.out.println("\nTesting AutoCloseable MiniBankResource:");
        try (MiniBankResource r = new MiniBankResource()) {
            r.open();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}