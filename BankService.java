import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final Repository<Account> repository;
    private final TransactionProcessor processor;
    private final Path logFile = Paths.get("logs", "minibank_transactions.log");

    public BankService(Repository<Account> repository) {
        this.repository = repository;
        this.processor = new TransactionProcessor(4);
    }

    public Account openAccount(Account account) throws Exception {
        String[] errors = AnnotationValidator.validate(account);
        if (errors.length > 0) {
            throw new IllegalArgumentException("Validation failed: " + String.join(", ", errors));
        }
        Account saved = repository.save(account);
        processor.registerAccount(saved);
        logTransaction("OPEN " + saved.getAccountNumber() + " " + saved.getBalance());
        return saved;
    }

    public void deposit(String accountNumber, long amount) throws BankException {
        Account account = findAccountOrThrow(accountNumber);
        account.deposit(amount);
        logTransaction("DEPOSIT " + accountNumber + " " + amount);
    }

    public void withdraw(String accountNumber, long amount) throws BankException {
        Account account = findAccountOrThrow(accountNumber);
        account.withdraw(amount);
        logTransaction("WITHDRAW " + accountNumber + " " + amount);
    }

    public void transfer(String fromAcc, String toAcc, long amount) throws BankException {
        Account from = findAccountOrThrow(fromAcc);
        Account to = findAccountOrThrow(toAcc);
        from.transfer(to, amount);
        logTransaction("TRANSFER " + fromAcc + " " + amount);
    }

    public String statement(String accountNumber) throws BankException {
        Account account = findAccountOrThrow(accountNumber);
        return StatementFormatter.buildStatement(account);
    }

    public List<Account> listAccounts() {
        ArrayList<Account> list = repository.findAll();
        list.sort((a, b) -> Long.compare(b.getBalance(), a.getBalance()));
        return list;
    }

    public void processBatch(List<Runnable> batch) {
        for (Runnable r : batch) {
            processor.submit(r);
        }
    }

    public void shutdown() {
        processor.stop();
    }

    private Account findAccountOrThrow(String accountNumber) throws AccountNotFoundException {
        Account account = repository.findById(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return account;
    }

    private void logTransaction(String entry) {
        try {
            TransactionLog.append(logFile, entry);
        } catch (IOException ignored) {}
    }
}