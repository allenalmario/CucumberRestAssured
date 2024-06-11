package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        // write a code that will give you place id
        // execute this code only when place id is null

        PlaceStepDefinitions placeStepDefinitions = new PlaceStepDefinitions();

        if (PlaceStepDefinitions.place_id==null) {

            placeStepDefinitions.addPlacePayloadWith("Hello Puppy", "Japanese", "Nami St");
            placeStepDefinitions.user_calls_with_http_request("AddPlaceAPI", "POST");
            placeStepDefinitions.verify_place_id_created_maps_to_using("Hello Puppy", "GetPlaceAPI");

        }

    }

}
