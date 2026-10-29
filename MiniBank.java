import java.util.Scanner;

public class MiniBank {

    record BankInfo(String name, String branch) {}

    enum MenuOption {
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXIT
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo(
                "MiniBank",
                "Main Branch"
        );

        System.out.println("======================");
        System.out.println(bank.name());
        System.out.println(bank.branch());
        System.out.println("======================");


        int choice;

        do {

            System.out.println("\nMiniBank Menu");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            choice = sc.nextInt();


            MenuOption option = switch(choice)
            {
                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.EXIT;

                default -> null;
            };


            if(option != null)
            {
                switch(option)
                {
                    case OPEN_ACCOUNT ->
                    System.out.println(
                    "Open Account - to be implemented later");

                    case DEPOSIT ->
                    System.out.println(
                    "Deposit - to be implemented later");


                    case WITHDRAW ->
                    System.out.println(
                    "Withdraw - to be implemented later");


                    case TRANSFER ->
                    System.out.println(
                    "Transfer - to be implemented later");


                    case EXIT ->
                    System.out.println(
                    "Thank you for using MiniBank");
                }
            }
            else
            {
                System.out.println("Invalid Choice");
            }


        }while(choice != 5);


        sc.close();
    }
}