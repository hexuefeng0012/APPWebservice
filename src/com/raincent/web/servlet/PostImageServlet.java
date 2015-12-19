package com.raincent.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.json.JSONObject;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PostImageServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        JSONObject jObject=new JSONObject();

        String savePath = this.getServletConfig().getServletContext().getRealPath("/img/");
        File saveDir = new File(savePath);

        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("UTF-8");
        sfu.setFileSizeMax(1024*1024*2);
        sfu.setSizeMax(1024*1024*10);
        try{
            List<FileItem> itemList = sfu.parseRequest(request);
            for (FileItem fileItem : itemList) {
                String fieldName = fileItem.getFieldName();
                if(fileItem.isFormField()){
                    String value = fileItem.getString();
                    value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
                }else{
                    Long size = fileItem.getSize();
                    String fileName = fileItem.getName();

                    File file = new File(savePath,fileName);
                    fileItem.write(file);
                    jObject.put("url",fileName);
                }
            }
        }catch(FileSizeLimitExceededException e){
            request.setAttribute("msg", "Too Big");
        }catch(FileUploadException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        PrintWriter out = response.getWriter();
        out.println(jObject);
        out.flush();
        out.close();
    }
}

