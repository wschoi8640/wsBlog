package com.wschoi.wsblog.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wschoi.wsblog.util.CrawlingUtil;


@Repository
public class CrawlingService
{
	private static final Logger logPrinter = LoggerFactory.getLogger(CrawlingService.class);

	public String getMenu(String cafe, String date) throws ServletException, UnsupportedEncodingException
	{
		
		logPrinter.info("searching requested Menu...");
		StringBuilder menu = new StringBuilder("");
		
		if(cafe != null && date != null)
		{
			String cafe_dat = new String(cafe.getBytes("euc-kr"), "euc-kr");
			
			String cafe_eng = cafe_dat.substring(0, 4);
			String cafe_kor = cafe_dat.substring(4, cafe_dat.length());
			
			//modify to YYYYMMDD
			date = date.replaceAll("/", "");
			
			String url = "https://wis.hufs.ac.kr/jsp/HUFS/cafeteria/viewWeek.jsp?startDt="
					+ CrawlingUtil.getDate(date).substring(0, 8)
					+"&endDt="
					+ CrawlingUtil.getDate(date).substring(8, 16)
					+ "&caf_name="
					+ cafe_kor
					+"&caf_id="
					+ cafe_eng; 
			
			try 
			{
					String url_utf = new String(url.getBytes("euc-kr"), "utf-8");
					Document doc = Jsoup.connect(url_utf).get();
					Elements menu_div = doc.select("table table");
					int date_n = 6;
					int dlen = 1;
			
					LinkedHashMap<String, List<String>> menu_map = new LinkedHashMap<String, List<String>>();
					List<String> temp_menu = new ArrayList<String>();
					List<String> mlist = Arrays.asList(new String[] {"조식", "중식1", "중식2", "중식(면)", "석식"});
					menu_map.put("요일/메뉴", Arrays.asList(new String[] {"월","화","수","목","금","토"}));
			
					for(Element element : menu_div) 
					{
							temp_menu.add(element.text());
							if(dlen % date_n == 0) menu_map.put(mlist.get(dlen/date_n - 1), temp_menu);
							if(temp_menu.size() == 6) 
							{
									temp_menu = new ArrayList<String>();
							}
							dlen++;
					}
			
					Iterator<String> mkeys = menu_map.keySet().iterator();
			
					if(menu_map.size() == 1) 
					{
						menu.append("<h1>" + "표시할 메뉴가 없습니다." + "<h1>");
						menu.append(System.lineSeparator());
					}
					else if(menu_map.size() > 1)
					{		
							int cnt = 0;
							while(mkeys.hasNext()) 
							{
									String mkey = mkeys.next();
									if(!mkeys.hasNext()) cnt = -1;
									if(menu_map.get(mkey).isEmpty()) menu.append("표시할 메뉴가 없습니다.");
									CrawlingUtil.printMenu(mkey, menu, menu_map.get(mkey), cnt);
									cnt++;
							}
					}
			} 
			catch (IOException e) 
			{
					e.printStackTrace();
			}

		}
		return menu.toString();
	}
}
