import java.util.*;

public class AccountStore {
    private final Map<String, Account> accounts = new HashMap<>();
    private final List<String> transactionHistory = new ArrayList<>();

    public void add(Account account) {
        if (account != null) {
            accounts.put(account.getAccountNumber(), account);
            transactionHistory.add("Added account: " + account.getAccountNumber() + " (" + account.getOwnerName() + ")");
        }
    }

    public Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public Collection<Account> getAll() {
        return accounts.values();
    }

    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public void logTransaction(String entry) {
        transactionHistory.add(entry);
    }

    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> list = new ArrayList<>(accounts.values());
        list.sort(new Comparator<Account>() {
            @Override
            public int compare(Account a1, Account a2) {
                // Descending by balance; tie-break by account number
                int cmp = Long.compare(a2.getBalance(), a1.getBalance());
                if (cmp == 0) {
                    return a1.getAccountNumber().compareTo(a2.getAccountNumber());
                }
                return cmp;
            }
        });
        return list;
    }
}