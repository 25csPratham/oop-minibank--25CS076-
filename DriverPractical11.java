import java.nio.file.*;
import java.util.List;

public class DriverPractical11 {
    public static void main(String[] args) throws Exception {
        System.out.println("==========================================================");
        System.out.println("  Practical 11: MiniBank Persistence & NIO.2 Audit Reports");
        System.out.println("==========================================================");

        // 1. Serialization of Accounts
        Path dataFile = Paths.get("minibank_accounts.dat");
        Account[] accounts = {
            new SavingsAccount("Aarav Sharma", 15000, 2000),
            new CurrentAccount("Diya Patel", 25000, 5000),
            new FixedDepositAccount("Vikram Mehta", 50000)
        };

        System.out.println("--- 1. Persisting Accounts via ObjectOutputStream ---");
        StatePersister.save(accounts, dataFile);

        System.out.println("\n--- 2. Reloading Accounts via ObjectInputStream ---");
        Account[] reloaded = StatePersister.load(dataFile);
        for (Account acc : reloaded) {
            System.out.println(" - Loaded: " + acc);
        }

        // 2. Transaction Audit Logging via NIO
        System.out.println("\n--- 3. Appending to NIO Transaction Logs ---");
        Path logsDir = Paths.get("logs");
        if (!Files.exists(logsDir)) Files.createDirectories(logsDir);

        Path log1 = logsDir.resolve("batch_1.log");
        Path log2 = logsDir.resolve("batch_2.log");

        TransactionLog.append(log1, "DEPOSIT AC0001 5000");
        TransactionLog.append(log1, "WITHDRAW AC0002 2000");
        TransactionLog.append(log2, "DEPOSIT AC0002 3000");
        TransactionLog.append(log2, "WITHDRAW AC0001 1000");
        System.out.println("Transaction entries written to logs/batch_1.log and logs/batch_2.log");

        // 3. End-of-Day Summary Report via NIO DirectoryStream
        System.out.println("\n--- 4. Generating End-of-Day Report ---");
        Path reportFile = Paths.get("end_of_day_report.txt");
        ReportGenerator.generateReport(logsDir, reportFile);

        List<String> reportLines = Files.readAllLines(reportFile);
        for (String line : reportLines) {
            System.out.println(line);
        }
        System.out.println("==========================================================\n");
    }
}