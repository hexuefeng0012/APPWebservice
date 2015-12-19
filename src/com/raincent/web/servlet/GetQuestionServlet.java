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

import com.raincent.web.dao.QuestionDAO;
import com.raincent.web.dao.CommentDAO;
import com.raincent.web.model.Comment;
import com.raincent.web.model.Questions;
import com.raincent.web.util.TextUtility;

/**
 * 获得问题详情
 * @author DELL
 * http://localhost:8080/web/getQuestion?qid=1;
 */

public class GetQuestionServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
        response.setContentType("text/html;charset=UTF-8");
        int qid=TextUtility.String2Int(request.getParameter("qid"));
        JSONObject jObject=new JSONObject();
        
        try {
			QuestionDAO questionDao = new QuestionDAO();
            CommentDAO commentDAO=new CommentDAO();
            ArrayList<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
            rs = commentDAO.getComments(qid,0,100);
            rs.add(0,questionDao.getQuestion(qid));

		    jObject.put("question", rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
	
}
