package com.wschoi.wsblog.service;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.wschoi.wsblog.dao.UserDAO;

@Repository
public class LoginService 
{
		private static final Logger logPrinter = LoggerFactory.getLogger(LoginService.class);
	
		@Autowired
		UserDAO userDAO;
		
		public int login(Model model,
				HttpServletResponse response, String encodedUserID, String encodedUserPW) throws ServletException, IOException 
		{
				
				String userID = URLDecoder.decode(encodedUserID, "UTF-8");
				String userPW = URLDecoder.decode(encodedUserPW, "UTF-8");
				int result = userDAO.login(userID, userPW);
				
				if(result == 1) 
				{	
					logPrinter.info("Login Successful");
					model.addAttribute("userID", userID);
					response.getWriter().write("1"); //login suc
				}
				if(result == 0)
				{
					logPrinter.info("Login Failed - wrong password");
					response.getWriter().write("0"); //wrong pw
				}
				if(result == -1)
				{
					logPrinter.info("Login Failed - no such ID");
					response.getWriter().write("-1"); //no such ID
				}
				if(result == -2)
				{
					logPrinter.info("Login Failed - DB error");
					response.getWriter().write("-2"); // db Error
				}
				return result;
		}
}
