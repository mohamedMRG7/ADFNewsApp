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
        String description=request.getParameter("desc");

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
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
       // <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        
        out.println("<div class=\"container\">");
        out.println("<div class=\"jumbotron\">");
        out.println("<img src=" + thumbNail + " alt='image' style=\"width:200px; height: 200px; border-radius: 50%; float: right;\" >");
        out.println("<div class=\"\"> ");
        out.println("<h4>" + title + "</h4>");
        out.println("<p>"+description+"</p>");
        out.println("</div>");        
        out.println("</div>");
        
       
        int fullSize = 0;
        if (imagesList.length >= content.length) {
            fullSize = imagesList.length;
        } else {
            fullSize = content.length;
        }
        out.println("<table style=\"width:100%\">");
        for (int i = 0; i < fullSize; i++) {
            out.println("<tr>");
            
            //out.println("<div class=\"row\" style=\"margin: 25px 50px 75px 100px;  \">");
            if(i%2==0){
           
            if(imagesList[i]!=null && !imagesList[i].equals("NA")){
                out.println("<td valign=\"top\">");
                    out.println("<div >");
                          out.println("<img class=\"cimage\" src=" + imagesList[i] + " alt='image' width=\"300\" height=\"400\" style=\"margin: 25px 50px 75px 100px; border-radius: 50%; \" >");
                    out.println("</div>");
                out.println("</td>");
            }
           
            
            if(content[i]!=null && !content[i].equals("NA")){
                out.println("<td valign=\"middle\" align=\"justify\">");
                     out.println("<div  \">");
                          out.println("<p dir=\"auto\" style=\"font-weight: 400;  font-size: large; \">" + content[i] + "</p>");
                    out.println("</div>");
                out.println("</td>");
            }
            
            //out.println("</div>");
            

            }else {
                
                    if(content[i]!=null && !content[i].equals("NA")){
                        out.println("<td valign=\"middle\" align=\"justify\">");
                             out.println("<div  \">");
                                  out.println("<p dir=\"auto\" style=\"font-weight: 400;  font-size: large; \">" + content[i] + "</p>");
                            out.println("</div>");
                        out.println("</td>");
                    }
                
                    if(imagesList[i]!=null && !imagesList[i].equals("NA")){
                        out.println("<td valign=\"top\">");
                            out.println("<div >");
                                  out.println("<img class=\"cimage\" src=" + imagesList[i] + " alt='image' width=\"300\" height=\"400\" style=\"margin: 25px 50px 75px 100px; border-radius: 50%; \" >");
                            out.println("</div>");
                        out.println("</td>");
                    }
                
                }
            out.println("</tr>");
        }
        
        out.println("</table>");
        /*<table>
    <tr>
        <td valign="top"><img src="myImage.jpg" alt="" /></td>
        <td valign="middle">This is my text!</td>
    </tr>
</table>*/
       
        out.println("</div>");
        out.println("</body></html>");
        out.close();
    }
}
