import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        BankInfo bankInfo = new BankInfo("Charusat Apex Bank", "Changa Branch (CSPIT)");
        System.out.println("==========================================================");
        System.out.println("     " + bankInfo.name() + " - " + bankInfo.branch());
        System.out.println("==========================================================");

        Repository<Account> repo = new Repository<>();
        BankService service = new BankService(repo);

        // Prepopulate sample accounts
        try {
            service.openAccount(new SavingsAccount("Pratham Patel", 25000, 2000));
            service.openAccount(new CurrentAccount("Apex Corp", 50000, 10000));
            service.openAccount(new FixedDepositAccount("Trust Fund", 100000));
        } catch (Exception e) {
            System.err.println("Setup error: " + e.getMessage());
        }

        System.out.println("MiniBank Engine initialized with " + repo.count() + " accounts.");
        System.out.println("\n--- Demonstration of MiniBank Menu Actions ---");

        // Execute menu operations programmatically for clean demonstration
        try {
            System.out.println("\n[Action 1]: Listing accounts sorted by balance:");
            for (Account a : service.listAccounts()) {
                System.out.println(" * " + a);
            }

            System.out.println("\n[Action 2]: Depositing Rs.5000 to AC0001...");
            service.deposit("AC0001", 5000);

            System.out.println("\n[Action 3]: Withdrawing Rs.3000 from AC0001...");
            service.withdraw("AC0001", 3000);

            System.out.println("\n[Action 4]: Transferring Rs.4000 from AC0001 to AC0002...");
            service.transfer("AC0001", "AC0002", 4000);

            System.out.println("\n[Action 5]: Generating Statement for AC0001:");
            System.out.println(service.statement("AC0001"));

            System.out.println("\n[Action 6]: Testing Fault Tolerance (Withdrawal exceeding limit):");
            try {
                service.withdraw("AC0001", 999999);
            } catch (InsufficientFundsException e) {
                System.out.println("Handled Expected Error: " + e.getMessage());
            }

            System.out.println("\n[Action 7]: Saving System State to Disk on Exit...");
            Path persistFile = Paths.get("minibank_state.dat");
            StatePersister.save(repo.findAll().toArray(new Account[0]), persistFile);

            System.out.println("\nThank you for using " + bankInfo.name() + ". Goodbye!");
        } catch (Exception e) {
            System.err.println("Execution error: " + e.getMessage());
        } finally {
            service.shutdown();
        }
    }
}