package com.wschoi.wsblog.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

@Repository
public class UtilService
{
	HttpServletResponse response;
	
	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}
	
	public void alert(String msg) throws IOException 
	{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println(msg);
		script.println("</script>");
	}
}
