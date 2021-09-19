package web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class authentication
 */
public class authentication implements Filter {

    /**
     * Default constructor. 
     */
    public authentication() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
	
        HttpSession session = req.getSession(false);
        if (request instanceof HttpServletRequest) {
	    	 String path = ((HttpServletRequest)request).getRequestURL().toString();
	    	 String url = path.substring(path.lastIndexOf('/') + 1);
	    	 System.out.println(url);
	    	 if (url.equals("index.jsp") || url.equals("registerUser.jsp") || url.equals("login") || url.equals("main.css") || url.equals("about.jsp") || url.equals("background.jpg") || url.equals("registerUser")) {
	             chain.doFilter(req, res);
	    	 }else if (session == null) {   //checking whether the session exists
	             res.sendRedirect("index.jsp");
	         }
		 	 else{
	        	 if(session.getAttribute("UserID")== null) {
	        		 res.sendRedirect("index.jsp");
	        	 }else {
	        		 chain.doFilter(request, response);
	        	 }
		 	}
        }else {
        	if (session == null) {   //checking whether the session exists
                res.sendRedirect("index.jsp");
            } else {
                // pass the request along the filter chain
                chain.doFilter(request, response);
            }
        }
        
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}