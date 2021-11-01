package com.example.returninfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class handles all calls to the root endpoint. **/
@RestController
public class RootEndpointHandler {

    @GetMapping("/")
    public String index() {
        return "This endpoint is not used by TraderAuto! Please make sure to specify an appropriate endpoint.";
    }

}