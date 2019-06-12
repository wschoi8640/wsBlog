package com.wschoi.wsblog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wschoi.wsblog.service.ChatService;


@Controller
public class ChatController
{
	private static final Logger logPrinter = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	ChatService chatService;
	
	@PostMapping("/chatSubmit")
	public void chatSubmit(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("chatContent") String chatContent,
			@RequestParam("chatName") String chatName) throws IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		
		int result = chatService.submit(chatName, chatContent);
		
		if (result == 0)
		{
			logPrinter.info("Chat Submit failed - some inputs missing");
			response.getWriter().write("0");
		}
		else if (result == 1)
		{
			logPrinter.info("Chat Submit Successful");
			response.getWriter().write("1");
		}
		else
		{
			logPrinter.info("Chat Submit failed - DB Error");
			response.getWriter().write("-1");
		}
	}
	
	@RequestMapping(value = "/chatFetch", method = {RequestMethod.POST, RequestMethod.GET})
	public void chatFetch(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("listType") String listType) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		
		String result = chatService.fetch(listType);
		if(!result.equals(""))
		{
			logPrinter.info("Chat Fetch Successful");
			response.getWriter().write(result);
		}
		else
		{
			response.getWriter().write("");
		}
	}
}
