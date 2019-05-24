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
import org.springframework.web.bind.annotation.ResponseBody;

import com.wschoi.wsblog.dao.UserDAO;

@Repository
public class LoginService 
{
		private static final Logger logPrinter = LoggerFactory.getLogger(LoginService.class);
	
		@Autowired
		UserDAO userDAO;
		
		public int login(String encodedUserID, String encodedUserPW) throws ServletException, IOException 
		{
				
				String userID = URLDecoder.decode(encodedUserID, "UTF-8");
				String userPW = URLDecoder.decode(encodedUserPW, "UTF-8");
				int result = userDAO.login(userID, userPW);
				
				return result;
		}
}
