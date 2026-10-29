public class MiniBank {

    public static void main(String[] args) {

        Customer.Address address =
                new Customer.Address(
                        "101, MG Road",
                        "Vadodara",
                        "390001");

        Customer customer =
                new Customer(
                        "Pratham Patel",
                        "pratham@gmail.com",
                        "9876543210",
                        address);

        Customer copy = customer.clone();

        System.out.println("Customer ID : " + copy.getCustomerId());
        System.out.println("City : " + copy.getAddress().getCity());

        System.out.println();

        Account a1 = new Account("Rahul",10000);
        Account a2 = new Account("Priya",5000);
        Account a3 = new Account("Rahul",10000);

        a1.deposit(3000);
        a2.withdraw(1000);

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);

        System.out.println();

        System.out.println("a1 equals a2 : " + a1.equals(a2));
        System.out.println("a1 equals a1 : " + a1.equals(a1));

        System.out.println();

        Object obj = a1;

        if(obj instanceof Account){
            System.out.println("obj is an Account");
        }
    }
}