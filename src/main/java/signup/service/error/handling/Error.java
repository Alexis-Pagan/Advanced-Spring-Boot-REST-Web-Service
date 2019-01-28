package signup.service.error.handling;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Error Model Class", description="This model will be used to report error in human-readable format")
public class Error {
	
	@ApiModelProperty(notes="Error message")
	private String message;
	
	public Error(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
