/*
 * REST Training v1.0
 * 
 * @author Alexis Garcia - Service Architecture Software Developer at Evertec, Inc.
 * 
 * Date: 01/27/2019
 * 
 */

package signup.service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import signup.service.media.types.MediaTypes;
import signup.service.models.Response;
import signup.service.models.User;
import signup.service.repository.HibernateRepository;
import signup.service.validations.Validation;

@RestController
@Api(value="REST Endpoint Controller")
public class EmailController {

	@Autowired
	private HibernateRepository repository;

	private Response report = new Response();

	@GetMapping(path="/users/emails-addresses/emailController", produces = "application/json") 
	@ApiOperation(value="HTTP Method Return All Emails in MySQL")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successful retrieve of e-mail addresses", response = User.class), @ApiResponse(code = 404, message = "Handler not supported")})
	public ResponseEntity<List<User>> readEmailAddress() {

		List<User> emails = new ArrayList<User>();

		Iterable<User> all = repository.findAll();

		for(User var: all) {
			emails.add(var);
		}

		if(all.equals(null)) {
			report.setMessage("No emails retrieved.");
			return ResponseEntity.status(200).headers(MediaTypes.genericHeaders()).body(emails);
		}

		return ResponseEntity.status(200).headers(MediaTypes.genericHeaders()).body(emails);
	}

	@PostMapping(path="/users/emails-addresses/emailController/{id}", produces = "application/json") 
	@ApiOperation(value="HTTP Method Create All Emails Addresses in MySQL")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Bad Request", response = Response.class), @ApiResponse(code = 409, message = "Email exist in MySQL database"), @ApiResponse(code = 201, message = "E-mail address created")})
	public ResponseEntity<Resource<User>> createEmailAddress(@RequestBody User userDataSent, @PathVariable("id") int id) {

		boolean result = Validation.doValid(userDataSent);

		if(id >= 0 & result == false) {

			Optional<User> recordToVerify = repository.findById(id);

			if(recordToVerify.isPresent()) {
				return ResponseEntity.status(409).build();
			}

		} else {
			return ResponseEntity.status(400).build();
		}

		repository.save(userDataSent);

		User userEntity = new User(userDataSent.getEmail());

		Resource<User> user_resource = new Resource<User>(userEntity);

		Link linkHateoas = ControllerLinkBuilder.linkTo(EmailController.class).slash("/users/emails-addresses/emailController").withSelfRel();
		user_resource.add(linkHateoas);

		return ResponseEntity.status(201).headers(MediaTypes.postHeaders()).body(user_resource);
	}

	@DeleteMapping(path="/users/emails-address/emailController/{id}", produces = "application/json")
	@ApiOperation(value="HTTP Method Delete All Emails Addresses in MySQL")
	public ResponseEntity<Response> deleteEmailAddresses(@PathVariable("id") int id) {

		Optional<User> recordToDelete = repository.findById(id);

		System.out.println("Id found: ".concat(String.valueOf(recordToDelete.get().getId())));

		if(recordToDelete.isPresent() & recordToDelete.get().getId() >=0) {

			repository.deleteById(id);

			report.setMessage("Email address with id: " + id + " has been removed!");

			return ResponseEntity.status(HttpStatus.OK).headers(MediaTypes.genericHeaders()).body(report);

		} else { 
			report.setMessage("Address could not be removed!");
			return ResponseEntity.status(HttpStatus.OK).headers(MediaTypes.genericHeaders()).body(report);
		}
	}

	@PutMapping(path="/users/emails-address/emailController/{id}")
	@ApiOperation(value="HTTP Method Update All Emails Addresses in MySQL")
	public ResponseEntity<Response> putEmailAddresses(@RequestBody User user, @PathVariable("id") int id) {

		Optional<User> record = repository.findById(id);

		System.out.println("Record found: ".concat(record.get().getEmail()));

		if(!(user.getEmail().isEmpty()) & id >= 0) {

			if(record.isPresent() == true) {

				record.get().setEmail(user.getEmail());

				System.out.println("Email to update: ".concat(record.get().getEmail()));

				String eString = record.get().getEmail();

				System.out.println("Email to set up: ".concat(eString));

				User usr = new User();

				repository.save(usr).setEmail(eString);
				System.out.println("Record save: ".concat(record.get().getEmail()));

				report.setMessage("Email address updated.");
				return ResponseEntity.status(HttpStatus.OK).headers(MediaTypes.genericHeaders()).body(report);
			}
		} 
			report.setMessage("Record not updated.");
			return ResponseEntity.status(HttpStatus.OK).headers(MediaTypes.genericHeaders()).body(report);
	}
}
