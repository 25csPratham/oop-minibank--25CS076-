public class MiniBankResource implements AutoCloseable {
    public MiniBankResource() {
        System.out.println("Resource opened: Connection initialized.");
    }

    public void open() {
        System.out.println("Active banking resource in use.");
    }

    @Override
    public void close() {
        System.out.println("Resource closed: Connection securely released.");
    }
}