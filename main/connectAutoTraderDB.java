import java.sql.*;
import java.util.HashMap;

public class connectAutoTraderDB{

    private final Statement objStatement;

    public connectAutoTraderDB(){
        this.objStatement = connectAutoTraderDB.establishConnection();
    }

    private static Statement establishConnection(){

        try{

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }

            catch (ClassNotFoundException e) {
                e.printStackTrace();

            }

            String dburl="jdbc:mysql://traderautoplusdb.cs3kq1tsgpar.ca-central-1.rds.amazonaws.com:3306/cars";
            String username="sweatless";
            String password="PaulGries123";

            Connection myConnection= DriverManager.getConnection(dburl, username, password);

            return myConnection.createStatement();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String returnCarDetailsString(Integer car_id){

        String returnString = "";

        try{

            assert this.objStatement != null;
            ResultSet myResultSet=this.objStatement.executeQuery("SELECT * FROM cars.AvailableCars " +
                    "WHERE id_car = " + car_id.toString() + ";");

            if(myResultSet.next()) {
                returnString = "Car: " + myResultSet.getString("name") + "\n" +
                               "Cost: $" + myResultSet.getString("cost");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return returnString;
    }

    public HashMap<String, Object> returnCarDetails(Integer car_id){

        HashMap<String, Object> returnMap = new HashMap<>();

        try{

            assert this.objStatement != null;
            ResultSet myResultSet=this.objStatement.executeQuery("SELECT * FROM cars.AvailableCars " +
                    "WHERE id_car = " + car_id.toString() + ";");

            if(myResultSet.next()) {
                returnMap.put("Car", myResultSet.getString("name"));
                returnMap.put("Cost", myResultSet.getString("cost"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return returnMap;
    }

    public static void main(String[] args) {
        connectAutoTraderDB t = new connectAutoTraderDB();
        System.out.println(t.returnCarDetailsString(1));
        System.out.println(t.returnCarDetails(1).keySet());
    }
}