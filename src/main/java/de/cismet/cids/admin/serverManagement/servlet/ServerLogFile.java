/*
 * FileEditor.java
 *
 * Created on 2. August 2004, 11:56
 */

package de.cismet.cids.admin.serverManagement.servlet;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import de.cismet.cids.admin.serverManagement.*;
import org.apache.log4j.Logger;
import org.apache.log4j.*;

/**
 *
 * @author  oaltpeter
 * @version
 */
public class ServerLogFile extends HttpServlet
{
    private HeadlessServerConsole serverCon = null;       
    private String httpPort = "";
    

    private String serverIP = "";   
    
    Logger logger = Logger.getLogger(this.getClass());
    
    
      
    
    /** Initializes the servlet.
     */
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);        
    }
    
    
    
    /** Destroys the servlet.
     */
    @Override
    public void destroy()
    {
        
    }
    
    
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {               
        response.setContentType("text/html");        
        PrintWriter out = response.getWriter();
        
        if (serverCon == null)
        {
            serverCon = HeadlessServerConsole.getInstance();
        }                
        
        // variable configFileContent is allocated with an empty string, 
        // otherwise the whole content of the file would be added to the 
        // variable's text at every time the editor window is opened again:               
             
        httpPort = serverCon.getMiniatureServerPort();
        serverIP = ServerManager.getServerIP();
//        logger.fatal(serverCon.getServerLogFile());
        out.println(serverCon.getServerLogFile());       
          
        out.close();
    }
    
    
    
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    
    
    /** Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo()
    {
        return "Server Logfile";
    }
    
}
