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
public class PostUpdateCoinServlet extends HttpServlet
{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html;charset=UTF-8");
        int uid = TextUtility.String2Int(request.getParameter("uid"));
        int coin = TextUtility.String2Int(request.getParameter("coin"));
        JSONObject jObject = new JSONObject();

        try
        {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.updateCoin(uid,coin);

            HashMap<String, Object> hashMap = new HashMap<String, Object>();

            hashMap.put("uid", user.getUid());
            hashMap.put("coin", user.getCoin());

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
