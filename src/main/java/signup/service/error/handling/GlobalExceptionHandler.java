package signup.service.error.handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> error(Exception any) {
		
		String errorMessage = "Opps! service catched the following exception! ->: ".concat(any.getMessage());

		Error sendErrorMessage = new Error(errorMessage);

		return ResponseEntity.status(400).body(sendErrorMessage); 
		
	}
}
