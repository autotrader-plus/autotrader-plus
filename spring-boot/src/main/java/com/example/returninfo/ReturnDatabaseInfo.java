package com.example.returninfo;

import java.sql.*;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responseformatting.*;
import com.example.informationmanipulation.*;

@RestController
@CrossOrigin(origins ="*")
public class ReturnDatabaseInfo {

    private final AtomicLong counter = new AtomicLong();

    /**
     * This will return the information queried by the ReturnDatabaseInfo class
     */
    public String getContent() throws SQLException {

        ReturnCarInformation db_object = new ReturnCarInformation();
        return db_object.returnCarDetailsString(1);

    }

    @CrossOrigin(origins = "http://ec2-18-118-163-255.us-east-2.compute.amazonaws.com:8080")
    @GetMapping("/database")
    public HttpResponseDatabase httpResponseDatabase(@RequestParam(required = false, defaultValue = "") String name) throws SQLException{
        System.out.println("==== connect and respond to Autotrader Database ====");
        HttpResponseDatabase db_response = new HttpResponseDatabase(counter.incrementAndGet(), getContent());
        return db_response;
    }
}