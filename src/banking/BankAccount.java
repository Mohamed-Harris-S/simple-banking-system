package banking;

public class BankAccount {
        private String cardNumber;
        private String pinCode;
        private int balance;

        public BankAccount(String cardNumber, String pinCode, int balance){
            this.cardNumber = cardNumber;
            this.pinCode = pinCode;
            this.balance = balance;
        }

        public void getAccountDetails(){
            System.out.println("Your card number: ");
            System.out.println(cardNumber);
            System.out.println("Your card PIN: ");
            System.out.println(pinCode);
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String getPinCode() {
        return pinCode;
        }

        public int getBalance() {
            return balance;
        }

    public void setBalance(int amount) {
        balance+=amount;
    }

}





