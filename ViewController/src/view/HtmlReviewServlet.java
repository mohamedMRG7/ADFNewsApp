package view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;

import javax.servlet.*;
import javax.servlet.http.*;

public class HtmlReviewServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    String splitter="03213216523c";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

        String title = request.getParameter("title");

        String thumbNail = request.getParameter("thumbnail");


        String[] content = request.getParameter("content").split(splitter);
        String[] imagesList = request.getParameter("image").split(",");

        System.out.println(request.getParameter("content"));
        System.out.println(request.getParameter("image"));
        /*
         * <div class="container">
  <img src="nature.jpg" alt="Norway" style="width:100%;">
  <div class="text-block"> 
    <h4>Nature</h4>
    <p>What a beautiful sunrise</p>
  </div>
</div>
         * */
        
        //out.println("<link rel='stylesheet' type='text/css' href='EmpSearchServlet.css' />");
       
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>DownloadImageClass</title>");
        out.println("<link rel='stylesheet' type='text/css' href="+request.getContextPath()+"/css/htmlStyle.css />");
        
        out.println("</head>");
        out.println("<body>");
        //<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        
        out.println("<div class=\"maincont\">");
        out.println("<div class=\"container\">");
        out.println("<img src=" + thumbNail + " alt='image' style=\"width:100%; height: 350px;\" >");
        out.println("<div class=\"text-block\"> ");
        out.println("<h4>" + title + "</h4>");
        out.println("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum</p>");
        out.println("</div>");        
        out.println("</div>");
        
       
        int fullSize = 0;
        if (imagesList.length >= content.length) {
            fullSize = imagesList.length;
        } else {
            fullSize = content.length;
        }
        for (int i = 0; i < fullSize; i++) {
            
            out.println("<div style=\"margin: 25px 50px 75px 100px;  \">");
            if(imagesList[i]!=null && !imagesList[i].equals("NA"))
            out.println("<img class=\"cimage\" src=" + imagesList[i] + " alt='image' width=\"300\" height=\"400\" style=\"margin: 25px 50px 75px 100px;  float: none;\" >");
            
            if(content[i]!=null && !content[i].equals("NA"))
            out.println("<p style=\"font-weight: 400;  font-size: large; \">" + content[i] + "</p>");
            
            out.println("</div>");
            

        }
       
        out.println("</div>");
        out.println("</body></html>");
        out.close();
    }
}
