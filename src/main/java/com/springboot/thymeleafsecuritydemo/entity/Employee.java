package com.springboot.thymeleafsecuritydemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotNull(message = "Field is required")
	@Size(min = 2, message = "Min characters is 2")
	@Column(name="first_name")
	private String firstName;

	@NotNull(message = "Field is required")
	@Size(min = 2, message = "Min characters is 2")
	@Column(name="last_name")
	private String lastName;

	@NotNull(message = "Field is required")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@Column(name="email")
	private String email;
	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
}











