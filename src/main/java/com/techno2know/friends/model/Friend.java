package com.techno2know.friends.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	@JsonProperty("first-name")
	private String firstName;

	@NotBlank
	@JsonProperty("last-name")
	private String lastName;

	int age;

	@JsonIgnore
	private boolean married;

	@JsonManagedReference
	@OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
	private Set<Address> addresses;

	public Friend() {

	}

	public Friend(int id, @NotBlank String firstName, @NotBlank String lastName, int age, boolean married,
			Set<Address> addresses) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.married = married;
		this.addresses = addresses;
	}
	
	
	public Friend(int id, @NotBlank String firstName, @NotBlank String lastName, int age, boolean married) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.married = married;
		
	}


	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddress(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

}
