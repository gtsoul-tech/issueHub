<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="main.css">



<title>MyProjects</title>
</head>
<body class="gradient-custom">
<jsp:include page="navbarUser.jsp"/>

<div class="row">
  <div class="col-3 bg-dark ">
	  <div class="nav flex-sm-column nav-pills nav-tabs" id="v-pills-tab" role="tablist" aria-orientation="vertical">
	  	<a class="nav-link active bg-dark btn-dark" id="v-pills-owned-tab" data-toggle="pill" href="#v-pills-owned" role="tab" aria-controls="v-pills-owned" aria-selected="true">Leading Projects</a>
	  	<a class="nav-link bg-dark btn-dark" id="v-pills-joined-tab" data-toggle="pill" href="#v-pills-joined" role="tab" aria-controls="v-pills-joined" aria-selected="false">Membership Projects</a>
	  	<a class="nav-link bg-dark btn-dark" id="v-pills-requests-tab" data-toggle="pill" href="#v-pills-requests" role="tab" aria-controls="v-pills-requests" aria-selected="false">Requests</a>
	  	<a class="nav-link bg-dark btn-dark" id="v-pills-action-tab" data-toggle="pill" href="#v-pills-action" role="tab" aria-controls="v-pills-action" aria-selected="false">Create Project</a>
	  </div>
  </div>
  <div class="col-9">
  
