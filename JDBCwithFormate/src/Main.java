import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class Main{
    private static final String url="jdbc:mysql://localhost/mydb";
    private static final String user="root";
    private static final String password="Navya@7388";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded!");

        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connection done!");
            String query="INSERT INTO student(name,age,marks) VALUE(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            Scanner sc=new Scanner(System.in);
            int count=0;
            while(true){
                System.out.print("Enter Name :");
                String name= sc.next();
                System.out.print("Enter Age :");
                int age= sc.nextInt();
                System.out.print("Enter Marks :");
                double marks= sc.nextDouble();
                count++;
                System.out.println(count+" Record Added.");
                System.out.println("Enter more Data(Y/N):");
                String choice= sc.next();
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);
                preparedStatement.addBatch();
                if(choice.toLowerCase().equals("n")){
                    break;
                }

            }
            int[] rowAffectedInArray=preparedStatement.executeBatch();
            for(int i=0;i<rowAffectedInArray.length;i++){
                if(rowAffectedInArray[i]==0){
                    System.out.println("query "+(i+1)+" not executed.");
                }else{
                    System.out.println("All query run successfully.");
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}