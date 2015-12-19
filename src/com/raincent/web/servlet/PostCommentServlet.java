package com.raincent.web.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.raincent.web.dao.CommentDAO;
import com.raincent.web.util.TextUtility;

/**
 * 发送评论
 * @author Tong
 * http://localhost:8080/web/postComment;
 */
public class PostCommentServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int qid = TextUtility.String2Int(request.getParameter("qid"));
		int uid = TextUtility.String2Int(request.getParameter("uid"));
		String content = TextUtility.toUTF8(request.getParameter("content"));
		String img = TextUtility.toUTF8(request.getParameter("img"));
		
        CommentDAO commentDAO;
		JSONObject jObject = new JSONObject();
		try
		{
			commentDAO = new CommentDAO();
			if(1 == commentDAO.addComment(qid, uid,img,content)){
				jObject.put("msg", "ok");
            } else {
				jObject.put("msg", "error");
            }
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
}