<div class="tab-content" id="v-pills-tabContent">
  <div class="tab-pane fade show active" id="v-pills-owned" role="tabpanel" aria-labelledby="v-pills-owned-tab">
	  	<div id="Owned_projects" class="collapse show">
					<p ><b>Leading Projects</b></p>
					<c:if test="${empty ownedProjects}">
						<p><b>You do not own any projects, create one </b> </p>
					</c:if>
					<c:if test="${not empty ownedProjects}">
						<form method="POST" action="getMemberIssueDetails" id="Owned_Project">
							<label for="Owned_Projects">Choose a Project </label>
							<select name="Owned_Projects" id="Owned_Projects" form="Owned_Project" style="background-color: rgba(0, 0, 0, 0.1)" required>
								<c:forEach items="${ownedProjects}" var="item">
									<option value="${item.title}"><c:out value="${item.title}"/></option>
								</c:forEach>
							</select>
						  <button type="submit" class="btn btn-primary btn-dark">Details</button>
						</form>
					</c:if>
		 </div>
	  	<c:if test="${not empty project}">
	  			<div class="row">
			      <div class="col-8 col-sm-6">
				      <p>
						<b>Title:</b>	<c:out value="${project.title}"/> <br>
						<b>Description:</b> <c:out value="${project.description}"/><br>
						<b>Admin:  </b>	<c:out value="${project.leader.username}"/><br>
						<b>Members:</b>
						
						<table id="memBers" class="table table-dark table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Username</th>
						      <th scope="col">First name</th>
						      <th scope="col">Last name</th>
						      <th scope="col">Email address</th>
						    </tr>
						  </thead>
						  
						  <tbody>
						  
						  <c:forEach items="${members}" var="item" varStatus="i">
						    <tr>
						      <th scope="row">${i.count}</th>
						      <td><c:out value="${item.username}"/></td>
						      <td><c:out value="${item.firstName}"/></td>
						      <td><c:out value="${item.lastName}"/></td>
						      <td><c:out value="${item.email}"/> 
						      <form method= "POST" action="quitProject" id="kick">
								 <input type="hidden" name="title" value="<c:out value="${project.title}"/>">
								 <input type="hidden" name="username" value="<c:out value="${item.username}"/>">
            				  	 <button type="submit" class="btn btn-primary btn-dark" >X</button> 
							  </form> </td>
						    </tr>
						  </c:forEach>
						  </tbody>
						</table>
						
						
						
			      </div>
			      <div class="col-4 col-sm-6">
			      	
						<form method= "POST" action="deleteProject">
							<input type="hidden" name="title" value="<c:out value="${project.title}"/>">
					  		<button type="submit" class="btn btn-primary btn-dark">Delete this project</button>
						</form>
						<form method= "POST" action="modifyProject">
							<input type="hidden" name="title" value="<c:out value="${project.title}"/>">
							<label for="description">Description:</label>
					    	<input type="text" class="form-control" id="description" name="description" placeholder="Description" style="background-color: rgba(0, 0, 0, 0.1)" required>
					  		<button type="submit" class="btn btn-primary btn-dark">Modify this project's description</button>
						</form>
			      </div>
			    </div>
			    
			    <div class="border border-dark">
	  			<b>Issues:	</b>	<br>
					<c:if test="${not empty projectIssues}">
						<table id="isSues" class="table table-dark table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#id</th>
						      <th scope="col">Issue Summary</th>
						      <th scope="col">Issue description</th>
						      <th scope="col">Created Date</th>
						      <th scope="col">Created By</th>
						      <th scope="col">Status</th>
						      <th scope="col">Priority</th>
						      <th scope="col">Target Date</th>
						      <th scope="col">Resolution</th>
						      <th scope="col">Resolution Date</th>
						      <th scope="col">Assigned To</th>
						      
						    </tr>
						  </thead>
						  
						  <tbody>
						  
						  <c:forEach items="${projectIssues}" var="item" >
						    <tr>
						      <th scope="row">${item.issueId} 
						      <input type="hidden" name="issue_id" form="delete_issue" value="<c:out value="${item.issueId}"/>">
            				  <button type="submit" class="btn btn-primary btn-dark" form="delete_issue">Delete</button> 
            				  </th>
						      <td><c:out value="${item.issueSummary}"/></td>
						      <td><c:out value="${item.issueDescription}"/></td>
						      <td><c:out value="${item.createdDate}"/></td>
						      
						      <td><c:out value="${item.createdBy}"/></td>
						      
						      <td><c:out value="${item.status}"/></td>
						      <td><c:out value="${item.priority}" default="Not set"/></td>
						      <td><c:out value="${item.targetDate}" default="Not set"/></td>
						      <td><c:out value="${item.resolution}" default="Not set"/> </td>
						      
						      <td><c:out value="${item.resolutionDate}" default="Not set"/></td>
						      <td><c:out value="${map[item.issueId].username}" default="Not set"/> 
						      <c:if test="${not empty map[item.issueId].username}">
							  	<input type="hidden" name="issue_id" form="unassigned_to" value="<c:out value="${item.issueId}"/>">
							  	<button type="submit" class="btn btn-primary btn-dark" form="unassigned_to">Unassign</button> 
            				  </c:if>
            				  </td>
						    </tr>
						  </c:forEach>
						  </tbody>
						</table>
						
						
						<form method="POST" action="unassignIssue" id="unassigned_to">
						<input type="hidden" name="project" value="<c:out value="${project.title}"/>">
						</form>
						<form method="POST" action="deleteIssue" id="delete_issue">
						<input type="hidden" name="project" value="<c:out value="${project.title}"/>">
						</form>
						
						<form method="POST" action="assignIssue" id="assigned_to">
							<label for="assigned_to">Choose a Member to assign an issue </label>
							<input type="hidden" name="project" value="<c:out value="${project.title}"/>">
							<select name="issue_id" id="issue_id" form="assigned_to" style="background-color: rgba(0, 0, 0, 0.1)" required>
								<c:forEach items="${projectIssues}" var="item">
									<option value="${item.issueId}"><c:out value="${item.issueId}"/></option>
								</c:forEach>
							</select>
							<select name="assigned_to" id="assigned_to" form="assigned_to" style="background-color: rgba(0, 0, 0, 0.1)" required>
								<c:forEach items="${members}" var="item">
									<option value="${item.username}"><c:out value="${item.username}"/></option>
								</c:forEach>
								<option value="${project.leader.username}"><c:out value="${project.leader.username}"/></option>
							</select>
							<button type="submit" class="btn btn-primary btn-dark">Assign</button>
						</form>
					</c:if>
					
					<form method="POST" action="modifyIssue" id="modify_issue" class="border border-dark">
						<label for="issue_id">IssueId:</label>
						<select name="issue_id" id="issue_id" form="modify_issue" style="background-color: rgba(0, 0, 0, 0.1)" required>
							<c:forEach items="${projectIssues}" var="item">
								<option value="${item.issueId}"><c:out value="${item.issueId}"/></option>
							</c:forEach>
						</select>
						<br>
						<input type="hidden" name="project" value="<c:out value="${project.title}"/>">
						
						<label for="issue_summary">Issue Summary</label>
						<input type="text" name="issue_summary" id="issue_summary" placeholder="Issue Summary" style="background-color: rgba(0, 0, 0, 0.1)">
						<br>
						<label for="issue_description">Issue Description</label>
						<input type="text" name="issue_description" id="issue_description" placeholder="Issue Description" style="background-color: rgba(0, 0, 0, 0.1)">
						<br>
						<label for="priorityn">Priority:</label>
						<select name="priority" id="priority" form="modify_issue" style="background-color: rgba(0, 0, 0, 0.1)" required>
								<option value="High">High</option>
								<option value="Medium">Medium</option>
								<option value="Low">Low</option>
						</select>
						<br>
						<label for="resolution">Resolution</label>
						<input type="text" name="resolution" id="resolution" placeholder="Resolution" style="background-color: rgba(0, 0, 0, 0.1)">
								<br>
						<label for="resolution_date">Resolution date:</label>
						<input type="date" id="resolution_date" name="resolution_date" style="background-color: rgba(0, 0, 0, 0.1)"
						       
						       min="2021-06-02" max="2022-12-31">
						       <br>
						<label for="target_date">Target date:</label>
						<input type="date" id="target_date" name="target_date" style="background-color: rgba(0, 0, 0, 0.1)"
						       
						       min="2021-06-02" max="2022-12-31">
						<button type="submit" class="btn btn-primary btn-dark">Modify it</button>
				</form>
					
				</div>
					
				<br>
				<br>
				
			<p><b>Register an issue you found</b></p>
			<form method= "POST" action="registerIssue">
				<div class="form-group">
				    <label for="issue_summary">Issue Summary</label>
				    <input type="text" class="form-control" id="issue_summary" name="issue_summary" placeholder="Issue summary" style="background-color: rgba(0, 0, 0, 0.1)" required>
				</div>
		    	<div class="form-group">
		    		<label for="issue_description">Issue Description</label>
		    		<input type="text" class="form-control" id="issue_description" name="issue_description" placeholder="Issue description" style="background-color: rgba(0, 0, 0, 0.1)" required>
		    	</div>
		    	<input type="hidden" class="form-control" id="project" name="project" value="<c:out value="${project.title}"/>">
		  		<button type="submit" class="btn btn-primary btn-dark">Submit</button>
			</form>
			</c:if>
	  </div>
  <div class="tab-pane fade" id="v-pills-joined" role="tabpanel" aria-labelledby="v-pills-joined-tab">
	<div id="joined_projects" class="collapse show">
				<p><b>Membership Projects</b></p>
				
				<c:if test="${empty joinedProjects}">
						<p><b>You have not joined a project, join one through <a class="nav-item nav-link" href="<c:url value = "searchProject.jsp"/>"> Search Project</a></b> </p>
				</c:if>
				<c:if test="${not empty joinedProjects}">
				<form method="POST" action="getMemberIssueDetails" id="joined_project">
					<label for="joined_projects">Choose a Project </label>
					<select name="joined_projects" id="joined_projects" form="joined_project" style="background-color: rgba(0, 0, 0, 0.1)">
						<c:forEach items="${joinedProjects}" var="item">
							<option value="${item.title}"><c:out value="${item.title}"/></option>
						</c:forEach>
					</select>
				  <button type="submit" class="btn btn-primary btn-dark">Details</button>
				</form>
				</c:if>
	</div>
	
	<c:if test="${not empty joinedProject}">
			<form method= "POST" action="quitProject">
				<input type="hidden" name="title" value="<c:out value="${joinedProject.title}"/>">
		  		<button type="submit" class="btn btn-primary btn-dark">Quit this Project</button>
			</form>
    		<p>
    		<b>Title:</b>	<c:out value="${joinedProject.title}"/> <br>
			<b>Description:</b> <c:out value="${joinedProject.description}"/><br>
			<b>Admin:  </b>	<c:out value="${joinedProject.leader.username}"/><br>
			<b>Members:</b>
			</p>
			
			
			<table id="joinedMemBers" class="table table-dark table-striped">
			  <thead>
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">Username</th>
			      <th scope="col">First name</th>
			      <th scope="col">Last name</th>
			      <th scope="col">Email address</th>
			    </tr>
			  </thead>
			  
			  <tbody>
			  
			  <c:forEach items="${joinedProjectMembers}" var="item" varStatus="i">
			    <tr>
			      <th scope="row">${i.count}</th>
			      <td><c:out value="${item.username}"/></td>
			      <td><c:out value="${item.firstName}"/></td>
			      <td><c:out value="${item.lastName}"/></td>
			      <td><c:out value="${item.email}"/> 
			      </td>
			    </tr>
			  </c:forEach>
			  </tbody>
			</table>
			
			
			<div class="border border-dark">
	  			<b>Issues:	</b>	<br>
					<c:if test="${not empty joinedProjectIssues}">
						<table id="isSues_joined" class="table table-dark table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#id</th>
						      <th scope="col">Issue Summary</th>
						      <th scope="col">Issue description</th>
						      <th scope="col">Created Date</th>
						      <th scope="col">Created By</th>
						      <th scope="col">Status</th>
						      <th scope="col">Priority</th>
						      <th scope="col">Target Date</th>
						      <th scope="col">Resolution</th>
						      <th scope="col">Resolution Date</th>
						      <th scope="col">Assigned To</th>
						      
						    </tr>
						  </thead>
						  
						  <tbody>
						  
						  <c:forEach items="${joinedProjectIssues}" var="item" >
						    <tr>
						      <th scope="row">${item.issueId}</th>
						      <td><c:out value="${item.issueSummary}"/></td>
						      <td><c:out value="${item.issueDescription}"/></td>
						      <td><c:out value="${item.createdDate}"/></td>
						      
						      <td><c:out value="${item.createdBy}"/></td>
						      
						      <td><c:out value="${item.status}"/></td>
						      <td><c:out value="${item.priority}" default="Not set"/></td>
						      <td><c:out value="${item.targetDate}" default="Not set"/></td>
						      <td><c:out value="${item.resolution}" default="Not set"/> </td>
						      
						      <td><c:out value="${item.resolutionDate}" default="Not set"/></td>
						      <td><c:out value="${joinedMap[item.issueId].username}" default="Not set"/></td>
						    </tr>
						  </c:forEach>
						  </tbody>
						</table>
						
						
					</c:if>
						
						
					<c:if test="${empty myIssues}">
						<b>You have not been assigned any issues in this project</b>
					</c:if>
					<c:if test="${not empty myIssues}">
					<b>My Issues:</b>
						<table id="my_isSues" class="table table-dark table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#id</th>
						      <th scope="col">Issue Summary</th>
						      <th scope="col">Issue description</th>
						      <th scope="col">Created Date</th>
						      <th scope="col">Created By</th>
						      <th scope="col">Status</th>
						      <th scope="col">Priority</th>
						      <th scope="col">Target Date</th>
						      <th scope="col">Resolution</th>
						      <th scope="col">Resolution Date</th>
						      <th scope="col">Assigned To</th>
						    </tr>
						  </thead>
						  
						  <tbody>
						  
						  <c:forEach items="${myIssues}" var="item" >
						    <tr>
						      <th scope="row">${item.key.issueId}</th>
						      <td><c:out value="${item.key.issueSummary}"/></td>
						      <td><c:out value="${item.key.issueDescription}"/></td>
						      <td><c:out value="${item.key.createdDate}"/></td>
						      
						      <td><c:out value="${item.key.createdBy}"/></td>
						      
						      <td><c:out value="${item.key.status}"/></td>
						      <td><c:out value="${item.key.priority}" default="Not set"/></td>
						      <td><c:out value="${item.key.targetDate}" default="Not set"/></td>
						      <td><c:out value="${item.key.resolution}" default="Not set"/> </td>
						      
						      <td><c:out value="${item.key.resolutionDate}" default="Not set"/></td>
						      <td><c:out value="${item.value.username}" default="Not set"/></td>
						    </tr>
						  </c:forEach>
						  </tbody>
						</table>
					
					</c:if>
					
					<form method="POST" action="modifyIssue" id="modify_issue" class="border border-dark">
						<label for="issue_id">IssueId:</label>
						<select name="issue_id" id="issue_id" form="modify_issue" style="background-color: rgba(0, 0, 0, 0.1)" required>
							<c:forEach items="${myIssues}" var="item">
								<option value="${item.key.issueId}"><c:out value="${item.key.issueId}"/></option>
							</c:forEach>
						</select>
						<input type="hidden" name="project" value="<c:out value="${joinedProject.title}"/>">
						<input type="hidden" name="issue_summary" id="issue_summary" value=<c:out value=""/>>
						
						<input type="hidden" name="issue_description" id="issue_description" value=<c:out value=""/>>
						<br>
						<input type="hidden" name="priority" id="priority" value=<c:out value=""/>>
						
						<label for="resolution">Resolution</label>
						<input type="text" name="resolution" id="resolution" placeholder="Resolution" style="background-color: rgba(0, 0, 0, 0.1)" required>
								<br>
						<label for="resolution_date">Resolution date:</label>
						<input type="date" id="resolution_date" name="resolution_date" style="background-color: rgba(0, 0, 0, 0.1)" required
						       
						       min="2021-06-02" max="2022-12-31">
						       <br>
						
						<input type="hidden" id="target_date" name="target_date" value=<c:out value=""/>>
						<button type="submit" class="btn btn-primary btn-dark">Modify it</button>
				</form>
				
				
				<p><b>Register an issue you found</b></p>
			<form method= "POST" action="registerIssue">
				<div class="form-group">
				    <label for="issue_summary">Issue Summary</label>
				    <input type="text" class="form-control" id="issue_summary" name="issue_summary" placeholder="Issue summary" style="background-color: rgba(0, 0, 0, 0.1)" required>
				</div>
		    	<div class="form-group">
		    		<label for="issue_description">Issue Description</label>
		    		<input type="text" class="form-control" id="issue_description" name="issue_description" placeholder="Issue description" style="background-color: rgba(0, 0, 0, 0.1)" required>
		    	</div>
		    	<input type="hidden" class="form-control" id="project" name="project" value="<c:out value="${joinedProject.title}"/>">
		  		<button type="submit" class="btn btn-primary btn-dark">Submit</button>
			</form>
				</div>
	</c:if>
  </div>
  <div class="tab-pane fade" id="v-pills-requests" role="tabpanel" aria-labelledby="v-pills-requests-tab">
		<c:if test="${empty myRequests}">
			<p><b>You got no requests</b></p>
		</c:if>
		<c:if test="${not empty myRequests}">
			<div>
				<p><b> People who requested to join in one of your projects</b></p>
				
				<c:forEach items="${myRequests}" var="item">
				<div class="border border-dark">
				<div class="container">
				  <div class="row">
				    <div class="col">
				    	<b><c:out value="${item.project.title}"/></b>
				    	<button class="btn btn-primary btn-dark button_collapse" data-toggle="collapse" data-target="#<c:out value="${item.applicant.username}"/>"  class="button_collapse">Project details</button>
						<div class="collapse show" id="<c:out value="${item.applicant.username}"/>">
							<p>Description:<c:out value="${item.project.description}"/></p>
							
							<form method= "POST" action="answerRequest">
						    	<div class="form-group">
							    	<input type="hidden" class="form-control" id="username" name="username" value="<c:out value="${item.applicant.username}"/>">
							    	<input type="hidden" class="form-control" id="project" name="project" value="<c:out value="${item.project.title}"/>">
							    	
							    	<input type="radio" id="check" name="check" value="YES" style="background-color: rgba(0, 0, 0, 0.1)" required>
				 					<label for="check">Accept</label><br>
							    	<input type="radio" id="check" name="check" value="NO" style="background-color: rgba(0, 0, 0, 0.1)" required>
				  					<label for="check">Decline</label><br>
							    	
							    	<button type="submit" class="btn btn-primary btn-dark">Submit</button>
						    	</div>
							</form>
				    	
						</div>
					</div>
				    <div class="col">
				    	<p>by User <br> 
				    	<b>Username:</b> <c:out value="${item.applicant.username}"/> <br> 
				    	<b>Email Address:</b> <c:out value="${item.applicant.email}"/> <br> 
				    	<b>First Name:</b> <c:out value="${item.applicant.firstName}"/> <br> 
				    	<b>Last Name:</b> <c:out value="${item.applicant.lastName}"/> <br> <p>
			    	</div>
				  </div>
			    </div>
					
						
						
				</div>
				</c:forEach>
			</div>
		</c:if>
		  <c:if test="${empty otherRequests}">
			<p><b>You haven't requested to join in any project</b></p>
		  </c:if>
		<c:if test="${not empty otherRequests}">
			<div>
				<p><b>Projects you requested to join</b></p>
				
				<c:forEach items="${otherRequests}" var="item">
				<div class="border border-dark">
				<div class="container">
				  <div class="row">
				    <div class="col">
				    	<b><c:out value="${item.project.title}"/></b>
				    	<button class="btn btn-primary btn-dark button_collapse" data-toggle="collapse" data-target="#<c:out value="${item.applicant.username}"/>"  class="button_collapse">Project details</button>
						<div class="collapse show" id="<c:out value="${item.applicant.username}"/>">
							<p>Description:<c:out value="${item.project.description}"/></p>
							
							<form method= "POST" action="answerRequest">
						    	<div class="form-group">
							    	<input type="hidden" class="form-control" id="username" name="username" value="<c:out value="${sessionScope.UserID}"/>">
							    	<input type="hidden" class="form-control" id="admin" name="admin" value="<c:out value="${item.user.username}"/>">
							    	<input type="hidden" class="form-control" id="project" name="project" value="<c:out value="${item.project.title}"/>">
							    	<input type="hidden" class="form-control" id="check" name="check" value="NO">
							    	<button type="submit" class="btn btn-primary btn-dark">Delete</button>
						    	</div>
							</form>
				    	
						</div>
					</div>
				    <div class="col">
				    	<p>to User <br> 
				    	<b>Username:</b> <c:out value="${item.user.username}"/> <br> 
				    	<b>Email Address:</b> <c:out value="${item.user.email}"/> <br> 
				    	<b>First Name:</b> <c:out value="${item.user.firstName}"/> <br> 
				    	<b>Last Name:</b> <c:out value="${item.user.lastName}"/> <br> <p>
			    	</div>
				  </div>
			    </div>
					
						
						
				</div>
				</c:forEach>
			</div>
		</c:if>
  </div>
  <div class="tab-pane fade" id="v-pills-action" role="tabpanel" aria-labelledby="v-pills-action-tab">
    <div id="create">
			<p><b>Create Your Project</b></p>
			<form method= "POST" action="registerProject">
				<div class="form-group">
				    <label for="title">Title</label>
				    <input type="text" class="form-control" id="title"  style="background-color: rgba(0, 0, 0, 0.1)" name="title" placeholder="Title"required>
				</div>
		    	<div class="form-group">
		    		<label for="description">Description</label>
		    		<input type="text" class="form-control" id="description"  style="background-color: rgba(0, 0, 0, 0.1)" name="description" placeholder="Description" required>
		    	</div>
		  		<button type="submit" class="btn btn-primary btn-dark">Submit</button>
			</form>
	</div>
  </div>
 </div>
  
  </div>
</div>

<script>
$(document).ready(function() {
	$('#memBers').DataTable( {
    	"order": [[ 2, "desc" ]]
    	} );
	$('#isSues').DataTable( {
    	"order": [[ 7, "desc" ]]
    	} );
    $('#v-pills-tab a[href="#' + '${tab}' + '"]').tab('show');
    
} );
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>


</body>
</html>
