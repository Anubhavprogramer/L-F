package com.lostandfound.dto;

import com.lostandfound.model.Status;

import jakarta.validation.constraints.NotNull;

public class UpdateItemStatusRequest {
	@NotNull
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
