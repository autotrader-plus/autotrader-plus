package com.example.informationmanipulation;

import net.bytebuddy.asm.Advice;
import java.lang.Object;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static java.lang.Math.min;
import static org.junit.jupiter.api.Assertions.*;

class ReturnMultipleCarsTest {


    @Test
    @DisplayName("AllCars: Basic Case")
    void returnAllCars() throws SQLException {
        ArrayList<HashMap<String, String>> testList = new ArrayList<>();

        // Adding all hashmaps of car information
        testList.add(0, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Chevrolet Corvette Stingray");
            put("Mileage", "15466");
            put("Car Type", "Convertible");
            put("ID", "0");
            put("Cost", "143944");
            put("Dealership", "Georgian Chevrolet");
        }});
        testList.add(1, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Hyundai Sonata Ultimate");
            put("Mileage", "10323");
            put("Car Type", "Convertible");
            put("ID", "1");
            put("Cost", "35649");
            put("Dealership", "Guelph Hyundai");
        }});
        testList.add(2, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Mazda CS-5 GT");
            put("Mileage", "8923");
            put("Car Type", "Convertible");
            put("ID", "2");
            put("Cost", "40779");
            put("Dealership", "Dixie Mazdda");
        }});
        testList.add(3, new HashMap<>() {{
            put("Model Year", "2018");
            put("Car", "BMW M3 CS");
            put("Mileage", "19950");
            put("Car Type", "Convertible");
            put("ID", "3");
            put("Cost", "91888");
            put("Dealership", "Auto|One");
        }});
        testList.add(4, new HashMap<>() {{
            put("Model Year", "2017");
            put("Car", "Honda Pillot 4WD");
            put("Mileage", "145174");
            put("Car Type", "SUV");
            put("ID", "4");
            put("Cost", "28560");
            put("Dealership", "Barrie Honda");
        }});
        testList.add(5, new HashMap<>() {{
            put("Model Year", "2014");
            put("Car", "Lexus GX 460");
            put("Mileage", "114781");
            put("Car Type", "SUV");
            put("ID", "5");
            put("Cost", "41998");
            put("Dealership", "Porche of London");
        }});
        testList.add(6, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Dodge Durango");
            put("Mileage", "22709");
            put("Car Type", "SUV");
            put("ID", "6");
            put("Cost", "64724");
            put("Dealership", "Galt");
        }});
        testList.add(7, new HashMap<>() {{
            put("Model Year", "2018");
            put("Car", "Mercedes-Benz C-Class C 300");
            put("Mileage", "33390");
            put("Car Type", "Sedan");
            put("ID", "7");
            put("Cost", "37599");
            put("Dealership", "Special Interest Automobiles");
        }});
        testList.add(8, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Honda Accord Hybrid");
            put("Mileage", "51905");
            put("Car Type", "Sedan");
            put("ID", "8");
            put("Cost", "35800");
            put("Dealership", "Special Interest Automobiles");
        }});
        testList.add(9, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Hyundai Santa Fe");
            put("Mileage", "8809");
            put("Car Type", "SUV");
            put("ID", "9");
            put("Cost", "22988");
            put("Dealership", "Special Interest Automobiles");
        }});
        testList.add(10, new HashMap<>() {{
            put("Model Year", "2016");
            put("Car", "Lincoln MKX Rserve AWD");
            put("Mileage", "64950");
            put("Car Type", "Sedan");
            put("ID", "10");
            put("Cost", "21899");
            put("Dealership", "Airport Ford");
        }});
        testList.add(11, new HashMap<>() {{
            put("Model Year", "2020");
            put("Car", "Hyundai Elantra");
            put("Mileage", "27701");
            put("Car Type", "Sedan");
            put("ID", "11");
            put("Cost", "20995");
            put("Dealership", "Attrell Hyundai");
        }});
        testList.add(12, new HashMap<>() {{
            put("Model Year", "2014");
            put("Car", "Jeep Grand Cherokee");
            put("Mileage", "207599");
            put("Car Type", "SUV");
            put("ID", "12");
            put("Cost", "19900");
            put("Dealership", "Daleo Motors");
        }});
        testList.add(13, new HashMap<>() {{
            put("Model Year", "2009");
            put("Car", "Mercedes-Benz S-Class");
            put("Mileage", "108731");
            put("Car Type", "Sedan");
            put("ID", "13");
            put("Cost", "16888");
            put("Dealership", "Northline");
        }});
        testList.add(14, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Mistubishi Outlander ES");
            put("Mileage", "4300");
            put("Car Type", "SUV");
            put("ID", "14");
            put("Cost", "30152");
            put("Dealership", "Dixie Mitsubishi");
        }});
        testList.add(15, new HashMap<>() {{
            put("Model Year", "2020");
            put("Car", "KIA Sorento LX");
            put("Mileage", "20130");
            put("Car Type", "SUV");
            put("ID", "15");
            put("Cost", "28000");
            put("Dealership", "Eagle Motors");
        }});
        testList.add(16, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Honda Civic Sedan LX");
            put("Mileage", "12039");
            put("Car Type", "Sedan");
            put("ID", "16");
            put("Cost", "26328");
            put("Dealership", "Class Honda");
        }});
        testList.add(17, new HashMap<>() {{
            put("Model Year", "2016");
            put("Car", "Toyota RAV4 LE");
            put("Mileage", "109500");
            put("Car Type", "SUV");
            put("ID", "17");
            put("Cost", "20478");
            put("Dealership", "Vaughan Chrysler");
        }});
        testList.add(18, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Mercedes-Benz E450");
            put("Mileage", "12389");
            put("Car Type", "Wagon");
            put("ID", "18");
            put("Cost", "70128");
            put("Dealership", "Mercedes-Benz Oakville");
        }});
        testList.add(19, new HashMap<>() {{
            put("Model Year", "2017");
            put("Car", "BMW 3 Series 328d");
            put("Mileage", "42201");
            put("Car Type", "Wagon");
            put("ID", "19");
            put("Cost", "33850");
            put("Dealership", "BMW Laval");
        }});
        testList.add(20, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Ram 1500 Laramie");
            put("Mileage", "6574");
            put("Car Type", "Truck");
            put("ID", "20");
            put("Cost", "52099");
            put("Dealership", "Unique Jeep");
        }});
        testList.add(21, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Chevrolet Silverado 1500");
            put("Mileage", "28470");
            put("Car Type", "Truck");
            put("ID", "21");
            put("Cost", "46990");
            put("Dealership", "canadadrives");
        }});
        testList.add(22, new HashMap<>() {{
            put("Model Year", "2017");
            put("Car", "Chevrolet Colorado WT");
            put("Mileage", "27899");
            put("Car Type", "Truck");
            put("ID", "22");
            put("Cost", "27995");
            put("Dealership", "Wilson's");
        }});
        testList.add(23, new HashMap<>() {{
            put("Model Year", "2016");
            put("Car", "Infiniti QX50");
            put("Mileage", "71935");
            put("Car Type", "Wagon");
            put("ID", "23");
            put("Cost", "25895");
            put("Dealership", "Dixie Infiniti");
        }});

        assert Objects.equals(testList, ReturnMultipleCars.returnAllCars());
    }

    @Test
    @DisplayName("FilteredCars: Basic Case")
    void returnFilteredCars() throws SQLException {
        String filter = "SUV";

        ArrayList<HashMap<String, String>> testList = new ArrayList<>();

        testList.add(0, new HashMap<>() {{
            put("Model Year", "2017");
            put("Car", "Honda Pillot 4WD");
            put("Mileage", "145174");
            put("Car Type", "SUV");
            put("ID", "4");
            put("Cost", "28560");
            put("Dealership", "Barrie Honda");
        }});
        testList.add(1, new HashMap<>() {{
            put("Model Year", "2014");
            put("Car", "Lexus GX 460");
            put("Mileage", "114781");
            put("Car Type", "SUV");
            put("ID", "5");
            put("Cost", "41998");
            put("Dealership", "Porche of London");
        }});
        testList.add(2, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Dodge Durango");
            put("Mileage", "22709");
            put("Car Type", "SUV");
            put("ID", "6");
            put("Cost", "64724");
            put("Dealership", "Galt");
        }});
        testList.add(3, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Hyundai Santa Fe");
            put("Mileage", "8809");
            put("Car Type", "SUV");
            put("ID", "9");
            put("Cost", "22988");
            put("Dealership", "Special Interest Automobiles");
        }});
        testList.add(4, new HashMap<>() {{
            put("Model Year", "2014");
            put("Car", "Jeep Grand Cherokee");
            put("Mileage", "207599");
            put("Car Type", "SUV");
            put("ID", "12");
            put("Cost", "19900");
            put("Dealership", "Daleo Motors");
        }});
        testList.add(5, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Mistubishi Outlander ES");
            put("Mileage", "4300");
            put("Car Type", "SUV");
            put("ID", "14");
            put("Cost", "30152");
            put("Dealership", "Dixie Mitsubishi");
        }});
        testList.add(6, new HashMap<>() {{
            put("Model Year", "2020");
            put("Car", "KIA Sorento LX");
            put("Mileage", "20130");
            put("Car Type", "SUV");
            put("ID", "15");
            put("Cost", "28000");
            put("Dealership", "Eagle Motors");
        }});
        testList.add(7, new HashMap<>() {{
            put("Model Year", "2016");
            put("Car", "Toyota RAV4 LE");
            put("Mileage", "109500");
            put("Car Type", "SUV");
            put("ID", "17");
            put("Cost", "20478");
            put("Dealership", "Vaughan Chrysler");
        }});

//        assert Objects.equals(testList, returnedList);
        assert Objects.equals(testList, ReturnMultipleCars.returnFilteredCars(filter));
    }

}