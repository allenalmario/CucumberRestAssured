package testData;

import io.cucumber.java.hu.De;
import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address) {

        AddPlace place = new AddPlace();

        place.setAccuracy(50);
        place.setAddress(address);
        place.setLanguage(language);
        place.setPhone_number("+1-123-45678");
        place.setWebsite("www.deezknee.com");
        place.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("Water Park");
        myList.add("Amusement Park");
        place.setTypes(myList);
        Location location = new Location();
        location.setLat(-12.412515);
        location.setLng(90.123045);
        place.setLocation(location);

        return place;

    }

    public DeletePlace deletePlacePayload(String placeId) {

        DeletePlace place = new DeletePlace();
        place.setPlace_id(placeId);

        return place;

    }

}
