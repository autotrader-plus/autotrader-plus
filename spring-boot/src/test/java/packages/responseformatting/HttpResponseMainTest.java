package packages.responseformatting;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseMainTest {

    @Test
    void getId() {
        HttpResponseMain test = new HttpResponseMain(1, "TEST CONTENT");
        assert(test.getId() == 1);
    }

    @Test
    void getContent() {
        HttpResponseMain test = new HttpResponseMain(1, "TEST CONTENT");
        assert(Objects.equals(test.getContent(), "TEST CONTENT"));
    }
}