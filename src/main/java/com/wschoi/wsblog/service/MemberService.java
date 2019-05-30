package com.wschoi.wsblog.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wschoi.wsblog.dao.UserDAO;
import com.wschoi.wsblog.dto.UserDTO;

@Repository
public class MemberService 
{
	private static final Logger logPrinter = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	UserDAO userDAO;
	
	public int login(String encodedUserID, String encodedUserPW) throws ServletException, IOException 
	{
			
		logPrinter.info("checking Login Data");
		
		String userID = URLDecoder.decode(encodedUserID, "UTF-8");
		String userPW = URLDecoder.decode(encodedUserPW, "UTF-8");
		int result = userDAO.login(userID, userPW);
		
		return result;
	}
	
	public int join(String encodedUserID, String encodedUserPassword, String encodedUserName, 
			String encodedUserGender, String encodedUserEmail, String encodedUserEntryCode) throws UnsupportedEncodingException
	{
		int result = 0;
		
		String ackEntryCode = "11101112";
		
		String userID = URLDecoder.decode(encodedUserID,"UTF-8");
		String userPassword = URLDecoder.decode(encodedUserPassword, "UTF-8");
		String userName = URLDecoder.decode(encodedUserName, "UTF-8");
		String userGender = URLDecoder.decode(encodedUserGender, "UTF-8");
		String userEmail = URLDecoder.decode(encodedUserEmail, "UTF-8");
		String userEntryCode = URLDecoder.decode(encodedUserEntryCode, "UTF-8");
		
		logPrinter.info("checking Join Data");
		
		if(!userEntryCode.equals(ackEntryCode))
		{
			return 0; // no permission
		}
		else
		{
			UserDAO userDAO = new UserDAO();
			UserDTO user = new UserDTO();
			
			user.setUserID(userID);
			user.setUserPassword(userPassword);
			user.setUserName(userName);
			user.setUserGender(userGender);
			user.setUserEmail(userEmail);
			
			result = userDAO.join(user);
		}
		
		return result;
	}
}
