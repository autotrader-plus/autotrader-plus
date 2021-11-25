package packages.returninfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import packages.exceptions.DatabaseConnectionFailureException;

import java.lang.reflect.Type;
import java.util.*;

class SignInEndpointHandlerTest {
    
    @Test
    void httpResponseSenso() {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("username", "DNE");
        mapping.put("password", "DNE");

        Gson gsonObj = new Gson();
        String request = gsonObj.toJson(mapping);

        SignInEndpointHandler test = new SignInEndpointHandler();

        System.out.println(request);
        String response = test.httpResponseSignIn( request );

        assert(Objects.equals( response, ""));

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