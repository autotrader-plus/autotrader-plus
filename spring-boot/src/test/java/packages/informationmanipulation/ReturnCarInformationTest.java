package packages.informationmanipulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

class ReturnCarInformationTest {
    ReturnCarInformation returnCarInformation;
    ReturnCarInformation mockedConnection;

    @BeforeEach
    void setUp() {
        returnCarInformation = new ReturnCarInformation();
        mockedConnection = mock(ReturnCarInformation.class);
    }

    @Test
    @DisplayName("CarDetailString: Basic Case (Unit Test with Mocked Database)")
    void returnCarDetailsStringUnitTest() throws SQLException {
        mockedConnection.returnCarDetailsString(1);
        when(mockedConnection.returnCarDetailsString(1)).thenReturn(returnCarInformation.returnCarDetailsString(1));
        verify(mockedConnection).returnCarDetailsString(1);
        assert Objects.equals(mockedConnection.returnCarDetailsString(1), "ID: 1\nCar: Hyundai Sonata Ultimate\n" +
                "Cost: $35649\nMileage: 10323\nModel Year: 2021\nDealership: Guelph Hyundai\nCar Type: Convertible");
    }

    @Test
    @DisplayName("CarDetails: Basic Case (Unit Test with Mocked Database)")
    void returnCarDetailsUnitTest() throws SQLException {
        mockedConnection.returnCarDetails(1);
        when(mockedConnection.returnCarDetails(1)).thenReturn(returnCarInformation.returnCarDetails(1));
        verify(mockedConnection).returnCarDetails(1);

        HashMap<String, String> testMap = new HashMap<>();

        testMap.put("ID", "1");
        testMap.put("Car", "Hyundai Sonata Ultimate");
        testMap.put("Cost", "35649");
        testMap.put("Mileage", "10323");
        testMap.put("Model Year", "2021");
        testMap.put("Dealership", "Guelph Hyundai");
        testMap.put("Car Type", "Convertible");
        testMap.put("Photo", "https://www.hyundaionhuntclub.com/vimgs/usd00hyc031d022004/IOF_H600/ColourPhotoSample_0.jpg");

        assert Objects.equals(mockedConnection.returnCarDetails(1), testMap);
    }

    @Test
    @DisplayName("CarDetailString: Basic Case (Integration Test)")
    void returnCarDetailsString() throws SQLException {
        assert Objects.equals(returnCarInformation.returnCarDetailsString(1), "ID: 1\nCar: Hyundai Sonata Ultimate\n" +
                "Cost: $35649\nMileage: 10323\nModel Year: 2021\nDealership: Guelph Hyundai\nCar Type: Convertible");
    }

    @Test
    @DisplayName("CarDetails: Basic Case (Integration Test)")
    void returnCarDetails() throws SQLException {

        HashMap<String, String> testMap = new HashMap<>();

        testMap.put("ID", "1");
        testMap.put("Car", "Hyundai Sonata Ultimate");
        testMap.put("Cost", "35649");
        testMap.put("Mileage", "10323");
        testMap.put("Model Year", "2021");
        testMap.put("Dealership", "Guelph Hyundai");
        testMap.put("Car Type", "Convertible");
        testMap.put("Photo", "https://www.hyundaionhuntclub.com/vimgs/usd00hyc031d022004/IOF_H600/ColourPhotoSample_0.jpg");
        
        assert Objects.equals(testMap, returnCarInformation.returnCarDetails(1));
    }

}