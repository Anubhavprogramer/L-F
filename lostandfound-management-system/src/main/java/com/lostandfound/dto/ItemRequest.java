package com.lostandfound.dto;


import jakarta.validation.constraints.NotBlank;
public class ItemRequest {
	@NotBlank
	private String name;
	private String description;
	
	@NotBlank
	private String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
