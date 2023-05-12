package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Message {
	@Id
	private long messageId;
	private String sendEmail;
	private String receiveEmail;
	
	private String content;

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Message(long messageId, String sendEmail, String receiveEmail, String content) {
		super();
		this.messageId = messageId;
		this.sendEmail = sendEmail;
		this.receiveEmail = receiveEmail;
		this.content = content;
	}

	public Message() {
		super();
	}
	
	
}
