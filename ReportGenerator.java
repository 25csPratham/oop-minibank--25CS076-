import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static void generateReport(Path logsDir, Path reportFile) throws IOException {
        long totalDeposits = 0;
        long totalWithdrawals = 0;
        int totalTransactions = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(logsDir, "*.log");
             BufferedWriter writer = Files.newBufferedWriter(reportFile)) {

            writer.write("====================================================\n");
            writer.write("          MINIBANK END-OF-DAY AUDIT REPORT           \n");
            writer.write("====================================================\n");

            for (Path logFile : stream) {
                BasicFileAttributes attrs = Files.readAttributes(logFile, BasicFileAttributes.class);
                writer.write("File: " + logFile.getFileName() + " | Size: " + attrs.size() + " bytes | Modified: " + attrs.lastModifiedTime() + "\n");

                try (BufferedReader reader = Files.newBufferedReader(logFile)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split("\\s+");
                        if (parts.length >= 3) {
                            String action = parts[0].toUpperCase();
                            long amount = Long.parseLong(parts[2]);
                            totalTransactions++;

                            if (action.contains("DEPOSIT")) {
                                totalDeposits += amount;
                            } else if (action.contains("WITHDRAW") || action.contains("TRANSFER")) {
                                totalWithdrawals += amount;
                            }
                        }
                    }
                }
            }

            long netChange = totalDeposits - totalWithdrawals;
            writer.write("----------------------------------------------------\n");
            writer.write("Total Transactions Processed : " + totalTransactions + "\n");
            writer.write("Total Inflow  (Deposits)     : Rs. " + totalDeposits + "\n");
            writer.write("Total Outflow (Withdrawals)  : Rs. " + totalWithdrawals + "\n");
            writer.write("Net Liquidity Change         : Rs. " + netChange + "\n");
            writer.write("====================================================\n");
        }
        System.out.println("[ReportGenerator] Summary report written to " + reportFile.getFileName());
    }
}