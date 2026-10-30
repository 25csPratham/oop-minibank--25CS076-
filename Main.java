public class Main {


public static void main(String args[]){


    Account rahul =
    new SavingsAccount("Rahul",10000,1000);


    Account priya =
    new CurrentAccount("Priya",5000,3000);



    try{


        rahul.withdraw(15000);


    }
    catch(InsufficientFundsException e){


        System.out.println(
        "Error: " + e.getMessage()
        );

    }
    catch(InvalidAmountException e){


        System.out.println(
        "Error: " + e.getMessage()
        );

    }
    finally{


        System.out.println(
        "Withdrawal attempt finished"
        );

    }




    try{


        rahul.transfer(priya,2000);


        System.out.println(
        "Transfer successful"
        );


    }
    catch(BankException e){


        System.out.println(
        "Transfer failed: "
        + e.getMessage()
        );

    }
    finally{


        System.out.println(
        "Transfer process ended"
        );

    }





    try(MiniBankResource r =
        new MiniBankResource()){


        r.open();


    }
    catch(Exception e){

        System.out.println(e);

    }


}

}