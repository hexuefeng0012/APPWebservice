package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.raincent.web.dao.BannerDAO;
import com.raincent.web.model.Banner;
import com.raincent.web.util.TextUtility;

/** 
 * 获得单个banner详情
 * @author DELL
 * http://localhost:8080/web/getBanner?bid=1;
 */
public class GetBannerServlet extends HttpServlet{

	private static final long serialVersionUID = 2152477007512231857L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String bidStr=req.getParameter("bid");
		int bid=0;
		bid=TextUtility.String2Int(bidStr);
		JSONObject jObject=new JSONObject();
		try {
				BannerDAO bannerDAO=new BannerDAO();
			    Banner banner=bannerDAO.getBanner(bid);
				HashMap<String, Object> map=new HashMap<String, Object>();
				map.put("bannerId",banner.getBid());
				map.put("pic_url", banner.getPic_url());
				map.put("newstitle",banner.getNewstitle());
				map.put("author", banner.getAuthor());
				map.put("summary",banner.getSummary());
				map.put("content",banner.getContent());
				map.put("news_url",banner.getNews_url());
				map.put("newstime",TextUtility.formatDate(banner.getNewstime()));
			jObject.put("banner", map);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jObject.put("error", e.getMessage());
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
		PrintWriter out=resp.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}

}
