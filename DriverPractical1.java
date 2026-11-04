public class DriverPractical1 {
    public static void main(String[] args) {
        BankInfo info = new BankInfo("Charusat Apex Bank", "Changa Branch");
        System.out.println("==================================================");
        System.out.println("Application: " + info.name() + " (" + info.branch() + ")");
        System.out.println("==================================================");

        System.out.println("--- MiniBank Available Menu Options ---");
        for (MenuOption opt : MenuOption.values()) {
            System.out.println((opt.ordinal() + 1) + ". " + opt);
        }

        // Simulating menu selection via switch expression
        int choice = 2; // Deposit
        String action = switch (choice) {
            case 1 -> "Open Account - to be implemented in a later lab";
            case 2 -> "Deposit - to be implemented in a later lab";
            case 3 -> "Withdraw - to be implemented in a later lab";
            case 4 -> "Transfer - to be implemented in a later lab";
            case 5 -> "Exiting MiniBank application. Goodbye!";
            default -> "Invalid option selected.";
        };
        System.out.println("\nUser selected option " + choice + ": " + action);
    }
}