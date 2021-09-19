<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<meta charset="ISO-8859-1">
	<title>${param.pageTitle}</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  		<a class="navbar-brand" href="#">IssueHub</a>
  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
   	 <span class="navbar-toggler-icon"></span>
  	</button>
	  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	    <div class="navbar-nav">
	      <ul class="navbar-nav bg-dark">
		      <li class="nav-item">
		      	<a class="nav-item nav-link" href="<c:url value = "profileController"/>">Home <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		      	<a class="nav-item nav-link" href="<c:url value = "projectsController"/>"> My Projects</a>
		      </li>
		      <li class="nav-item">
			  	<a class="nav-item nav-link" href="<c:url value = "searchMembers.jsp"/>"> Search Member</a>
			  </li>
			  <li class="nav-item">
			  	<a class="nav-item nav-link" href="<c:url value = "searchProject.jsp"/>"> Search Project</a>
			  </li>
	      </ul>
	    </div>
	    
	    <div class="navbar-nav ml-auto">
	    <a class="nav-item nav-link" href="<c:url value = "/logOut"/>"> <span class="glyphicon glyphicon-log-in"></span> Login/out</a>
	    </div>
	  </div>
	</nav>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>