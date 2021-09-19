package web.user;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

import model.User;

public class SearchUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao registerDao;

    public void init() {
        registerDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
        	PrintWriter out= response.getWriter();
		 	response.setContentType("text/html");
        	String query = request.getParameter("query");
        	String slice = request.getParameter("page");
        	
        	int page;
        	if(slice == "" || slice == null) {
        		page =1;
        	}else {
        		page = Integer.parseInt(slice);
        	}
        	if(query == "") {
        		request.setAttribute("message","You must put some keywords to search");
        	}
            UserDao searchDao = new UserDao();
            request.setAttribute("ownedProjects", searchDao.getOwnedProjects((String)request.getSession().getAttribute("UserID")));
            request.setAttribute("pages", searchDao.getSearchSize(query));
            request.setAttribute("query", query);
            request.setAttribute("searchedUsers", searchDao.getUsersByQuery(query, page));
            //System.out.println(searchDao.getUsersByQuery(query, page));
        	RequestDispatcher dispatcher = request.getRequestDispatcher("searchMembers.jsp");
			dispatcher.include(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}