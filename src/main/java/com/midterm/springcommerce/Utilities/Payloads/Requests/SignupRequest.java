package com.midterm.springcommerce.Utilities.Payloads.Requests;

import java.util.Set;

import jakarta.validation.constraints.*;

public class SignupRequest {
    @NotBlank(message = "The username is required.")
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank(message = "The email is required.")
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank(message = "The password is required.")
    @Size(min = 6, max = 40)
    private String password;
    
    @NotBlank(message = "The first name is required.")
    @Size(max = 50)
    private String firstname;
    @NotBlank(message = "The last name is required.")
    @Size(max = 50)
    private String lastname;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }

	@Override
	public String toString() {
		return "SignupRequest [username=" + username + ", email=" + email + ", role=" + role + ", password=" + password
				+ "]";
	}
    
}
