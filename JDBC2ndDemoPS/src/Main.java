import java.sql.*;

public class Main {
    private static final String url="jdbc:mysql://localhost:3306/mydb";
    private static final String user="root";
    private static final String password = "Navya@7388";



    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e) {
            System.out.print(e.getMessage());
        }
        try{
            Connection connection= DriverManager.getConnection(url,user,password);
            System.out.println("Connection Done");
            // TO get Data from DATABASE
            String query="SELECT * FROM student where id=5";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name =resultSet.getString("name");
                int age=resultSet.getInt("age");
                double marks =resultSet.getDouble("marks");
                System.out.println("\tID :"+id+"\tNAME :"+name+"\tAGE :"+age+"\tMARKS :"+marks);

            }
            // TO INSERT data in DATABASE
            query="INSERT INTO student(name,age,marks) VALUES('Bhupendra',25,78)";
            preparedStatement=connection.prepareStatement(query);
            int rowAffected=preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Data INSERTED." +rowAffected+" Row Affected.");

            }
            // TO UPDATE data in DATABASE
            query="UPDATE student SET name='AKASH' WHERE id=6";
            preparedStatement=connection.prepareStatement(query);
            rowAffected=preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Data Updated." +rowAffected+" Row Affected.");

            }
            // TO DELETE data in DATABASE
            query="DELETE FROM student WHERE id=7";
            preparedStatement=connection.prepareStatement(query);
            rowAffected=preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Data Deleted." +rowAffected+" Row Affected.");
            }
            else{
                System.out.println("record not found. try again with correct data.");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
