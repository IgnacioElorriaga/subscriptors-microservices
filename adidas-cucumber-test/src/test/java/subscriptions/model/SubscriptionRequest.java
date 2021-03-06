package subscriptions.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
@Data
public class SubscriptionRequest implements Serializable {

	private String email;

	private String firstName;

	private String gender;

	private String dateOfBirth;

	private Boolean consent;

	private Integer newsletterId;

}
