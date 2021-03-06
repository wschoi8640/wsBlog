package com.wschoi.wsblog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtilController 
{
	private static final Logger logPrinter = LoggerFactory.getLogger(UtilController.class);

	@PostMapping("/setDarkMode")
	public void setDarkMode(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
				
		if(session.getAttribute("darkMode") == null) 
		{
			session.setAttribute("darkMode", -1);
			response.getWriter().write("-1");
			logPrinter.info("Switching to Dark Mode");
		}
		else if((int)session.getAttribute("darkMode") == 1 || session.getAttribute("darkMode").equals("1")) 
		{
			session.setAttribute("darkMode", -1);
			response.getWriter().write("-1");
			logPrinter.info("Switching to Dark Mode");
		}
		else if((int)session.getAttribute("darkMode") == -1 || session.getAttribute("darkMode").equals("-1")) 
		{
			session.setAttribute("darkMode", 1);
			response.getWriter().write("1");
			logPrinter.info("Switching to Bright Mode");
		}
	}
}
