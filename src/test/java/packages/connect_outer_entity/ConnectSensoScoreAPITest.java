package packages.connect_outer_entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

class ConnectSensoScoreAPITest {

    private ConnectSensoScoreAPI connector;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("balance", "10000");
        mapping.put("credit_score", "780");
        mapping.put("loan_age", "36");
        mapping.put("vehicle_year", "2021");
        mapping.put("car_value", "10000");
        mapping.put("vehicle_make", "Honda");
        mapping.put("vehicle_model", "Civic");
        mapping.put("loan_start_date", "2021-01-05");

        connector = new ConnectSensoScoreAPI(mapping);
    }

    @Test
    public void getReturnInfo() {
        System.out.println(connector.getReturnInfo());
        assert connector.getReturnInfo() != null;
    }

    @Test
    public void pingSensoAPI() throws IOException, InterruptedException {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("balance", "10000");
        mapping.put("credit_score", "780");
        mapping.put("loan_age", "36");
        mapping.put("vehicle_year", "2021");
        mapping.put("car_value", "10000");
        mapping.put("vehicle_make", "Honda");
        mapping.put("vehicle_model", "Civic");
        mapping.put("loan_start_date", "2021-01-05");

        SensoAPIInterface connector = new ConnectSensoScoreAPI(mapping);
        assert connector.pingSensoAPI(mapping) != null;
    }

}