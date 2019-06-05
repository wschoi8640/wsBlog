package com.wschoi.wsblog.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.wschoi.wsblog.dao.BbsDAO;
import com.wschoi.wsblog.dto.BbsDTO;

@Repository
public class BbsService
{
	@Autowired
	BbsDAO bbsDAO;

	public int setPageNumber(Model model)
	{
		int pageNumber = 1;
		Map modelMap = model.asMap();
		if(modelMap.get("pageNumber") != null)
		{
			pageNumber = Integer.parseInt((String) modelMap.get("pageNumber"));
		}
		model.addAttribute("pageNumber", pageNumber);
		
		return pageNumber;
	}

	public void setNextPage(Model model, int pageNumber)
	{
		boolean nextPage = false;
		nextPage = bbsDAO.nextPage(pageNumber);
		model.addAttribute("nextPage", nextPage);
	}

	public String getArticleList(int bbsID, HttpSession session)
	{
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		BbsDTO bbs = new BbsDAO().getBbs(bbsID);
		result.append("[{\"value\": \"" + bbs.getBbsTitle().replaceAll(" ","&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>") + "\"},");
		result.append("{\"value\": \"" + bbs.getUserID() + "\"},");
		result.append("{\"value\": \"" + bbs.getBbsDate().substring(0,11) + bbs.getBbsDate().substring(11,13) + "½Ã" + bbs.getBbsDate().substring(14,16) + "ºÐ" + "\"},");
		result.append("{\"value\": \"" + bbs.getBbsContent().replaceAll(" ","&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>") + "\"}]");
		result.append("], \"last\":\"" + 0 + "\"}");
		
		String rs = result.toString();
		return rs;
	}
	
	public String getBbsDTOList(Model model, int pageNumber)
	{
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		ArrayList<BbsDTO> list = bbsDAO.getList(pageNumber);
		for(int i = 0; i < list.size(); i++) {
			result.append("[{\"value\": \"" + list.get(i).getBbsID() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getArrangedData() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getUserID() + "\"},");
			result.append("{\"value\": \"" + list.get(i).getBbsTime() + "\"}]");
			if(i != list.size() - 1) result.append(",");
		}
		result.append("], \"last\":\"" + pageNumber + "\"}");
		String rs = result.toString();

		return rs;
	}
}
