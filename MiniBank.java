public class MiniBank {

    public static void main(String[] args) {

        System.out.println("Mobile");

        System.out.println(
                Validator.isValidMobile("9876543210"));

        System.out.println(
                Validator.isValidMobile("12345"));



        System.out.println("\nEmail");

        System.out.println(
                Validator.isValidEmail("abc@gmail.com"));

        System.out.println(
                Validator.isValidEmail("abcgmail.com"));



        System.out.println("\nPAN");

        System.out.println(
                Validator.isValidPan("ABCDE1234F"));

        System.out.println(
                Validator.isValidPan("ABC12"));



        System.out.println("\nIFSC");

        System.out.println(
                Validator.isValidIfsc("SBIN0001234"));

        System.out.println(
                Validator.isValidIfsc("12345"));



        Command command =
                CommandParser.parse(
                        "DEPOSIT AC0001 500");

        System.out.println("\nParsed Command");

        System.out.println(command.type());

        System.out.println(command.accountNumber());

        System.out.println(command.amount());



        Account account =
                new Account("Rahul",10000);

        account.deposit(2000);

        System.out.println();

        System.out.println(
                StatementFormatter.buildStatement(account));
    }
}