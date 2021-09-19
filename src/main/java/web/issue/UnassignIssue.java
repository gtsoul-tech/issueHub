package web.issue;


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
import dao.AssignedToDao;
import dao.IssueDao;

import model.User;
import model.UsersProjects;
import model.AssignedToKey;
import model.AssignedTo;

import model.Issue;
import model.Project;
import model.ProjectUserId;

public class UnassignIssue extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IssueDao issueDao;

    public void init() {
        issueDao = new IssueDao();
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
		 	
		 	
        	String issueId = request.getParameter("issue_id");
            String title = request.getParameter("project");
            
            
            
            if (issueId == null || title == null) {
            	request.setAttribute("message","All fields are required,Try again to unassign the issue");
    		}else {
    			ProjectDao registerDao = new ProjectDao();
    			IssueDao issueDao = new IssueDao();
    	        UserDao userDao = new UserDao();
	            Project temp = new Project();
	            temp = (Project) registerDao.getMyProject(title);
	            Issue issue = issueDao.getIssue(Integer.parseInt(issueId));
	            if(temp != null) {
	            	if(!(issue.getStatus().equals("Closed") || issue.getStatus().equals("Resolved"))) {
	                    request.setAttribute("message", "Issue is not assigned to anyone");
	            	}else {
	            		if( !issue.getStatus().equals("Resolved")) {
	            			issue.setStatus("Open");
	            		}
		            	if(userDao.amILeader((String)request.getSession().getAttribute("UserID"), temp)) {
		            		int result = issueDao.mergeIssue(issue);
			            	if (result ==1) {
			                    request.setAttribute("message", "Opening issue went wrong.Try again");
			                }else if(result ==0) {
			                    request.setAttribute("message", "Issue got open");
			                }
			            	
			            	AssignedToDao registerAssignedTo = new AssignedToDao();
			            	AssignedTo assignedTo = registerAssignedTo.getAssignmentsByIssue(issue);
			            	if(assignedTo != null) {
			            		result=registerAssignedTo.deleteAssignement(assignedTo);
				                if (result ==1) {
				                	request.setAttribute("message","Unassignment went wrong");
				                }else if(result ==0) {
				                	request.setAttribute("message","Unassigned successfully");
				                }
			            	}
			            	else {
				                	request.setAttribute("message","Assignement does not exist");
			            	}
		            	}else {
		            		request.setAttribute("message", "You have to be the admin to unassign an issue");
		            	}
	            	}
	            	
	            }	else {
	            	request.setAttribute("message", "Project doesnt exists,try another project");
	            }
        	}
            RequestDispatcher dispatcher = request.getRequestDispatcher("projectsController");//issuesController");
            dispatcher.include(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}