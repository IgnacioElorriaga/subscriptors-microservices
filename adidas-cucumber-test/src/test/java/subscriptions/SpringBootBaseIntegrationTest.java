package subscriptions;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import subscriptions.model.Response;
import subscriptions.model.SubscriptionRequest;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(properties = { "eureka.client.enabled:false", "spring.profiles.active:test",
		"feign.hystrix.enabled:false" })
public abstract class SpringBootBaseIntegrationTest {
	private final String SERVER_URL = "http://localhost";
    private final String SUBSCRIPTIONS_ENDPOINT = "/subscriptions";
 
    private RestTemplate restTemplate;
 
    
    
	protected int port = 8096;
 
    public SpringBootBaseIntegrationTest() {
        restTemplate = new RestTemplate();
    }
    private String subscriptionsEndpoint() {
        return SERVER_URL + ":" + port + SUBSCRIPTIONS_ENDPOINT;
    }
 
    ResponseEntity<Response> post(final SubscriptionRequest request) {
        return restTemplate.postForEntity(subscriptionsEndpoint(), request, Response.class);
    }
 
    SubscriptionRequest getContents() {
        return restTemplate.getForEntity(subscriptionsEndpoint(), SubscriptionRequest.class).getBody();
    }
 
    
}
