import java.util.LinkedList;
import java.util.Queue;

public class TransactionBuffer {
    private final Queue<Command> buffer = new LinkedList<>();
    private final int capacity;

    public TransactionBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(Command cmd) throws InterruptedException {
        while (buffer.size() == capacity) {
            System.out.println("[Buffer Full] Producer waiting...");
            wait();
        }
        buffer.add(cmd);
        System.out.println("[Produced] " + cmd.type() + " on " + cmd.accountNumber() + " Rs." + cmd.amount());
        notifyAll();
    }

    public synchronized Command consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("[Buffer Empty] Consumer waiting...");
            wait();
        }
        Command cmd = buffer.poll();
        System.out.println("[Consumed] Processing " + cmd.type() + " on " + cmd.accountNumber() + " Rs." + cmd.amount());
        notifyAll();
        return cmd;
    }
}