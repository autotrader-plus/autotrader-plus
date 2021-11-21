package packages.returninfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.*;

class ServerMainEndpointHandlerTest {


    @Test
    void httpResponseSenso() throws SQLException, IOException, InterruptedException {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("car-preference", "SUV");
        mapping.put("zip-code", "M11 1S6");
        mapping.put("downpayment", "200");
        mapping.put("name", "Paul");
        mapping.put("credit-score", "770");
        mapping.put("monthlybudget", "5000");

        Gson gsonObj = new Gson();
        String request = gsonObj.toJson(mapping);

        ServerMainEndpointHandler test = new ServerMainEndpointHandler();

        Gson gsonObj2 = new Gson();
        Type mapType = new TypeToken<Map<Object, Object>>(){}.getType();
        HashMap<String, Object> response = gsonObj2.fromJson(test.httpResponseSenso(request), mapType);
        ArrayList<HashMap<String, Object>> entry = (ArrayList<HashMap<String, Object>>) response.get("12");

        Gson gsonObj3 = new Gson();
        HashMap<String, Object> firstloan = gsonObj3.fromJson(String.valueOf(entry.get(0)), mapType);
        assert(Objects.equals(String.valueOf(firstloan.get("capital")), "531.11"));

    }

    @Test
    void parseRequestBody() throws JsonProcessingException {
        HashMap<String, String> info = new HashMap<>();
        info.put("credit_score", "3000");
        info.put("car_preference", "SUV");
        info.put("name", "Paul");
        System.out.print(ServerMainEndpointHandler.parseRequestBody("{\"name\": \"Paul\", \"car_preference\": \"SUV\", \"credit_score\": \"3000\"}"));
        assert(Objects.equals(ServerMainEndpointHandler.parseRequestBody("{\"name\": \"Paul\", \"car_preference\": \"SUV\", \"credit_score\": \"3000\"}"), info));
    }

    @Test
    void getFilteredCars() throws SQLException {
        assert(ServerMainEndpointHandler.getFilteredCars("SUV").get(0).get("Model Year").equals("2017"));
        assert(ServerMainEndpointHandler.getFilteredCars("SUV").get(0).get("Car").equals("Honda Pillot 4WD"));
    }

    @Test
    // SImilar test to the first test, except now it calls all cars in the database since no car preference is indicated.
    void httpResponseSenso2() throws SQLException, IOException, InterruptedException {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("car-preference", "");
        mapping.put("zip-code", "M11 1S6");
        mapping.put("downpayment", "200");
        mapping.put("name", "Paul");
        mapping.put("credit-score", "770");
        mapping.put("monthlybudget", "5000");

        Gson gsonObj = new Gson();
        String request = gsonObj.toJson(mapping);

        ServerMainEndpointHandler test = new ServerMainEndpointHandler();

        Gson gsonObj2 = new Gson();
        Type mapType = new TypeToken<Map<Object, Object>>(){}.getType();
        HashMap<String, Object> response = gsonObj2.fromJson(test.httpResponseSenso(request), mapType);
        ArrayList<HashMap<String, Object>> entry = (ArrayList<HashMap<String, Object>>) response.get("12");

        Gson gsonObj3 = new Gson();
        HashMap<String, Object> firstloan = gsonObj3.fromJson(String.valueOf(entry.get(0)), mapType);
        assert(Objects.equals(String.valueOf(firstloan.get("capital")), "531.11"));

    }

}