package banking;

import java.util.Random;



public class CardGenerator {
    private static final String BIN = "400000";


    public static String pinCodeGenerator(){
        Random random = new Random();
        return random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10);
    }

    public static String randomNumberGenerator(){
        Random random = new Random();
        long lower = 100000000L;
        long upper = 999999999L;
        return BIN + random.nextLong(lower, upper + 1);
    }



    public static int luhnAlgorithmGenerator(String uniqueNum){
        int sum = 0;
        StringBuilder doubleNum = new StringBuilder();
        for(int i=0;i<uniqueNum.length();i++){
            int temp = Integer.parseInt(String.valueOf(uniqueNum.charAt(i)));
            if((i+1)%2!=0) {
                temp *= 2;
                if (temp > 9) {
                    temp -= 9;
                }
            }
            sum+=temp;
            doubleNum.append(temp);
        }
//        System.out.println(doubleNum);
//        System.out.println(sum);
        if(uniqueNum.length()==15){
            int checkSum = sum;
            while(checkSum%10!=0){
                checkSum++;
            }

//            System.out.println(checkSum);
            int lastdigit = checkSum-sum;
            return checkSum - sum;
        }else{
            return sum;
        }

    }

    public static String accountNumber() {
        String uniqueNum = randomNumberGenerator();
        int lastDigit = luhnAlgorithmGenerator(uniqueNum);
        uniqueNum+=lastDigit;
//     System.out.println(uniqueNum);
//        int sum = luhnAlgorithmGenerator(uniqueNum);
//        if(sum%10==0){
//            System.out.println("Legit");
//        }else{
//            System.out.println("not legit");
//        }
        return uniqueNum;
    }
}

