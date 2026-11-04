public class DriverPractical4 {
    public static void main(String[] args) {
        System.out.println("=== Practical 04: Regex Validation & Command Parsing ===");

        System.out.println("isValidMobile('9876543210'): " + Validator.isValidMobile("9876543210"));
        System.out.println("isValidMobile('12345'):      " + Validator.isValidMobile("12345"));
        System.out.println("isValidEmail('abc@xyz.com'): " + Validator.isValidEmail("abc@xyz.com"));
        System.out.println("isValidEmail('invalid-mail'):" + Validator.isValidEmail("invalid-mail"));
        System.out.println("isValidPan('ABCDE1234F'):    " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("isValidIfsc('HDFC0001234'):  " + Validator.isValidIfsc("HDFC0001234"));

        System.out.println("\nCommand Parsing Demonstration:");
        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Parsed Command: Type=" + cmd.type() + ", Account=" + cmd.accountNumber() + ", Amount=" + cmd.amount());

        System.out.println("\nStatement Formatter Demonstration:");
        Account acc = new SavingsAccount("Pratham Patel", 8500, 1000);
        System.out.println(StatementFormatter.buildStatement(acc));
    }
}