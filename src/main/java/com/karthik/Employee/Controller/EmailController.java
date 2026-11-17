package com.karthik.Employee.Controller;

import com.karthik.Employee.Service.EmailService;
import com.karthik.Employee.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;

	// Sending a simple Email
	@PostMapping("/sendMail")
	public String
	sendMail(@RequestBody EmailDetails details)
	{
		return emailService.sendSimpleMail(details);
	}

	// Sending email with attachment
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(
			@RequestBody EmailDetails details)
	{
		return emailService.sendMailWithAttachment(details);
	}
}
