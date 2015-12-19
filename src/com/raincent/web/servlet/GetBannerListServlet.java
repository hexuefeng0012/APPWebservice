package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * 获取banner展示列表
 * @author DELL
 * http://localhost:8080/web/getBannerList?startbid=0&count=4;
 */
public class GetBannerListServlet extends HttpServlet{


	private static final long serialVersionUID = 2152477007512231857L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String startbidStr=req.getParameter("startbid");
		String countStr=req.getParameter("count");
		int startbid=0;
		int count=0;
		startbid=TextUtility.String2Int(startbidStr);
		count=TextUtility.String2Int(countStr);
		JSONObject jObject=new JSONObject();
		try {
			BannerDAO bannerDAO=new BannerDAO();
			ArrayList<Banner> blist=bannerDAO.getBanner(startbid,count);
			ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
			
			for (Banner banner:blist) {
				HashMap<String, Object> map=new HashMap<String, Object>();
				map.put("bannerId",banner.getBid());
				map.put("pic_name",banner.getPic_name());
				map.put("pic_url", banner.getPic_url());
				list.add(map);
			}			
			jObject.put("bannerlist", list);
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
