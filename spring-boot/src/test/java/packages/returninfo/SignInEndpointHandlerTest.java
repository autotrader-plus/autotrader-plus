package packages.returninfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import packages.exceptions.DatabaseConnectionFailureException;

import java.lang.reflect.Type;
import java.util.*;

class SignInEndpointHandler {


    // TODO: Further editing required in this test case
    @Test
    void httpResponseSenso() {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("username", "DNE");
        mapping.put("password", "DNE");

        Gson gsonObj = new Gson();
        String request = gsonObj.toJson(mapping);

        SignInEndpointHandler test = new SignInEndpointHandler();

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
        info.put("username", "DNE");
        info.put("password", "DNE");
        assert(Objects.equals(ServerMainEndpointHandler.parseRequestBody("{\"username\": \"DNE\", " +
                "\"password\": \"DNE\"}"), info));
    }

}