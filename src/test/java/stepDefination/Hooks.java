package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		stepDefination m = new stepDefination();
		if (stepDefination.placeId == null) {

			m.add_place_payload_is_available_with("arkav", "Bengali", "SODEPUR");
			m.api_is_called_with_post_http_request("AddPlaceAPI", "POST");
			m.is_called_and_check_created_place_id_maps_to_the("GetPlaceAPI", "arkav");
		}
	}
}
