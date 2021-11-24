package packages.returninfo;

import org.junit.jupiter.api.Test;
import packages.exceptions.DatabaseConnectionFailureException;

import java.util.HashMap;
import java.util.Objects;

class ReturnDatabaseInfoTest {

    @Test
    void getContent() throws DatabaseConnectionFailureException {
        ReturnDatabaseInfo test = new ReturnDatabaseInfo();
        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("Model Year", "2021");
        expected.put("Car", "Hyundai Sonata Ultimate");
        expected.put("Mileage", "10323");
        expected.put("Car Type", "Convertible");
        expected.put("ID", "1");
        expected.put("Cost", "35649");
        expected.put("Dealership", "Guelph Hyundai");
        expected.put("Photo", "https://www.hyundaionhuntclub.com/vimgs/usd00hyc031d022004/IOF_H600/ColourPhotoSample_0.jpg");
        System.out.print(test.getContent(1));
        assert (Objects.equals(test.getContent(1), expected));
    }

    @Test
    void httpResponseMain() {
        ReturnDatabaseInfo test = new ReturnDatabaseInfo();
        String expected = "{\"Model Year\":\"2021\",\"Car\":\"Hyundai Sonata Ultimate\"," +
                "\"Mileage\":\"10323\",\"Car Type\":\"Convertible\"," +
                "\"Photo\":\"https://www.hyundaionhuntclub.com/vimgs/usd00hyc031d022004/IOF_H600/ColourPhotoSample_0.jpg\"," +
                "\"ID\":\"1\",\"Cost\":\"35649\"," +
                "\"Dealership\":\"Guelph Hyundai\"}";
        String result = test.httpResponseMain("1");
        assert(Objects.equals(result, expected));
    }
}