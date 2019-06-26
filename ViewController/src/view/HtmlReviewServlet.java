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

        String title = request.getParameter("title");

        String thumbNail = request.getParameter("thumbnail");


        String[] content = request.getParameter("content").split(",");
        String[] imagesList = request.getParameter("image").split(",");

        System.out.println(request.getParameter("content"));
        System.out.println(request.getParameter("image"));

       
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>DownloadImageClass</title></head>");
        out.println("<body>");

        out.println("<div style=\"margin:auto;\">");
        out.println("<h4>" + title + "</h4>");
        // out.println("<img src="+request.getContextPath()+"/images/"+thumbNail+" alt='image' width=\"400\" height=\"500\" >");
        out.println("<img src=" + thumbNail + " alt='image' width=\"400\" height=\"500\" >");

        out.println("</div>");
        int fullSize = 0;
        if (imagesList.length >= content.length) {
            fullSize = imagesList.length;
        } else {
            fullSize = content.length;
        }
        for (int i = 0; i < fullSize; i++) {
            out.println("<div style=\"margin: 25px 50px 75px 100px;\">");
            if(content[i]!=null && !content[i].equals("NA"))
            out.println("<p>" + content[i] + "</p>");
            if(imagesList[i]!=null && !imagesList[i].equals("NA"))
            out.println("<img src=" + imagesList[i] + " alt='image' width=\"300\" height=\"400\" >");
            out.println("</div>");
        }

        out.println("</body></html>");
        out.close();
    }
}
