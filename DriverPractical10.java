public class DriverPractical10 {
    public static void safeTransfer(Account from, Account to, long amount) throws BankException {
        // Consistent Lock Ordering: Always lock the account with the smaller accountNumber first
        Account firstLock = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? from : to;
        Account secondLock = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? to : from;

        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + " locked first account: " + firstLock.getAccountNumber());
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}

            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + " locked second account: " + secondLock.getAccountNumber());
                from.withdraw(amount);
                to.deposit(amount);
                System.out.println(Thread.currentThread().getName() + " successfully transferred Rs." + amount + " from " + from.getAccountNumber() + " to " + to.getAccountNumber());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==========================================================");
        System.out.println("  Practical 10: MiniBank Transaction Engine & Concurrency");
        System.out.println("==========================================================");

        // 1. Thread Pool Transaction Processing
        System.out.println("--- 1. ExecutorService Fixed Thread Pool (4 Workers) ---");
        TransactionProcessor processor = new TransactionProcessor(4);
        Account acc1 = new SavingsAccount("User A", 10000, 1000);
        Account acc2 = new CurrentAccount("User B", 5000, 2000);
        processor.registerAccount(acc1);
        processor.registerAccount(acc2);

        for (int i = 1; i <= 6; i++) {
            final int txId = i;
            processor.submit(() -> {
                String thread = Thread.currentThread().getName();
                try {
                    acc1.deposit(100);
                    System.out.println("Tx #" + txId + " executed deposit of Rs.100 on " + acc1.getAccountNumber() + " by [" + thread + "]");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            });
        }
        processor.stop();
        System.out.println("Thread pool completed batch tasks. Acc1 Balance: Rs." + acc1.getBalance());

        // 2. Producer-Consumer Buffer Coordination
        System.out.println("\n--- 2. Inter-Thread Producer-Consumer Buffer ---");
        TransactionBuffer buffer = new TransactionBuffer(2);
        Thread producer = new Thread(() -> {
            try {
                buffer.produce(new Command(TransactionType.DEPOSIT, acc1.getAccountNumber(), 500));
                buffer.produce(new Command(TransactionType.WITHDRAW, acc2.getAccountNumber(), 300));
                buffer.produce(new Command(TransactionType.TRANSFER, acc1.getAccountNumber(), 1000));
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    buffer.consume();
                    Thread.sleep(80);
                }
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        // 3. Deadlock Prevention Demonstration
        System.out.println("\n--- 3. Deadlock Prevention with Consistent Lock Ordering ---");
        Thread t1 = new Thread(() -> {
            try {
                safeTransfer(acc1, acc2, 1000);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }, "TransferThread-1 (A->B)");

        Thread t2 = new Thread(() -> {
            try {
                safeTransfer(acc2, acc1, 500);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }, "TransferThread-2 (B->A)");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Both transfers completed smoothly without deadlock!");
        System.out.println("==========================================================\n");
    }
}