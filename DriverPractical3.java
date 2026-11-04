public class DriverPractical3 {
    public static void main(String[] args) {
        System.out.println("=== Practical 03: Object Methods & Nested Address ===");

        Customer.Address addr = new Customer.Address("12 Green Valley", "Anand", "388001");
        Customer c1 = new Customer("Pratham Patel", "pratham@charusat.edu.in", "9988776655", addr);
        System.out.println("Customer with Nested Address: " + c1 + " | Address: " + c1.getAddress());

        Customer c1Clone = c1.clone();
        System.out.println("Cloned Customer: " + c1Clone + " (Identical: " + (c1.getCustomerId().equals(c1Clone.getCustomerId())) + ")");

        Account a1 = new SavingsAccount("User A", 4000, 1000);
        Account a2 = new SavingsAccount("User B", 4000, 1000);

        System.out.println("Account toString(): " + a1);
        System.out.println("Comparing a1 and a2 equals(): " + a1.equals(a2) + " (Distinct generated account numbers)");
        System.out.println("instanceof check: a1 is Account? " + (a1 instanceof Account) + " | a1 is Transactable? " + (a1 instanceof Transactable));
    }
}