package com.kbdisplay.ls1710.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kbdisplay.ls1710.service.data.UserService;


@Controller("securityController")
public class SecurityController {

	final Logger			logger	= LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private UserService	userService;



}
