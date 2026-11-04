public class DriverPractical5 {
    public static void main(String[] args) {
        System.out.println("=== Practical 05: Account Hierarchy & Polymorphism ===");

        Account[] accounts = {
            new SavingsAccount("Rahul Verma", 10000, 1000),
            new CurrentAccount("Priya Sharma", 5000, 3000),
            new FixedDepositAccount("Sanjay Patel", 50000)
        };

        System.out.println("Polymorphic Interest Rates:");
        for (Account a : accounts) {
            System.out.printf(" - %-22s : Type=%-20s Rate=%.1f%% Annual Interest=Rs.%.2f\n",
                    a.getOwnerName(), a.getClass().getSimpleName(), a.interestRate(), a.yearlyInterest());

            // Pattern matching instanceof
            if (a instanceof CurrentAccount ca) {
                System.out.println("   [Note]: Overdraft facility available up to Rs." + ca.getOverdraftLimit());
            }
        }
    }
}