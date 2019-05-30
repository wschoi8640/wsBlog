package com.wschi.wsblog.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

public class CrawlingUtil
{
	public static boolean isLongMonth(int month) 
	{
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || 
				month == 10 || month == 12) 
		{
			return true;
		}
		else 	return false;
	}
	public static String getDate(String date_input) 
	{
		try 
		{
			//set the date
			String startEnd_format = null;
			Date cur_date = new SimpleDateFormat("yyyyMMdd").parse(date_input);
			Calendar cal = Calendar.getInstance();
			cal.setTime(cur_date);
			
			//get 1st day of the week
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			Date start_date = cal.getTime();
			startEnd_format = new SimpleDateFormat("yyyyMMdd").format(start_date);

			//get last day of the week
			cal.set(Calendar.DAY_OF_WEEK, 7);
			Date end_date = cal.getTime();
			startEnd_format = startEnd_format + new SimpleDateFormat("yyyyMMdd").format(end_date);
	
	
			return startEnd_format;
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return "err";
	}
	public static String encode(String arg) throws IOException
	{
			return new String(arg.getBytes("euc-kr"), "euc-kr");
	}

	public static StringBuilder printMenu(String menuType, StringBuilder menu, List<String> menus, int tbody) throws ServletException, IOException 
	{
		if(tbody == 0)
		{
			menu.append("</br></br>");
			menu.append(System.lineSeparator());
			menu.append("<div class=\"container\">");
			menu.append(System.lineSeparator());
			menu.append("<div class=\"row\">");
			menu.append(System.lineSeparator());
			menu.append("<table class=\"table table-striped\" style=\"text-align : center; border : 1px solid #dddddd\">");
			menu.append(System.lineSeparator());
			menu.append("<thead>");
			menu.append(System.lineSeparator());
		}
		else if(tbody == 1)
		{
			menu.append("<tbody>");
			menu.append(System.lineSeparator());
		}
		menu.append("<tr>");
		menu.append(System.lineSeparator());

		if(tbody == 0) 
		{
			menu.append("<th style=\"background-color: #eeeeee; text-align : center\">"+encode(menuType)+"</th>");
			menu.append(System.lineSeparator());
		}
		else 
		{
			menu.append("<td>"+encode(menuType)+"</td>");
			menu.append(System.lineSeparator());
		}
		for(int i = 0; i < menus.size(); i++) 
		{
			//Split menu into multi column
			String [] temp_arr = menus.get(i).split(" ");
			if(tbody == 0) menu.append("<th style=\"background-color: #eeeeee; text-align : center\">");
			else menu.append("<td>");
			for(String temp : temp_arr) 
			{
				if(tbody == 0) 	menu.append(encode(temp));
				else menu.append("<p>" + encode(temp)+ "</p>");
			}
			if(tbody == 0) 
			{
				menu.append("</th>");
				menu.append(System.lineSeparator());
			}
			else
			{
				menu.append("</td>");
				menu.append(System.lineSeparator());
			}
		}
		menu.append("</tr>");
		menu.append(System.lineSeparator());
		if(tbody == 0)
		{
			menu.append("</thead>");
			menu.append(System.lineSeparator());
		}
		else if(tbody == -1)
		{
			menu.append("</tbody>");
			menu.append(System.lineSeparator());
			menu.append("</table>");
			menu.append(System.lineSeparator());
			menu.append("</div>");
			menu.append(System.lineSeparator());
			menu.append("</div>");
			menu.append(System.lineSeparator());
		}
		return menu;
	}
}
