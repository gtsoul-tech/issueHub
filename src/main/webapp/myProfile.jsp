<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="main.css">
<title>Home</title>
<style>

#profile {
	margin-top: 30px;
}

</style>
</head>
<body class="gradient-custom">
<jsp:include page="navbarUser.jsp"/>

<div class="container">
  <div class="row">
    <div class="col" >
      
      <div class="center_form text-center" id="profile">
      		<img src="defaultpic.jpg" alt="Anonymoys Profile" class="rounded-circle border border-dark">
  	 		<p> <b>Username:</b> <c:out value="${user.username}"/> </p>
  	 		<p> <b>Email Address:</b> <c:out value="${user.email}"/> </p>
  	 		<p> <b>First name:</b> <c:out value="${user.firstName}"/> </p>
  	 		<p> <b>Last name:</b> <c:out value="${user.lastName}"/> </p>
  	 		<p> <b>Issues resolved:</b> <c:out value="${myStats}"/> </p>
  	 		
  	 		
		</div>
    </div>
    <div class="col">
    <div class="center_form">
			<form method= "POST" action="deleteUser">
				<div class="form-group">
		    		<label for="password"><b>Password</b></label>
		    		<input type="password" class="form-control" id="password" name="password" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Password to delete the account" required>
		    	</div>
		  		<button type="submit" class="btn btn-primary btn-dark">Delete account</button>
			</form>
		</div>	
		<br><br>
	<div class="bg">
		<div class="center_form">
			<p class="myp1"><b>Change Password</b></p>
			<form method= "POST" action="modifyUser">
				<div class="form-group">
				    <label for="oldpassword">Old Password</label>
				    <input type="password" class="form-control" id="oldpassword" name="oldpassword" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Old Password" required>
				</div>
		    	<div class="form-group">
		    		<label for="password">New Password</label>
		    		<input type="password" class="form-control" id="password" name="password" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="New Password" required>
		    	</div>
		  		<button type="submit" class="btn btn-primary btn-dark">Submit</button>
			</form>
		</div>	
	</div>
    </div>
  </div>
</div>



	<div class="container">
	  <div class="row">
	    <div class="col">
			<c:if test="${not empty otherInvites}">
			  	 			<div>
							<p><b> You got invited to the project titled :</b></p>
							
							<c:forEach items="${otherInvites}" var="item">
							<div class="border border-dark">
							<div class="container">
							  <div class="row">
							    <div class="col">
							    	<b><c:out value="${item.project.title}"/></b>
							    	<button class="btn btn-primary btn-dark button_collapse" data-toggle="collapse" data-target="#<c:out value="${item.user.username}"/>"  class="button_collapse">Project details</button>
									<div class="collapse show" id="<c:out value="${item.project.title}"/>">
										<p>Description:<c:out value="${item.project.description}"/></p>
										
										<form method= "POST" action="answerInvite">
									    	<div class="form-group">
										    	<input type="hidden" class="form-control" id="username" name="username" value="<c:out value="${item.user.username}"/>">
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
							    	<p>by its admin <br> 
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
						
						<c:if test="${not empty myInvites}">
			  	 			<div>
							<p><b> You invited the following users:</b></p>
							
							<c:forEach items="${myInvites}" var="item">
							<div class="border border-dark">
							<div class="container">
							  <div class="row">
							    <div class="col">
							    	<b><c:out value="${item.project.title}"/></b>
							    	<button class="btn btn-primary btn-dark button_collapse" data-toggle="collapse" data-target="#<c:out value="${item.user.username}"/>"  class="button_collapse">User details</button>
									<div class="collapse show" id="<c:out value="${item.user.username}"/>">
										<p>Description:<c:out value="${item.project.description}"/></p>
										
										<form method= "POST" action="answerInvite">
									    	<div class="form-group">
										    	<input type="hidden" class="form-control" id="username" name="username" value="<c:out value="${item.user.username}"/>">
										    	<input type="hidden" class="form-control" id="project" name="project" value="<c:out value="${item.project.title}"/>">
										    	<input type="hidden" class="form-control" id="invited" name="invited" value="<c:out value="${item.invited.username}"/>">
										    	<input type="hidden" class="form-control" id="check" name="check" value="NO">
										    	
										    	<button type="submit" class="btn btn-primary btn-dark">Delete</button>
									    	</div>
										</form>
							    	
									</div>
								</div>
							    <div class="col">
							    	<p> <br>
							    	<b>Username:</b> <c:out value="${item.invited.username}"/> <br> 
							    	<b>Email Address:</b> <c:out value="${item.invited.email}"/> <br> 
							    	<b>First Name:</b> <c:out value="${item.invited.firstName}"/> <br> 
							    	<b>Last Name:</b> <c:out value="${item.invited.lastName}"/> <br> <p>
						    	</div>
							  </div>
						    </div>
								
									
									
							</div>
							</c:forEach>
							</div>
						</c:if>
		</div>
		<div class="col">
    	</div>
	   </div>
  </div>
  
  <script type="text/javascript">
	
	</script>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>