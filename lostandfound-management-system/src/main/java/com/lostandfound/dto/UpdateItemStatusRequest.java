package com.lostandfound.dto;

import com.lostandfound.model.Status;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UpdateItemStatusRequest {
	@NotNull
	private Status status;
	
	private String receiverName;
	
	@Email
    private String receiverEmail;

	public Status getStatus() {
		return status;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
