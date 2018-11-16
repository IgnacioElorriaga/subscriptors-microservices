package subscriptions;

import java.time.LocalDate;

import org.junit.Assert;

import com.adidas.subscription.service.dto.Gender;
import com.adidas.subscription.service.dto.Response;
import com.adidas.subscription.service.dto.SubscriptionRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SubscriptionSteps extends SpringBootBaseIntegrationTest{
	
	private SubscriptionRequest request;

	@Given("^As a User I want to subscribe to a newsletter$")
	public void as_a_User_I_want_to_subscribe_to_a_newsletter() throws Exception {
		request = SubscriptionRequest.builder().build();
	}

	@When("^I have provided all the information$")
	public void i_have_provided_all_the_information() throws Exception {
		setAllValues(request);
	}

	@Then("^the subscription was generated$")
	public void the_subscription_was_generated() throws Exception {
		Response response = post(request).getBody();
		Assert.assertNotNull(response); 
		Assert.assertNotNull(response.getSubscriptionId()); 
		
	}

	@When("^I have not provided all the information$")
	public void i_have_not_provided_all_the_information() throws Exception {
		setAllValues(request);
	}

	@Then("^I have received an error (\\d+)$")
	public void i_have_received_an_error(int arg1) throws Exception {
		Assert.assertEquals(400, arg1);
	}


	@When("^the parameter \"([^\"]*)\" was not provided$")
	public void the_parameter_was_not_provided(String arg1) throws Exception {
	    switch (arg1) {
		case "consent":
			request.setConsent(null);
			break;
		case "dateOfBirth":
			request.setDateOfBirth(null);
			break;
		case "newsletterId":
			request.setNewsletterId(null);
			break;
		case "gender":
			request.setGender(null);
			break;
		case "email":
			request.setEmail(null);
			break;
		case "firstName":
			request.setFirstName(null);
			break;
		default:
			break;
		}
	}
	
	
	private void setAllValues(SubscriptionRequest request) {
		request.setConsent(Boolean.TRUE);
		request.setDateOfBirth(LocalDate.now().toString());
		request.setEmail("user@cucumber.org");
		request.setFirstName("User Testing");
		request.setGender(Gender.FEMALE.name().toLowerCase());
		request.setNewsletterId(23);
	}
}