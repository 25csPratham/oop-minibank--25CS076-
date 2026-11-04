public class DriverPractical7 {
    public static void main(String[] args) {
        System.out.println("=== Practical 07: Metadata, Custom Annotations & Reflection ===");

        Account validAccount = new SavingsAccount("Valid User", 10000, 1000);
        String[] validErrors = AnnotationValidator.validate(validAccount);
        System.out.println("Validating valid account: Errors found = " + validErrors.length);

        // Create account with invalid (negative) balance to test @Positive reflection check
        Account invalidAccount = new SavingsAccount("Invalid User", -500, 1000);
        String[] errors = AnnotationValidator.validate(invalidAccount);
        System.out.println("Validating account with negative balance (-500):");
        for (String err : errors) {
            System.out.println(" [Annotation Violation]: " + err);
        }
    }
}