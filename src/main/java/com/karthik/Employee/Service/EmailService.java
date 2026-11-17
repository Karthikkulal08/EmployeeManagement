package com.karthik.Employee.Service;
import com.karthik.Employee.model.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);

	// Method
	// To send an email with attachment
	String sendMailWithAttachment(EmailDetails details);
}
