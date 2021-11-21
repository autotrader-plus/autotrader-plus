package packages.informationmanipulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static java.lang.Math.min;

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
            put("Photo", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/screen-shot-2019-10-03-at-9-52-03-pm-1570154537.png");
        }});
        testList.add(1, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Hyundai Sonata Ultimate");
            put("Mileage", "10323");
            put("Car Type", "Convertible");
            put("ID", "1");
            put("Cost", "35649");
            put("Dealership", "Guelph Hyundai");
            put("Photo", "https://www.hyundaionhuntclub.com/vimgs/usd00hyc031d022004/IOF_H600/ColourPhotoSample_0.jpg");
        }});
        testList.add(2, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Mazda CS-5 GT");
            put("Mileage", "8923");
            put("Car Type", "Convertible");
            put("ID", "2");
            put("Cost", "40779");
            put("Dealership", "Dixie Mazdda");
            put("Photo", "https://images.wheels.ca/wp-content/uploads/2019/02/Review-2019-Mazda-CX-5-GT-12.jpg");
        }});
        testList.add(3, new HashMap<>() {{
            put("Model Year", "2018");
            put("Car", "BMW M3 CS");
            put("Mileage", "19950");
            put("Car Type", "Convertible");
            put("ID", "3");
            put("Cost", "91888");
            put("Dealership", "Auto|One");
            put("Photo", "https://cdn.bmwblog.com/wp-content/uploads/2020/11/2022-bmw-m3-cs-g80-00.jpg");
        }});
        testList.add(4, new HashMap<>() {{
            put("Model Year", "2017");
            put("Car", "Honda Pillot 4WD");
            put("Mileage", "145174");
            put("Car Type", "SUV");
            put("ID", "4");
            put("Cost", "28560");
            put("Dealership", "Barrie Honda");
            put("Photo", "https://o.aolcdn.com/images/dims3/GLOB/legacy_thumbnail/800x450/format/jpg/quality/85/http://www.blogcdn.com/www.autoblog.com/media/2011/04/01-2011-honda-pilot-opt.jpg");
        }});
        testList.add(5, new HashMap<>() {{
            put("Model Year", "2014");
            put("Car", "Lexus GX 460");
            put("Mileage", "114781");
            put("Car Type", "SUV");
            put("ID", "5");
            put("Cost", "41998");
            put("Dealership", "Porche of London");
            put("Photo", "https://media.ed.edmunds-media.com/lexus/gx-460/2022/oem/2022_lexus_gx-460_4dr-suv_base_fq_oem_1_1600.jpg");
        }});
        testList.add(6, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Dodge Durango");
            put("Mileage", "22709");
            put("Car Type", "SUV");
            put("ID", "6");
            put("Cost", "64724");
            put("Dealership", "Galt");
            put("Photo", "https://www.autotrader.com/wp-content/uploads/2021/09/2022-dodge-durango-front-right-driving.jpg");
        }});
        testList.add(7, new HashMap<>() {{
            put("Model Year", "2018");
            put("Car", "Mercedes-Benz C-Class C 300");
            put("Mileage", "33390");
            put("Car Type", "Sedan");
            put("ID", "7");
            put("Cost", "37599");
            put("Dealership", "Special Interest Automobiles");
            put("Photo", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2022-mercedes-benz-c-class-118-1613767289.jpg?crop=0.781xw:0.781xh;0.145xw,0.219xh&resize=640:*");
        }});
        testList.add(8, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Honda Accord Hybrid");
            put("Mileage", "51905");
            put("Car Type", "Sedan");
            put("ID", "8");
            put("Cost", "35800");
            put("Dealership", "Special Interest Automobiles");
            put("Photo", "https://media.ed.edmunds-media.com/honda/accord-hybrid/2018/oem/2018_honda_accord-hybrid_sedan_touring_fq_oem_1_600.jpg");
        }});
        testList.add(9, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Hyundai Santa Fe");
            put("Mileage", "8809");
            put("Car Type", "SUV");
            put("ID", "9");
            put("Cost", "22988");
            put("Dealership", "Special Interest Automobiles");
            put("Photo", "https://s.aolcdn.com/os/ab/_cms/2020/06/03024835/2020-hyundai-santa-fe-official-16.jpg");
        }});
        testList.add(10, new HashMap<>() {{
            put("Model Year", "2016");
            put("Car", "Lincoln MKX Rserve AWD");
            put("Mileage", "64950");
            put("Car Type", "Sedan");
            put("ID", "10");
            put("Cost", "21899");
            put("Dealership", "Airport Ford");
            put("Photo", "https://media.ed.edmunds-media.com/lincoln/mkx/2017/oem/2017_lincoln_mkx_4dr-suv_reserve_fq_oem_1_1600.jpg");
        }});
        testList.add(11, new HashMap<>() {{
            put("Model Year", "2020");
            put("Car", "Hyundai Elantra");
            put("Mileage", "27701");
            put("Car Type", "Sedan");
            put("ID", "11");
            put("Cost", "20995");
            put("Dealership", "Attrell Hyundai");
            put("Photo", "https://www.cnet.com/a/img/resize/c04ff4d7e00e4f669ed109ff24f6e2528320fc3c/hub/2021/03/05/6562dfd2-d038-44b2-8a98-dd4bae1aae3d/2021-hyundai-elantra-sel-1.jpg?auto=webp&width=768");
        }});
        testList.add(12, new HashMap<>() {{
            put("Model Year", "2014");
            put("Car", "Jeep Grand Cherokee");
            put("Mileage", "207599");
            put("Car Type", "SUV");
            put("ID", "12");
            put("Cost", "19900");
            put("Dealership", "Daleo Motors");
            put("Photo", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2022-jeep-grand-cherokee-summit-reserve-4xe-114-1632861119.jpg");
        }});
        testList.add(13, new HashMap<>() {{
            put("Model Year", "2009");
            put("Car", "Mercedes-Benz S-Class");
            put("Mileage", "108731");
            put("Car Type", "Sedan");
            put("ID", "13");
            put("Cost", "16888");
            put("Dealership", "Northline");
            put("Photo", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-mercedes-benz-s-class-110-1598983698.jpg");
        }});
        testList.add(14, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Mistubishi Outlander ES");
            put("Mileage", "4300");
            put("Car Type", "SUV");
            put("ID", "14");
            put("Cost", "30152");
            put("Dealership", "Dixie Mitsubishi");
            put("Photo", "https://media.ed.edmunds-media.com/mitsubishi/outlander/2019/oem/2019_mitsubishi_outlander_4dr-suv_gt_fq_oem_1_1600.jpg");
        }});
        testList.add(15, new HashMap<>() {{
            put("Model Year", "2020");
            put("Car", "KIA Sorento LX");
            put("Mileage", "20130");
            put("Car Type", "SUV");
            put("ID", "15");
            put("Cost", "28000");
            put("Dealership", "Eagle Motors");
            put("Photo", "https://s.aolcdn.com/commerce/autodata/images/USC90KIS021A021001_2.jpg");
        }});
        testList.add(16, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Honda Civic Sedan LX");
            put("Mileage", "12039");
            put("Car Type", "Sedan");
            put("ID", "16");
            put("Cost", "26328");
            put("Dealership", "Class Honda");
            put("Photo", "https://s.aolcdn.com/commerce/autodata/images/USC90HOC021A121001.jpg");
        }});
        testList.add(17, new HashMap<>() {{
            put("Model Year", "2016");
            put("Car", "Toyota RAV4 LE");
            put("Mileage", "109500");
            put("Car Type", "SUV");
            put("ID", "17");
            put("Cost", "20478");
            put("Dealership", "Vaughan Chrysler");
            put("Photo", "https://s.aolcdn.com/commerce/autodata/images/USD00TOS112A021001.jpg");
        }});
        testList.add(18, new HashMap<>() {{
            put("Model Year", "2021");
            put("Car", "Mercedes-Benz E450");
            put("Mileage", "12389");
            put("Car Type", "Wagon");
            put("ID", "18");
            put("Cost", "70128");
            put("Dealership", "Mercedes-Benz Oakville");
            put("Photo", "https://www.motortrend.com/uploads/sites/5/2020/03/2021-Mercedes-Benz-E-Class-04.jpg?fit=around%7C875:492");
        }});
        testList.add(19, new HashMap<>() {{
            put("Model Year", "2017");
            put("Car", "BMW 3 Series 328d");
            put("Mileage", "42201");
            put("Car Type", "Wagon");
            put("ID", "19");
            put("Cost", "33850");
            put("Photo", "https://hips.hearstapps.com/hmg-prod/amv-prod-cad-assets/images/13q1/494258/2014-bmw-328d-diesel-sedan-first-drive-review-car-and-driver-photo-510760-s-original.jpg?fill=2:1&resize=1200:*");
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
            put("Photo", "https://s.aolcdn.com/commerce/autodata/images/USD00RMT11BD021001.jpg");
        }});
        testList.add(21, new HashMap<>() {{
            put("Model Year", "2019");
            put("Car", "Chevrolet Silverado 1500");
            put("Mileage", "28470");
            put("Car Type", "Truck");
            put("ID", "21");
            put("Cost", "46990");
            put("Dealership", "canadadrives");
            put("Photo", "https://www.chevrolet.com/content/dam/chevrolet/na/us/english/index/vehicles/2021/trucks/silverado-ld/mov/colorizer/02-images/2021-silverado1500-1sp-gaz-colorizer.jpg?imwidth=960");
        }});
        testList.add(22, new HashMap<>() {{
            put("Model Year", "2017");
            put("Car", "Chevrolet Colorado WT");
            put("Mileage", "27899");
            put("Car Type", "Truck");
            put("ID", "22");
            put("Cost", "27995");
            put("Dealership", "Wilson's");
            put("Photo", "https://www.chevrolet.ca/content/dam/chevrolet/na/canada/english/index/trucks/2021-colorado/colorizer/01-images/2021-colorado%20-4z7-gaz-colorizer.jpg?imwidth=960");
        }});
        testList.add(23, new HashMap<>() {{
            put("Model Year", "2016");
            put("Car", "Infiniti QX50");
            put("Mileage", "71935");
            put("Car Type", "Wagon");
            put("ID", "23");
            put("Cost", "25895");
            put("Dealership", "Dixie Infiniti");
            put("Photo", "https://www.motortrend.com/uploads/sites/10/2018/01/2019-Infiniti-QX50-front-three-quarter-in-motion-05.jpg");
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

        assert Objects.equals(testList, ReturnMultipleCars.returnFilteredCars(filter));
    }

}