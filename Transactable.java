public interface Transactable {
    void deposit(long amount) throws InvalidAmountException;
    void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException;
}