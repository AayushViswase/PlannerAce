package Payload.Request;

import lombok.Data;

@Data
public class InterviewerRequest {

	private String name;
	private String email;
	private String password;
	private String phoneNumber;
}
