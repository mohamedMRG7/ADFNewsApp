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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        
       String title=request.getParameter("title");
        
        String thumbNail=request.getParameter("thumbnail");
     
    
        String[] content=request.getParameter("content").split(",");
        String []imagesList=request.getParameter("image").split(",");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>DownloadImageClass</title></head>");
        out.println("<body>");
        
        out.println("<div style=\"margin:auto;\">");
        out.println("<h4>"+title+"</h4>");
        out.println("<img src="+request.getContextPath()+"/images/"+thumbNail+" alt='image' width=\"400\" height=\"500\" >");
        out.println("</div>");
        
        for(int i=0;i<imagesList.length;i++){
        out.println("<div style=\"margin: 25px 50px 75px 100px;\">");
        out.println("<p>"+content[i]+"</p>");
        out.println("<img src="+request.getContextPath()+"/images/"+imagesList[i]+" alt='image' width=\"300\" height=\"400\" >");
        out.println("</div>");
        }
        
        out.println("</body></html>");
        out.close();
    }
}
