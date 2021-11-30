package packages.server_setup;

import org.junit.jupiter.api.Test;

class RootEndpointHandlerTest {
    @Test
    void index(){
        RootEndpointHandler test = new RootEndpointHandler();
        assert(test.index() == "This endpoint is not used by TraderAuto! Please make sure to specify an appropriate endpoint.");
    }
}