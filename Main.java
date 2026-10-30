public class Main {


    public static void main(String args[]){


        Account acc =
        new SavingsAccount("Rahul",-500,1000);



        String result[] =
        AnnotationValidator.validate(acc);



        if(result.length==0){

            System.out.println("Validation Successful");

        }
        else{


            System.out.println("Validation Errors:");

            for(String error:result){

                System.out.println(error);

            }

        }

    }

}