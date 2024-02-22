import java.sql.*;
public class Dbconnect {
    public Connection con;
    public Statement stmt;
    public Dbconnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb","root" , "241260_Hna");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
