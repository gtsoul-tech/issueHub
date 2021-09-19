package web.invitesRequests;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UsersProjectsDao;
import dao.ProjectDao;
import dao.RequestDao;
import dao.AssignedToDao;
import dao.InvitedToDao;
import dao.IssueDao;

import model.User;
import model.UsersProjects;
import model.AssignedToKey;
import model.InvitedTo;
import model.InvitedToKey;
import model.AssignedTo;

import model.Issue;
import model.Project;
import model.ProjectUserId;
import model.Request;

public class AnswerRequest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init() {
        
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
		 	
		 	
        	String applicant = request.getParameter("username");
            String title = request.getParameter("project");
            String check = request.getParameter("check");;
            String admin = request.getParameter("admin");;
            if(admin == null) {
            	admin=(String)request.getSession().getAttribute("UserID");
            }

			ProjectDao registerDao = new ProjectDao();
            RequestDao requestDao = new RequestDao();
	        UserDao userDao = new UserDao();
	        Project temp = (Project) registerDao.getMyProject(title);
	        
	        InvitedToDao invitedToDao = new InvitedToDao();
	        
	        InvitedTo invitedTo=invitedToDao.getInvite(userDao.getUser(admin),userDao.getUser(applicant),temp);
	        if(invitedTo != null) {
	        	int result=invitedToDao.deleteInvite(invitedTo);
	            if (result ==1) {
	            	request.setAttribute("message","Deleting invite went wrong");
	            }else if(result ==0) {
	            	request.setAttribute("message","Deleted invite");
	            }
	        }
	        Request application=requestDao.getRequest(userDao.getUser(admin),userDao.getUser(applicant),temp);
	        		
	        
            if(check.equals("YES")) {
            	int result=requestDao.deleteRequest(application);
                if (result ==1) {
                	request.setAttribute("message","Deleting that application went wrong");
                }else if(result ==0) {
                	request.setAttribute("message","Deleted application");
                }
                request.setAttribute("title",title);
                request.setAttribute("username",applicant);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("joinProject");//issuesController");
                dispatcher.include(request, response);
            }else if(check.equals("NO")) {
            	int result=requestDao.deleteRequest(application);
                if (result ==1) {
                	request.setAttribute("message","Deleting that application went wrong");
                }else if(result ==0) {
                	request.setAttribute("message","Deleted application");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("projectsController");//issuesController");
                dispatcher.include(request, response);
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}