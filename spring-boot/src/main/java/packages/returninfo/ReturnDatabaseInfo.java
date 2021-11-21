package packages.returninfo;

import java.sql.*;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

import packages.informationmanipulation.*;
import packages.responseformatting.HttpResponseMain;

@RestController
@CrossOrigin(origins ="*")
public class ReturnDatabaseInfo {

    private final AtomicLong counter = new AtomicLong();

    /**
     * This will return the information queried by the ReturnDatabaseInfo class
     * @return
     */
    public HashMap<String, Object> getContent(int carId) throws SQLException {

        ReturnCarInformation db_object = new ReturnCarInformation();
        return db_object.returnCarDetails(carId);


    }

    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @PostMapping("/database")
    public String httpResponseMain(@RequestBody String carId) throws SQLException{
        System.out.println("==== Connecting to Autotrader Database ====");
        HashMap<String, Object> response = getContent(Integer.parseInt(carId));
        HttpResponseMain http_response = new HttpResponseMain(response);
        System.out.println(String.format("==== Returning Car Information for Car %s ====", carId));
        return http_response.getContent();
    }
}