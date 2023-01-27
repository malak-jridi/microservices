package org.comAndDev.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class EmailServiceApplication {
	@Autowired
	private  EmailSenderService service;

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		service.sendEmailWithAttachment("garalimaha9@gmail.com",
									"This is the Email Body...",
									"This is the Email Subject"
										);
	}
}
