package com.wschoi.wsblog.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wschoi.wsblog.dao.ChatDAO;
import com.wschoi.wsblog.dto.ChatDTO;


@Repository
public class ChatService
{
	private static final Logger logPrinter = LoggerFactory.getLogger(ChatService.class);

	@Autowired
	ChatDAO chatDAO;
	
	public int submit(String encodedChatName, String encodedChatContent) throws UnsupportedEncodingException 
	{
		logPrinter.info("Checking Chat Data...");

		int result = -1;
		
		String chatName = URLDecoder.decode(encodedChatName, "UTF-8");
		String chatContent = URLDecoder.decode(encodedChatContent, "UTF-8");
		
		if(chatName == null || chatName.equals("") || chatContent == null || chatContent.equals("")){
			result = 0;
		} else {
			result = new ChatDAO().submit(chatName, chatContent);
		}
		return result;
	}
	
	public String fetch(String listType)
	{
		logPrinter.info("Loading Chat Data...");
		String result = "";
		
		if(listType == null || listType.equals("")) result = "";
		else if(listType.equals("today")) result = getToday();
		else 
		{
			try 
			{
				Integer.parseInt(listType);
				result = getID(listType);
			} 
			catch (Exception e) 
			{
				result = "";
			}
		}
		return result;
	}
	
	private String getToday() {

		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();

		ArrayList<ChatDTO> chatList = chatDAO.getChatList(new SimpleDateFormat("yyyy-mm-dd").format(new Date()));
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getChatID() + "\"}");
		String rs = result.toString();
		return rs;
	}

	private String getID(String chatID) {

		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(chatID);
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getChatID() + "\"}");
		String rs = result.toString();
		return rs;
	}
}
