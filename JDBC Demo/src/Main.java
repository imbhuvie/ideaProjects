import java.sql.*;
public class Main {
    private static final String url="jdbc:mysql://localhost:3306/mydb";
    private  static final  String user="root";
    private static final String password="Navya@7388";
    public static void main(String[] args){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
//        System.out.println("Driver Loaded");
    } catch (ClassNotFoundException e){
        System.out.print(e.getMessage());
    }
    try{
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        String query0="insert into student(name,age,marks) values('bhuvie',25,76.65)";
        int rowAffected=statement.executeUpdate(query0);
        if(rowAffected>0){
            System.out.println("Inserted successfully. "+rowAffected+" rows affected.");
        }
        else{
            System.out.println("Not inserted.");
        }
        String query1="select * from student";
        ResultSet resultSet=statement.executeQuery(query1);
        while(resultSet.next()){
            int id= resultSet.getInt("id");
            String name =resultSet.getString("name");
            int age = resultSet.getInt("age");
            double marks= resultSet.getDouble("marks");
            System.out.println("ID :"+id+"\tNAME: "+name+"\tAGE :"+age+"\tMARKS : "+marks+"\n");
        }
        String query2="update student set marks=90.99 where id=4";
        rowAffected = statement.executeUpdate(query2);
        if(rowAffected>0){
            System.out.println("Updated successfully."+rowAffected+" rows affected.");
        }
        else{
            System.out.println("Not updated.");
        }
        resultSet=statement.executeQuery(query1);
        while(resultSet.next()){
            int id= resultSet.getInt("id");
            String name =resultSet.getString("name");
            int age = resultSet.getInt("age");
            double marks= resultSet.getDouble("marks");
            System.out.println("ID :"+id+"\tNAME: "+name+"\tAGE :"+age+"\tMARKS : "+marks+"\n");
        }
        String query3="delete from student where id=4";
        rowAffected = statement.executeUpdate(query3);
        if(rowAffected>0){
            System.out.println("Deleted successfully. "+rowAffected+" rows affected.");
        }
        else{
            System.out.println("Not Deleted.");
        }
        resultSet=statement.executeQuery(query1);
        while(resultSet.next()){
            int id= resultSet.getInt("id");
            String name =resultSet.getString("name");
            int age = resultSet.getInt("age");
            double marks= resultSet.getDouble("marks");
            System.out.println("ID :"+id+"\tNAME: "+name+"\tAGE :"+age+"\tMARKS : "+marks+"\n");
        }
        
    }
    catch (SQLException e){
        System.out.println(e.getMessage());
    }
    }
}