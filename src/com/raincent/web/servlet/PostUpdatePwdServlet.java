package com.raincent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class PostUpdatePwdServlet extends HttpServlet
{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html;charset=UTF-8");
        int uid = TextUtility.String2Int(request.getParameter("uid"));
        String old_pwd=request.getParameter("old_pwd"); 
        String new_pwd=request.getParameter("new_pwd"); 

        JSONObject jObject = new JSONObject();

        try
        {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.updatePwd(uid,old_pwd,new_pwd);

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
