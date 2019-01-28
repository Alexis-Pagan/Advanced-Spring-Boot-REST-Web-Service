package signup.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Model class for Email value", description="Email sent by user will be save in this model")
@Entity
@Table(name="signon_users")
public class User {
	
	@ApiModelProperty(notes="Saves email addresses {data binding}")
	@Column(name="email_address")
	private String email;
	
	@ApiModelProperty(notes="primary key of signon_users table")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String email) {
		this.email = email;
	}
	public User() {
	
	}

	@JsonIgnore
	public int getId() {
		return id;
	}
	
	
}
