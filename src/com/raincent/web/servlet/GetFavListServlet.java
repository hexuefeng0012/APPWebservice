package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.raincent.web.dao.FavDAO;
import com.raincent.web.dao.NewsDAO;
import com.raincent.web.model.News;
import com.raincent.web.util.TextUtility;

/**
 * 获得用户收藏的新闻信息列表
 * @author DELL
 * http://localhost:8080/web/favlist?uid=1;
 */
public class GetFavListServlet extends HttpServlet{

	private static final long serialVersionUID = -1220953625786723302L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		String uidStr = request.getParameter("uid");
		int uid = 0;
		uid = TextUtility.String2Int(uidStr);
		JSONObject jObject = new JSONObject();
		try
		{
			FavDAO favDAO = new FavDAO();
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			ArrayList<Integer> retList = new ArrayList<Integer>();
			retList = favDAO.getFavlist(uid);
			for (int newsid : retList)
			{
				NewsDAO newsDAO=new NewsDAO();
				News news=newsDAO.getNews(newsid);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("newsId", news.getNewsid());
				map.put("newstype", news.getNewstype());
				map.put("newstitle", news.getNewstitle());			
				map.put("summary", news.getSummary());
				map.put("thumbnail_url", news.getThumbnail_url());
				map.put("newstime", TextUtility.formatDate(news.getNewstime()));
				list.add(map);
			}
			jObject.put("favlist", list);
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				jObject.put("error", e.getMessage());
			} catch (JSONException ex)
			{
				ex.printStackTrace();
			}
		}

		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
}
