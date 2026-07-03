package banking;

import java.util.Scanner;

public class Main {

    public static int login(Scanner scanner,BankAccount account){
        boolean isLoggedIn = true;
        while(isLoggedIn){
            System.out.println("""
                    1. Balance
                    2. Add income
                    3. Do transfer
                    4. Close account
                    5. Log out
                    0. Exit
                    """);

            int loggedInChoice = scanner.nextInt();

            switch(loggedInChoice){
                case 1:
                    System.out.println("Balance: "+account.getBalance());
                    break;
                case 2:
                    System.out.println("Enter income:");
                    int amount = scanner.nextInt();
                    account.setBalance(amount);
                    AccountDAO.updateBalance(amount,account.getCardNumber());
                    break;
                case 3:
                    System.out.println("Transfer\n" +
                            "Enter card number:");
                    String cardNum = scanner.nextLine();
//                    account.doTransfer(amount);
//                    AccountDAO.updateBalance(amount,account.getCardNumber());
                    break;
                case 4:
                    AccountDAO.deleteAccount(account.getCardNumber());
                    System.out.println("The account has been closed!");
                    isLoggedIn=false;
                    break;
                case 5:
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

        Database.createTable();

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
                    BankAccount account = new BankAccount(cardNumber,pin,balance);
                    AccountDAO.insertCard(account);
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

                    BankAccount loginAccount = AccountDAO.login(accNum,accPin);

                    if(loginAccount!=null){
                            System.out.println("You have successfully logged in!");
                            int logType = login(scanner,loginAccount);
                            if(logType==0){
                                return;
                            }
                        }else{
                        System.out.println("Wrong card number or PIN!");
                    }
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
            }
        }
    }
}