package com.wschoi.wsblog.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wschoi.wsblog.dao.BbsDAO;
import com.wschoi.wsblog.dto.BbsDTO;

@Repository
public class BbsService
{
	private static final Logger logPrinter = LoggerFactory.getLogger(BbsService.class);

	@Autowired
	BbsDAO bbsDAO;

	public String getArticleList(int bbsID, HttpSession session)
	{
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");

		BbsDTO bbs = new BbsDAO().getBbs(bbsID);
		result.append("[{\"value\": \"" + bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll("\n", "<br>") + "\"},");
		result.append("{\"value\": \"" + bbs.getUserID() + "\"},");
		result.append("{\"value\": \"" + bbs.getBbsDate().substring(0, 11) + bbs.getBbsDate().substring(11, 13)
				+ "½Ã" + bbs.getBbsDate().substring(14, 16) + "ºÐ" + "\"},");
		result.append("{\"value\": \"" + bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll("\n", "<br>") + "\"}]");
		result.append("], \"last\":\"" + 0 + "\"}");

		String rs = result.toString();
		return rs;
	}

	public String getBbsDTOList(int pageNumber)
	{
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");

		ArrayList<BbsDTO> list = bbsDAO.getList(pageNumber);
		System.out.println("1st : " + pageNumber);
		for (int i = 0; i < list.size(); i++)
		{
			result.append("[{\"value\": \"" + list.get(i).getBbsID() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getArrangedData() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getUserID() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getBbsTime() + "\"}]");
			if (i != list.size() - 1)
				result.append(",");
		}
		result.append("], \"last\":\"" + pageNumber + "\"}");
		String rs = result.toString();
		
		return rs;
	}

	public int write(String encodedMyTitle, String userID, String encodedMyContent)
			throws UnsupportedEncodingException
	{
		logPrinter.info("checking write Data");

		String myTitle = URLDecoder.decode(encodedMyTitle, "UTF-8");
		String myContent = URLDecoder.decode(encodedMyContent, "UTF-8");

		int result = bbsDAO.write(myTitle, userID, myContent);

		return result;
	}

	public int update(String encodedMyTitle, int bbsID, String encodedMyContent) throws UnsupportedEncodingException
	{
		logPrinter.info("checking update Data");
		String myTitle = URLDecoder.decode(encodedMyTitle, "UTF-8");
		String myContent = URLDecoder.decode(encodedMyContent, "UTF-8");
		System.out.println(myContent);
		int result = bbsDAO.update(bbsID, myTitle, myContent);

		return result;
	}

	public int delete(int bbsID, String userID, HttpServletResponse response)
	{
		int result = -1;
		BbsDTO bbs = new BbsDAO().getBbs(bbsID);
		if (!userID.equals(bbs.getUserID()))
		{
			try
			{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('not allowed!')");
				script.println("location.href = 'bbs'");
				script.println("</script>");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else
		{
			BbsDAO bbsDAO = new BbsDAO();
			result = bbsDAO.delete(bbsID);
		}
		return result;
	}
}
