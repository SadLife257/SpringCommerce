package com.midterm.springcommerce.Models;

import java.util.Calendar;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.midterm.springcommerce.Utilities.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(generator = "role-generator")
	@GenericGenerator(name = "role-generator", 
				    parameters = @Parameter(name = "prefix", value = "RL"), 
				    strategy = "com.midterm.springcommerce.Utilities.IdGenerator")
	private String id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
}
