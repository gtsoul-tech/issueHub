package web.issue;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.ProjectDao;
import dao.IssueDao;


import model.Issue;
import model.Project;

public class ModifyIssue extends HttpServlet {
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
            
            
            String issueSummary = request.getParameter("issue_summary");
            String issueDescription = request.getParameter("issue_description");
            String status = request.getParameter("status");
            String resolution = request.getParameter("resolution");
            String resolutionDate = request.getParameter("resolution_date");
            
            String priority = request.getParameter("priority");
            String targetDate = request.getParameter("target_date");
            Date date;
            
            
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
	            	if(userDao.amILeader((String)request.getSession().getAttribute("UserID"), temp) || userDao.amIassigned((String)request.getSession().getAttribute("UserID"), issue)) {
	            		if((resolution != "" && resolutionDate == "") || (resolution== ""  && resolutionDate != "")) {
	            			request.setAttribute("message", "You should choose both resolution and resolution date");
		                }
		                else {
		                	
		                	 if(resolution!= "" && resolutionDate != ""){
				                	issue.setResolution(resolution);	
				                	date =Date.valueOf(resolutionDate);
				                	issue.setResolutionDate(date);
				                	issue.setStatus("Resolved");
		                	 }
	            		
		            		if(issueSummary!= "") {
			                	issue.setIssueSummary(issueSummary);
			                }
			                if(issueDescription !="") {
			                	issue.setIssueDescription(issueDescription);
			                }
			                
			                if(targetDate!="") {
			                	date =Date.valueOf(targetDate);
			                	issue.setTargetDate(date);
			                }
			                if(priority!="") {
			                	issue.setPriority(priority);
			                }
			                int result = issueDao.mergeIssue(issue);
			            	if (result ==1) {
			                    request.setAttribute("message", "Modifying issue went wrong.Try again");
			                }else if(result ==0) {
			                    request.setAttribute("message", "Issue got modifyed");
			                }
			                
		                }
	            	}else {
	            		request.setAttribute("message", "You must be the admin or assinged to it to modify this issue");
	            	}
	            	
	            }else {
	            	request.setAttribute("message", "Project doesnt exists,try another project");
	            }
        	}
            RequestDispatcher dispatcher = request.getRequestDispatcher("projectsController");
            dispatcher.include(request, response);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}