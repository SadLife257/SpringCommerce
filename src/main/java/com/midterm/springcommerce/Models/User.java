package com.midterm.springcommerce.Models;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {
	public User(String username2, String email2, String encode) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(generator = "user-generator")
	@GenericGenerator(name = "user-generator", parameters = @Parameter(name = "prefix", value = "AC"), strategy = "com.midterm.springcommerce.Utilities.IdGenerator")
	private String id;
	private String firstname;
	private String lastname;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@NotBlank
	@Size(max = 120)
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet();
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private ShoppingCart cart;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedAt;
}
