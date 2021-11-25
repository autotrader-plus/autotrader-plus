package packages.connectouterentity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

class ConnectSensoAPITest {

    private ConnectSensoRateAPI connector;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("loan_amount", "10000");
        mapping.put("credit_score", "780");
        mapping.put("payment_budget", "800");
        mapping.put("vehicle_make", "Honda");
        mapping.put("vehicle_model", "Civic");
        mapping.put("vehicle_year", "2021");
        mapping.put("vehicle_kms", "1000");
        mapping.put("list_price", "10000");
        mapping.put("downpayment", "10000");

        connector = new ConnectSensoRateAPI(mapping);
    }

    @Test
    public void getReturnInfo() {
        assert connector.getReturnInfo() != null;
        System.out.print(connector.getReturnInfo());
    }

    @Test
    public void pingSensoAPI() throws IOException, InterruptedException {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("loan_amount", "10000");
        mapping.put("credit_score", "780");
        mapping.put("payment_budget", "800");
        mapping.put("vehicle_make", "Honda");
        mapping.put("vehicle_model", "Civic");
        mapping.put("vehicle_year", "2021");
        mapping.put("vehicle_kms", "1000");
        mapping.put("list_price", "10000");
        mapping.put("downpayment", "10000");

        SensoAPIInterface connector = new ConnectSensoRateAPI(mapping);
        assert connector.pingSensoAPI(mapping) != null;
        System.out.print(connector.pingSensoAPI(mapping));
    }
}