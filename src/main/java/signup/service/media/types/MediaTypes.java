package signup.service.media.types;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class MediaTypes {
	
	static private HttpHeaders httpHeader = new HttpHeaders();
	
	static public HttpHeaders genericHeaders() {
		
		httpHeader.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		httpHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		
		return httpHeader;
	}

	static public HttpHeaders postHeaders() {
		
		httpHeader.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		httpHeader.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpHeader.add(HttpHeaders.LOCATION, "/users/emails-addresses/emailController");

		return httpHeader;
	}
}
