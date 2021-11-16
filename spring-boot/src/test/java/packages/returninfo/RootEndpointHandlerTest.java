package packages.returninfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RootEndpointHandlerTest {
    @Test
    void index(){
        RootEndpointHandler test = new RootEndpointHandler();
        assert(test.index() == "This endpoint is not used by TraderAuto! Please make sure to specify an appropriate endpoint.");
    }
}