import java.sql.*;
public class connectAutoTraderDB{
    public static void main(String[] args) {
        try{

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String dburl="jdbc:mysql://traderautoplusdb.cs3kq1tsgpar.ca-central-1.rds.amazonaws.com:3306/cars";
            String username="sweatless";
            String password="PaulGries123";

            Connection myConnection= DriverManager.getConnection(dburl, username, password);

            Statement myStatement=myConnection.createStatement();

            ResultSet myResultSet=myStatement.executeQuery("SELECT * FROM cars.AvailableCars;");

            while(myResultSet.next()){
                System.out.println("Car: " + myResultSet.getString("name"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}