package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.raincent.web.dao.UserDAO;
import com.raincent.web.model.User;
import com.raincent.web.util.TextUtility;

/**
 * Update the usename and password for the user
 * @author tong
 * http://localhost:8080/web/update;
 */
public class PostUpdateInfoServlet extends HttpServlet
{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		  String nick = URLDecoder.decode(request.getParameter("nick"),"utf-8");
	        String avator = URLDecoder.decode(request.getParameter("avator"),"utf-8");
	        String email = URLDecoder.decode(request.getParameter("email"),"utf-8");
        int uid = TextUtility.String2Int(request.getParameter("uid"));
        JSONObject jObject = new JSONObject();

        try
        {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.updateInfo(uid,avator,nick,email);

            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("uid", user.getUid());
            hashMap.put("usename", user.getUsename());
            hashMap.put("pwd", user.getPwd());
            hashMap.put("avator", user.getAvator());
            hashMap.put("nick", user.getNick());
            hashMap.put("coin", user.getCoin());
            hashMap.put("email", user.getEmail());
			hashMap.put("login_date", user.getLogin_date());
            

            jObject.put("user", hashMap);
        } catch (Exception e){
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.println(jObject);
        out.flush();
        out.close();
    }

}
