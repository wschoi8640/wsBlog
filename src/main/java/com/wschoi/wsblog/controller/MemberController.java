package com.wschoi.wsblog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public void doLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userID") String userID, @RequestParam("userPassword") String userPW)
			throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");

		HttpSession session = request.getSession();

		logPrinter.info("checking Login Data");

		int result = loginService.login(userID, userPW);

		if (result == 1)
		{
			logPrinter.info("Login Successful");
			session.setAttribute("userID", userID);
			response.getWriter().write("1"); // login suc
		}
		if (result == 0)
		{
			logPrinter.info("Login Failed - wrong password");
			response.getWriter().write("0"); // wrong pw
		}
		if (result == -1)
		{
			logPrinter.info("Login Failed - no such ID");
			response.getWriter().write("-1"); // no such ID
		}
		if (result == -2)
		{
			logPrinter.info("Login Failed - DB error");
			response.getWriter().write("-2"); // db Error
		}
	}
	
	@RequestMapping(value = "/doLogout", method = RequestMethod.GET)
	public String doLogout(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		session.invalidate();
		
		logPrinter.info("Log Out successful");
		
		return "index"; 
	}
}
