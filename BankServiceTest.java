public class BankServiceTest {
    public static void main(String[] args) {
        System.out.println("=== Running Unit Test Suite for BankService ===");
        int passed = 0;
        int failed = 0;

        // Test 1: Deposit increases balance
        try {
            Repository<Account> repo = new Repository<>();
            BankService service = new BankService(repo);
            Account acc = service.openAccount(new SavingsAccount("Test User 1", 5000, 1000));
            service.deposit(acc.getAccountNumber(), 2500);

            if (acc.getBalance() == 7500) {
                System.out.println("[PASS] testDepositIncreasesBalance: 5000 + 2500 = 7500");
                passed++;
            } else {
                System.err.println("[FAIL] testDepositIncreasesBalance: Expected 7500, got " + acc.getBalance());
                failed++;
            }
            service.shutdown();
        } catch (Exception e) {
            System.err.println("[FAIL] testDepositIncreasesBalance exception: " + e.getMessage());
            failed++;
        }

        // Test 2: Withdrawal over limit throws InsufficientFundsException
        try {
            Repository<Account> repo = new Repository<>();
            BankService service = new BankService(repo);
            Account acc = service.openAccount(new SavingsAccount("Test User 2", 2000, 1000));
            try {
                service.withdraw(acc.getAccountNumber(), 5000);
                System.err.println("[FAIL] testWithdrawalOverLimit: Exception not thrown");
                failed++;
            } catch (InsufficientFundsException e) {
                System.out.println("[PASS] testWithdrawalOverLimitThrowsException: Caught " + e.getMessage());
                passed++;
            }
            service.shutdown();
        } catch (Exception e) {
            System.err.println("[FAIL] testWithdrawalOverLimit unexpected: " + e.getMessage());
            failed++;
        }

        // Test 3: Concurrent deposits maintain thread-safe balance
        try {
            Repository<Account> repo = new Repository<>();
            BankService service = new BankService(repo);
            Account acc = service.openAccount(new SavingsAccount("Concurrent User", 1000, 0));

            int threads = 10;
            int depositsPerThread = 500;
            long amount = 2;
            Thread[] workerThreads = new Thread[threads];

            for (int i = 0; i < threads; i++) {
                workerThreads[i] = new Thread(() -> {
                    for (int j = 0; j < depositsPerThread; j++) {
                        try {
                            service.deposit(acc.getAccountNumber(), amount);
                        } catch (Exception ignored) {}
                    }
                });
                workerThreads[i].start();
            }

            for (Thread t : workerThreads) t.join();

            long expected = 1000 + (threads * depositsPerThread * amount);
            if (acc.getBalance() == expected) {
                System.out.println("[PASS] testConcurrentDeposits: Final balance exact: Rs." + expected);
                passed++;
            } else {
                System.err.println("[FAIL] testConcurrentDeposits: Expected " + expected + ", got " + acc.getBalance());
                failed++;
            }
            service.shutdown();
        } catch (Exception e) {
            System.err.println("[FAIL] testConcurrentDeposits exception: " + e.getMessage());
            failed++;
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Tests Run: " + (passed + failed) + " | Passed: " + passed + " | Failed: " + failed);
        System.out.println("===============================================\n");
    }
}