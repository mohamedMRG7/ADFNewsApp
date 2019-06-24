package view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class DownloadImageClass extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        
      String path = (request.getParameter("path"));
      

      OutputStream os = response.getOutputStream();
     
      InputStream inputStream = null;

      try {
          File outputFile = new File(path);
          inputStream = new FileInputStream(outputFile);
          BufferedInputStream in = new BufferedInputStream(inputStream);
          int b;
          byte[] buffer = new byte[10240];
          while ((b = in.read(buffer, 0, 10240)) != -1) {
              os.write(buffer, 0, b);
              
          }
          
          


      } catch (Exception e) {

          System.out.println(e);
      } finally {
          if (os != null) {
              os.close();
          }
          if (inputStream != null) {
              inputStream.close();
          }

      }
        
    }
}
