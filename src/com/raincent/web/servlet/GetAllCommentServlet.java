package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raincent.web.dao.CommentDAO;
import com.raincent.web.model.Comment;
import com.raincent.web.util.TextUtility;

/**
 * Get All The Commets with the user
 * @author Tong
 * http://ip:port/webroot/getallcomment?uid=0&count=4;
 */
public class GetAllCommentServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		int uid = TextUtility.String2Int(req.getParameter("uid"));
		int count = TextUtility.String2Int(req.getParameter("count"));
		int start = TextUtility.String2Int(req.getParameter("start"));
		JSONObject jObject=new JSONObject();
		
        try {
			CommentDAO commentDAO=new CommentDAO();
			jObject.put("commentlist", commentDAO.getAllComment(uid,start,count));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out=resp.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
	
}
