/*
 * custom validation class that will validate if user email provided is null (no value) or empty (empty strings)
 */

package signup.service.validations;

import signup.service.models.User;

public class Validation {
	
	public static boolean doValid(User user) {
		if(user.getEmail().equals(null) || user.getEmail().isEmpty()) {
			return true;
		}
		return false;
	}
}
