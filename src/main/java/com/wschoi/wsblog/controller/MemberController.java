package com.wschoi.wsblog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wschoi.wsblog.service.LoginService;

@Controller
public class MemberController 
{
		private static final Logger logPrinter = LoggerFactory.getLogger(MemberController.class);

		@Autowired
		LoginService loginService;
		
		@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
		public String redirectToIndex(Model model,
				HttpServletResponse response,
				@RequestParam("userID") String userID, 
				@RequestParam("userPassword") String userPW) throws ServletException, IOException
		{
				logPrinter.info("checking Login Data");
				loginService.login(model, response, userID, userPW);
				
				return "";
		}
}
