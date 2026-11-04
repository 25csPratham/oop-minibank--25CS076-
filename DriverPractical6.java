public class DriverPractical6 {
    public static void main(String[] args) {
        System.out.println("=== Practical 06: Interfaces & Functional Interfaces ===");

        Account acc = new SavingsAccount("Aman Gupta", 15000, 2000);

        // 1. Transactable interface
        Transactable t = acc;
        try {
            t.deposit(3000);
            System.out.println("Transactable deposit executed. Balance: Rs." + acc.getBalance());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // 2. InterestBearing interface default method
        InterestBearing ib = acc;
        System.out.println("InterestBearing yearlyInterest(): Rs." + ib.yearlyInterest());

        // 3. Functional Interface WithdrawRule with Anonymous Class
        WithdrawRule anonRule = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return amount <= 5000 && account.getBalance() >= amount;
            }
        };

        // 4. Functional Interface WithdrawRule with Lambda
        WithdrawRule lambdaRule = (account, amount) -> amount <= 5000 && account.getBalance() >= amount;

        System.out.println("WithdrawRule (Anonymous) allow(Rs.4000): " + anonRule.allow(acc, 4000));
        System.out.println("WithdrawRule (Lambda)    allow(Rs.8000): " + lambdaRule.allow(acc, 8000));
        System.out.println("Premium interface check: (acc instanceof Premium) = " + (acc instanceof Premium));
    }
}