package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.connector.Request;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.EscapeTokenizer;
import com.raincent.web.dao.NewsDAO;
import com.raincent.web.model.News;
import com.raincent.web.util.TextUtility;
//import com.sun.security.ntlm.Server;

/**
 * 搜索新闻
 * @author DELL
 * http://localhost:8080/web/postSearch;
 */
public class PostSearchServlet extends HttpServlet{
	
	private static final long serialVersionUID = 3818559006469376363L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String key=URLDecoder.decode(req.getParameter("key"),"utf-8");
		//String mm = Server.URLDecode(.querystring(mm)); 
		
		//String keysStr = req.getParameter("key");
		//String key=" ";
		//String key= TextUtility.toUTF8(req.getParameter("key")); 
	
		JSONObject jObject = new JSONObject();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			NewsDAO newsDAO = new NewsDAO();
			ArrayList<News> rList=newsDAO.getSearchNews(key);
			for (News news:rList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("newsId", news.getNewsid());
				map.put("newstype", news.getNewstype());
				map.put("newstitle", news.getNewstitle());			
				map.put("summary", news.getSummary());
				map.put("thumbnail_url", news.getThumbnail_url());
				map.put("newstime", TextUtility.formatDate(news.getNewstime()));
				list.add(map);
			}
			jObject.put("searchresult", list);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		PrintWriter out = resp.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}	
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
		
	}
}

	
