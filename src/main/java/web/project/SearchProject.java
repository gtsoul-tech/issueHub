package web.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjectDao;
import dao.UserDao;

import model.User;

public class SearchProject extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectDao registerDao;

    public void init() {
        registerDao = new ProjectDao();
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
            ProjectDao searchDao = new ProjectDao();
            request.setAttribute("searchedProjects", searchDao.getProjectsByQuery(query, page));
            request.setAttribute("pages", searchDao.getSearchSize(query));
            request.setAttribute("query", query);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("searchProject.jsp");
			dispatcher.forward(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}