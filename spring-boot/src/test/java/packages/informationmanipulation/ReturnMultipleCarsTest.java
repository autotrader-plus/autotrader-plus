package packages.informationmanipulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static java.lang.Math.min;

class ReturnMultipleCarsTest {
    ReturnMultipleCars returnMultipleCars;
    ReturnMultipleCars mockedConnection;

    @BeforeEach
    void setUp() {
        returnMultipleCars = new ReturnMultipleCars();
        mockedConnection = mock(ReturnMultipleCars.class);
    }

    @Test
    @DisplayName("AllCars: Basic Case (Unit Test with Mocked Database)")
    void returnAllCarsUnitTest() throws SQLException{
        mockedConnection.returnAllCars();
        when(mockedConnection.returnAllCars()).thenReturn(createArrayResponse());

        HashMap<String, Object> actual = returnMultipleCars.returnAllCars().get(0);
        System.out.println(mockedConnection.returnAllCars().get(0));
        System.out.println(actual);
        assert Objects.equals(mockedConnection.returnAllCars().get(0), actual);

    }

    private ArrayList<HashMap<String, Object>> createArrayResponse() {
        ArrayList<HashMap<String, Object>> testList = new ArrayList<>();

        testList.add(0, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Chevrolet Corvette Stingray");
            put("Mileage", "15466");
            put("Car Type", "Convertible");
            put("ID", "0");
            put("Cost", "43944");
            put("Dealership", "Georgian Chevrolet");
            put("Photo", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/screen-shot-2019-10-03-at-9-52-03-pm-1570154537.png");
        }});

        return testList;
    }

    @Test
    @DisplayName("FilteredCars: Basic Case (Unit Test with Mocked Database)")
    void returnFilteredCarsUnitTest() throws SQLException{
        mockedConnection.returnFilteredCars("SUV");
        when(mockedConnection.returnFilteredCars("SUV")).thenReturn(createFilteredArrayResponse());

        HashMap<String, Object> actual = returnMultipleCars.returnFilteredCars("SUV").get(0);

        assert Objects.equals(mockedConnection.returnFilteredCars("SUV").get(0), actual);
    }

    private ArrayList<HashMap<String, Object>> createFilteredArrayResponse() {
        ArrayList<HashMap<String, Object>> testList = new ArrayList<>();

        testList.add(0, new HashMap<>() {{
            put("Model Year", "2017");
            put("Car", "Honda Pillot 4WD");
            put("Mileage", "145174");
            put("Car Type", "SUV");
            put("ID", "4");
            put("Cost", "28560");
            put("Dealership", "Barrie Honda");
            put("Photo", "https://o.aolcdn.com/images/dims3/GLOB/legacy_thumbnail/800x450/format/jpg/quality/85/http://www.blogcdn.com/www.autoblog.com/media/2011/04/01-2011-honda-pilot-opt.jpg");
        }});

        return testList;
    }

    @Test
    @DisplayName("AllCars: Basic Case (Integration Testing)")
    void returnAllCars() throws SQLException {
        ArrayList<HashMap<String, Object>> allCars = returnMultipleCars.returnAllCars();
        assert Objects.equals(24, allCars.size());
    }

    @Test
    @DisplayName("FilteredCars: Basic Case (Integration Testing)")
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
            put("Photo", "https://o.aolcdn.com/images/dims3/GLOB/legacy_thumbnail/800x450/format/jpg/quality/85/http://www.blogcdn.com/www.autoblog.com/media/2011/04/01-2011-honda-pilot-opt.jpg");
        }});
        testList.add(1, new HashMap<>() {{
            put("Model Year", "2014");
            put("Car", "Lexus GX 460");
            put("Mileage", "114781");
            put("Car Type", "SUV");
            put("ID", "5");
            put("Cost", "41998");
            put("Dealership", "Porche of London");
            put("Photo", "https://media.ed.edmunds-media.com/lexus/gx-460/2022/oem/2022_lexus_gx-460_4dr-suv_base_fq_oem_1_1600.jpg");
        }});
        testList.add(2, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Dodge Durango");
            put("Mileage", "22709");
            put("Car Type", "SUV");
            put("ID", "6");
            put("Cost", "64724");
            put("Dealership", "Galt");
            put("Photo", "https://www.autotrader.com/wp-content/uploads/2021/09/2022-dodge-durango-front-right-driving.jpg");
        }});
        testList.add(3, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Hyundai Santa Fe");
            put("Mileage", "8809");
            put("Car Type", "SUV");
            put("ID", "9");
            put("Cost", "22988");
            put("Dealership", "Special Interest Automobiles");
            put("Photo", "https://s.aolcdn.com/os/ab/_cms/2020/06/03024835/2020-hyundai-santa-fe-official-16.jpg");
        }});
        testList.add(4, new HashMap<>() {{
            put("Model Year", "2014");
            put("Car", "Jeep Grand Cherokee");
            put("Mileage", "207599");
            put("Car Type", "SUV");
            put("ID", "12");
            put("Cost", "19900");
            put("Dealership", "Daleo Motors");
            put("Photo", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2022-jeep-grand-cherokee-summit-reserve-4xe-114-1632861119.jpg");
        }});
        testList.add(5, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Mistubishi Outlander ES");
            put("Mileage", "4300");
            put("Car Type", "SUV");
            put("ID", "14");
            put("Cost", "30152");
            put("Dealership", "Dixie Mitsubishi");
            put("Photo", "https://media.ed.edmunds-media.com/mitsubishi/outlander/2019/oem/2019_mitsubishi_outlander_4dr-suv_gt_fq_oem_1_1600.jpg");
        }});
        testList.add(6, new HashMap<>() {{
            put("Model Year", "2020");
            put("Car", "KIA Sorento LX");
            put("Mileage", "20130");
            put("Car Type", "SUV");
            put("ID", "15");
            put("Cost", "28000");
            put("Dealership", "Eagle Motors");
            put("Photo", "https://s.aolcdn.com/commerce/autodata/images/USC90KIS021A021001_2.jpg");
        }});
        testList.add(7, new HashMap<>() {{
            put("Model Year", "2016");
            put("Car", "Toyota RAV4 LE");
            put("Mileage", "109500");
            put("Car Type", "SUV");
            put("ID", "17");
            put("Cost", "20478");
            put("Dealership", "Vaughan Chrysler");
            put("Photo", "https://s.aolcdn.com/commerce/autodata/images/USD00TOS112A021001.jpg");
        }});

        assert Objects.equals(testList, returnMultipleCars.returnFilteredCars(filter));
    }

}