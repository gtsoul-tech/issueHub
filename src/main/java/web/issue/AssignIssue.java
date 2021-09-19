
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

public class AssignIssue extends HttpServlet {
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
            String assignedToUser = request.getParameter("assigned_to");
            
            
            
            if (issueId == null || title == null || assignedToUser == null) {
            	request.setAttribute("message","All fields are required,Try again to assign the issue");
    		}else {
    			ProjectDao registerDao = new ProjectDao();
    			IssueDao issueDao = new IssueDao();
    	        UserDao userDao = new UserDao();
	            Project temp = new Project();
	            temp = (Project) registerDao.getMyProject(title);
	            Issue issue = issueDao.getIssue(Integer.parseInt(issueId));
	            if(temp != null) {
	            	if(!(issue.getStatus().equals("Open") || issue.getStatus().equals("Resolved"))) {
	                    request.setAttribute("message", "Issue is already assigned or resolved");
	            	}else {
	            		
		            	issue.setStatus("Closed");
		            	if(userDao.amIMember(assignedToUser,temp) || userDao.amILeader(assignedToUser, temp)) {
		            		int result = issueDao.mergeIssue(issue);
			            	if (result ==1) {
			                    request.setAttribute("message", "Assign issue went wrong.Try again");
			                }else if(result ==0) {
			                    request.setAttribute("message", "Issue got assigned");
			                }
			            	
			            	AssignedToDao registerAssignedTo = new AssignedToDao();
			            	AssignedToKey key = new AssignedToKey(userDao.getUser(assignedToUser).getId(),temp.getId());
			            	AssignedTo assignedTo = new AssignedTo(key,userDao.getUser(assignedToUser),issue);
			            	
			            	if(userDao.amIassigned(assignedToUser,issue)) {
			            		request.setAttribute("message","You are already assinged to that issue");
			            	}
			            	else {
			                    result=registerAssignedTo.saveAssignement(assignedTo);
				                if (result ==1) {
				                	request.setAttribute("message","Assignment went wrong");
				                }else if(result ==0) {
				                	request.setAttribute("message","Assigned successfully");
				                }
			            	}
		            	}else {
		            		request.setAttribute("message", "You have to be a admin to assign an issue");
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