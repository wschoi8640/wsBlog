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

import com.wschoi.wsblog.service.MemberService;

@Controller
public class MemberController
{
	private static final Logger logPrinter = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public void doLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userID") String userID, @RequestParam("userPassword") String userPW)
			throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");

		HttpSession session = request.getSession();

		int result = memberService.login(userID, userPW);

		if (result == 1)
		{
			logPrinter.info("Login Successful");
			session.setAttribute("userID", userID);
			response.getWriter().write("1");

		}
		if (result == 0)
		{
			logPrinter.info("Login Failed - wrong password");
			response.getWriter().write("0");
		}
		if (result == -1)
		{
			logPrinter.info("Login Failed - no such ID");
			response.getWriter().write("-1");
		}
		if (result == -2)
		{
			logPrinter.info("Login Failed - DB error");
			response.getWriter().write("-2");
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
	
	@RequestMapping(value = "/doJoin", method = RequestMethod.POST)
	public void doJoin(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("userID") String userID,
			@RequestParam("userPassword") String userPassword,
			@RequestParam("userName") String userName,
			@RequestParam("userGender") String userGender,
			@RequestParam("userEmail") String userEmail,
			@RequestParam("entryCode") String userEntryCode) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		
		int result = memberService.join(userID, userPassword, userName, userGender, userEmail, userEntryCode);
		
		if (result == 0)
		{
			logPrinter.info("Join Failed - Wrong Entry Code");
			response.getWriter().write("0");
		}
		else if (result == -1)
		{
			logPrinter.info("Join Failed - ID already Exists");
			response.getWriter().write("-1");
		}
		else if (result == -2)
		{
			logPrinter.info("Join Failed - DB Error");
			response.getWriter().write("-2");
		}
		else if (result == 1)
		{
			logPrinter.info("Join Successful");
			response.getWriter().write("1");
			
		}
	}
}
