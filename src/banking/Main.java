package banking;
import java.util.Scanner;

public class Main {

    public static int login(Scanner scanner,BankAccount account){
        boolean isLoggedIn = true;
        while(isLoggedIn){
            System.out.println("1. Balance\n" +
                    "2. Log out\n" +
                    "0. Exit");

            int loggedInChoice = scanner.nextInt();

            switch(loggedInChoice){
                case 1:
                    System.out.println("Balance: "+account.getBalance());
                    break;
                case 2:
                    System.out.println("Logged out");
                    isLoggedIn=false;
                    break;
                case 0:
                    System.out.println("Bye!");
                    return loggedInChoice;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount();

        while(true){
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");

            int menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch(menuChoice){
                case 1:
                    String cardNumber = CardGenerator.accountNumber();
                    String pin = CardGenerator.pinCodeGenerator();
                    int balance = 0;
                    account.createAccount(cardNumber,pin,balance);
                    System.out.println("Your card has been created");
                    account.getAccountDetails();
                    break;
                case 2:
                    System.out.println("Enter your card number:");
                    String accNum = scanner.nextLine();

                    System.out.println("Enter your PIN:");
                    String accPin = scanner.nextLine();

//                    if(acc==null){
//                        System.out.println("Account not created yet!!");
//                        break;
//                    }

                    if(account.getCardNumber().equals(accNum) && account.getPinCode().equals(accPin)){
                            System.out.println("You have successfully logged in!");
                            int logType = login(scanner,account);
                            if(logType==0){
                                return;
                            }
                        }
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
            }
        }
    }
}