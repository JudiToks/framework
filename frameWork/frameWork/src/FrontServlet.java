package etu1820.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Vector;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import etu1820.framework.Mapping;
import etu1820.framework.util.Code;

public class FrontServlet extends HttpServlet
{

    HashMap<String, Mapping> mappingUrls;

    @Override
    public void init()
    {
        try 
        {
            Vector<String> allPath = Code.readPackage(getServletContext().getResource(".").toURI().getPath() + "WEB-INF/classes");
            mappingUrls = new HashMap<>();
            for (String path : allPath)
            {
                Vector<Code> codes = Code.readAnnotation(path);
                for (Code code : codes)
                {
                    mappingUrls.put(code.getValue(), new Mapping(code.getClassName(), code.getMethod()));
                }
            }
        } 
        catch (URISyntaxException | MalformedURLException e) 
        {
            throw new RuntimeException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    { 
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FrontServlet at " + request.getContextPath() + "</h1>");
            // out.println("<p>" + mappingUrls.get("/getEmp").getClassName() + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    { 
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

}