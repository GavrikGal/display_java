package com.kbdisplay.ls1710.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kbdisplay.ls1710.view.dataJournal.web.form.Message;

@RequestMapping("/security")
@Controller
public class SecurityController {

	final Logger			logger	= LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private MessageSource	messageSource;

	@RequestMapping("/loginfail")
	public String loginFail(Model uiModel, RedirectAttributes redirectAttributes, Locale locale) {

		logger.info("Login failed detected");
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("error", messageSource.getMessage("message_login_fail",
						new Object[] {}, locale)));
		System.out.println("suka");
		return "redirect:/measurements";

	}

}