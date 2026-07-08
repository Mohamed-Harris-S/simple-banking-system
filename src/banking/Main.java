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
            scanner.nextLine();

            switch(loggedInChoice){
                case 1:
                    int balance = AccountDAO.getBalance(account.getCardNumber());
                    System.out.println("Balance: "+balance);
                    break;
                case 2:
                    System.out.println("Enter income:");
                    int income = scanner.nextInt();
                    account.setBalance(income);
                    AccountDAO.deposit(income,account.getCardNumber());
                    System.out.println("Income was added!");
                    break;
                case 3:
                    System.out.println("Transfer\n" +
                            "Enter card number:");
                    String transferCardNum = scanner.nextLine();
                    boolean isCardValid = CardGenerator.isCardValid(transferCardNum);

                    if( !isCardValid){
                        System.out.println("Probably you made a mistake in the card number.");
                        break;
                    }

                    BankAccount transferAccount = AccountDAO.accountExists(transferCardNum);

                    if(transferAccount==null){
                        System.out.println("Such a card does not exist.");
                    }else if(transferAccount.getCardNumber().equals(account.getCardNumber())){
                        System.out.println("You can't transfer money to the same account!");
                    }
                    else{
                        System.out.println("Enter how much money you want to transfer:");
                        int amount = scanner.nextInt();
                        int transferBalance = AccountDAO.getBalance(account.getCardNumber());

                        if(transferBalance>amount){
                            AccountDAO.withdraw(amount,account.getCardNumber());
                            AccountDAO.deposit(amount,transferCardNum);
                            System.out.println("Success!");
                        }else{
                            System.out.println("Not enough money!");
                        }
                    }

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