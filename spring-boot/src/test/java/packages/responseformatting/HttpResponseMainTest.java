package packages.responseformatting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

class HttpResponseMainTest {

    private static HashMap<String, Object> testMap;

    @BeforeAll
    static void setUp(){
        HashMap<String, Object> mapping = new HashMap<>();
        mapping.put("1", "Test");
        mapping.put("2", "Test2");
        testMap = mapping;
    }

    @Test
    void getContent() {
        HttpResponseMain test = new HttpResponseMain(testMap);
        String expected = "{\"1\":\"Test\",\"2\":\"Test2\"}";
        System.out.print(test.getContent());
        assert(Objects.equals(test.getContent(), expected));
    }
}