import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;

import java.sql.*;
import java.util.Scanner;

class Main{
    private static final String url="jdbc:mysql://localhost/bank";
    private static final String user="root";
    private static final String password="Navya@7388";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver Loaded!");

        }
        catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try{
            Scanner sc=new Scanner(System.in);
            Connection connection = DriverManager.getConnection(url,user,password);
            connection.setAutoCommit(false);
            System.out.println("Connection done!");
            String debitQuery="update account set balance=balance-? where account_number=?";
            String creditQuery="update account set balance=balance+? where account_number=?";
            PreparedStatement debitpreparedStatement=connection.prepareStatement(debitQuery);
            PreparedStatement creditpreparedStatement=connection.prepareStatement(creditQuery);
            System.out.print("Account to debit :");
            int debitAccount=sc.nextInt();
            System.out.print("Account to credit :");
            int creditAccount=sc.nextInt();
            System.out.print("Amount :");
            double amount=sc.nextDouble();
            debitpreparedStatement.setInt(2,debitAccount);
            debitpreparedStatement.setDouble(1,amount);
            creditpreparedStatement.setInt(2,creditAccount);
            creditpreparedStatement.setDouble(1,amount);
            debitpreparedStatement.executeUpdate();
            creditpreparedStatement.executeUpdate();
//            System.out.println(isAvailable(connection,debitAccount,creditAccount,amount));
            if(isAvailable(connection,debitAccount,creditAccount,amount)){
                connection.commit();
                System.out.println("Done.");
            }
            else {
//                connection.rollback();
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static boolean isAvailable(Connection connection,int debitAcc,int creditAcc,double balance) {
        try{
            String query="select balance from account where account_number=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,debitAcc);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                double amount = resultSet.getDouble("balance");
                System.out.println(amount);
                if (amount >= balance) {
                    String query1="select * from account where account_number=? or ?;";
                    preparedStatement=connection.prepareStatement(query1);
                    preparedStatement.setInt(1,debitAcc);
                    preparedStatement.setInt(2,creditAcc);
                    resultSet=preparedStatement.executeQuery();
                    while(resultSet.next()){
                        int acc=resultSet.getInt("account_number");
                        double closingBalance=resultSet.getDouble("balance");
                        System.out.println("Account No :"+acc+" Remaining amount :"+closingBalance);
                    }
                    return true;
                }
                else return false;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}