package packages.responseformatting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

class HttpResponseMainTest {

    private static HashMap<String, Object> test_map;

    @BeforeAll
    static void setUp(){
        HashMap<String, Object> mapping = new HashMap<>();
        mapping.put("1", "Test");
        mapping.put("2", "Test2");
        test_map = mapping;
    }

    @Test
    void getContent() {
        HttpResponseMain test = new HttpResponseMain(test_map);
        String expected = "{\"1\":\"Test\",\"2\":\"Test2\"}";
        System.out.print(test.getContent());
        assert(Objects.equals(test.getContent(), expected));
    }
}