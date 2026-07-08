package banking;
import java.sql.*;

public class AccountDAO{

    public static void insertCard(BankAccount account){
        String sql = """
                INSERT INTO card(number,pin) 
                VALUES (? , ?);
                """;

        try(Connection connection = Database.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        )
        {
            pstmt.setString(1,account.getCardNumber());
            pstmt.setString(2,account.getPinCode());
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static BankAccount login(String number,String pin){
        String sql = """
                SELECT * FROM card 
                WHERE number = ? AND pin = ? ;
                """;

        try( Connection connection = Database.connect();
             PreparedStatement pstm = connection.prepareStatement(sql)
        ){
            pstm.setString(1,number);
            pstm.setString(2,pin);


            try(ResultSet rs = pstm.executeQuery();) {
                if (rs.next()) {
                    return new
                            BankAccount(rs.getString("number"),
                            rs.getString("pin"),
                            rs.getInt("balance"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void deposit(int amount,String cardNum){
        String sql = """
                UPDATE card SET balance = balance + ? WHERE number = ? ;
                """;

        try( Connection connection = Database.connect();
             PreparedStatement pstm = connection.prepareStatement(sql)
        ){
            pstm.setInt(1,amount);
            pstm.setString(2,cardNum);

            pstm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void deleteAccount(String cardNum){
        String sql = """
                DELETE FROM card WHERE number = ?
                """;

        try(Connection connection = Database.connect();
            PreparedStatement pstm = connection.prepareStatement(sql)
        ){
            pstm.setString(1,cardNum);

            pstm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        
    }

    public static BankAccount accountExists(String cardNum){
        String sql = """
                SELECT * FROM card WHERE number = ?;
                """;

        try( Connection connection = Database.connect();
             PreparedStatement pstm = connection.prepareStatement(sql)
        ){
            pstm.setString(1,cardNum);
            try(ResultSet rs = pstm.executeQuery()){
                if(rs.next()){
                        return new BankAccount(rs.getString("number"),
                                rs.getString("pin"),
                                rs.getInt("balance"));

                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int getBalance(String cardNum){
        String sql = """
                SELECT balance FROM card WHERE number = ?;
                """;

        try( Connection connection = Database.connect();
             PreparedStatement pstm = connection.prepareStatement(sql)
        ){
            pstm.setString(1,cardNum);
            try(ResultSet rs = pstm.executeQuery()){
                if(rs.next()){
                    return rs.getInt("balance");
                }
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void withdraw(int amount, String cardNum){
        String sql = """
                UPDATE card SET balance = balance - ? WHERE number = ?;
                """;

        try( Connection connection = Database.connect();
             PreparedStatement pstm = connection.prepareStatement(sql)
        ){
            pstm.setInt(1,amount);
            pstm.setString(2,cardNum);

            pstm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}