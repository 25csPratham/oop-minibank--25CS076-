public class DriverPractical9 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==========================================================");
        System.out.println("  Practical 09: MiniBank Multithreading & Synchronization");
        System.out.println("==========================================================");

        int numThreads = 10;
        int depositsPerThread = 1000;
        long depositAmount = 1;
        long expectedTotal = numThreads * depositsPerThread * depositAmount;

        // Account with initial balance 0
        Account testAccount = new SavingsAccount("Concurrent User", 0, 0);

        System.out.println("Initial Balance: Rs." + testAccount.getBalance());
        System.out.println("Spawning " + numThreads + " worker threads (" + depositsPerThread + " deposits of Rs." + depositAmount + " each)...");

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new AccountWorker(testAccount, depositsPerThread, depositAmount), "WorkerThread-" + (i + 1));
            System.out.println("Thread created & starting: [" + threads[i].getName() + "] State: " + threads[i].getState());
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
            System.out.println("Thread terminated: [" + t.getName() + "] State: " + t.getState());
        }

        System.out.println("----------------------------------------------------------");
        System.out.println("Expected Final Balance : Rs." + expectedTotal);
        System.out.println("Actual Final Balance   : Rs." + testAccount.getBalance());

        if (testAccount.getBalance() == expectedTotal) {
            System.out.println("Result: SUCCESS - Synchronization ensures thread safety and prevents race conditions.");
        } else {
            System.out.println("Result: RACE CONDITION DETECTED - Balance corrupted!");
        }
        System.out.println("==========================================================\n");
    }
}