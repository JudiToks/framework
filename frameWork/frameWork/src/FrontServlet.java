package etu1820.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;
import java.util.Vector;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import etu1820.framework.Mapping;
import etu1820.framework.ModelView;
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
        PrintWriter out = response.getWriter();
        String urlPath = request.getServletPath();
        String correctPath = urlPath.substring(1);
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            String className = mappingUrls.get(correctPath).getClassName();
            String methodeName = mappingUrls.get(correctPath).getMethod();
            Class<?> classe = Class.forName(className);
            Object myObject = classe.getConstructor().newInstance();
            Method methode = myObject.getClass().getDeclaredMethod(methodeName);
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) 
            {
                String fieldName = parameterNames.nextElement();
                Object value = Code.cast(request.getParameter(fieldName), myObject.getClass().getDeclaredField(fieldName).getType());
                String tempString = String.valueOf(fieldName.charAt(0)).toUpperCase();
                tempString = "set" + tempString + fieldName.substring(1);
                Method methodeSet = myObject.getClass().getDeclaredMethod(tempString, value.getClass());
                methodeSet.invoke(myObject, value);
            }
            // if (mappingUrls.get(correctPath).getMethod().toString().equalsIgnoreCase(correctPath)) 
            // {
            //     Mapping mapping = mappingUrls.get(correctPath);
            //     Class classMap = Class.forName(mapping.getClassName());
            //     Object object = classMap.getConstructor().newInstance();
                // ModelView view = (ModelView)classMap.getDeclaredMethod(mapping.getMethod()).invoke(object);
                // request.getRequestDispatcher(view.getUrl()).forward(request, response);
                Method method = myObject.getClass().getDeclaredMethod(methodeName);
                if (method.invoke(myObject) instanceof ModelView view) 
                {
                    HashMap<String, Object> data = view.getData();
                    for (Map.Entry<String, Object> map : data.entrySet())
                    {
                        request.setAttribute(map.getKey(), map.getValue());
                    }
                    request.getRequestDispatcher(view.getUrl()).forward(request, response);
                }
            // }
        }
        catch(Exception e)
        {
            out.println("<p> Diso </p>");
            e.printStackTrace();
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