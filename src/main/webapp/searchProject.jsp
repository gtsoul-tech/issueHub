<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="main.css">
<title>Search a project</title>
</head>
<body class="gradient-custom">
<jsp:include page="navbarUser.jsp"/>
<div class="container">
  <div class="row justify-content-center align-items-center">
	<h1>Search a project to join</h1> <br>
	<form method= "POST" action="searchProject">
					<input type="text" name="query" id="query" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Keywords">
					<input type="hidden" name="query" id="query" value=<c:out value="1"/> >
		  			<button type="submit" class="btn btn-primary btn-dark">Search</button>
	</form>
	<br>
			<c:if test="${not empty searchedProjects}">
    		
			<table id="memBers" class="table table-dark table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Admin</th>
						      <th scope="col">Title</th>
						      <th scope="col">Description</th>
						      
						    </tr>
						  </thead>
						  
						  <tbody>
						  
						  <c:forEach items="${searchedProjects}" var="item" varStatus="i">
						    <tr>
						      <th scope="row">${i.count}
						      <form method= "POST" action="requestInvite" id="invite">
								<input type="hidden" name="username" value="<c:out value="${item.leader.username}"/>">
								<input type="hidden" name="project" value="<c:out value="${item.title}"/>">
								<button type="submit" class="btn btn-primary btn-dark">Request to join</button>
							  </form>
            				  </th>
            				  <td><c:out value="${item.leader.username}"/></td>
						      <td><c:out value="${item.title}"/></td>
						      <td><c:out value="${item.description}"/></td>
						    </tr>
						  </c:forEach>
						  </tbody>
						</table>
			
			<form method= "POST" action="searchProject" id="search">
					<input type="hidden" name="query" id="query" value="<c:out value="${query}"/>">
					<select name="page" id="page" form="search" style="background-color: rgba(0, 0, 0, 0.1)">
						<c:forEach var = "i" begin = "1" end = "${pages}">
						<option value="${i}"> <c:out value = "${i}"/></option>
					    </c:forEach>
					</select>
		  			<button type="submit" class="btn btn-primary btn-dark">Search</button>
			</form>
			
			
			
			</c:if>

</div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function() {
    $('#memBers').DataTable( {
    	"order": [[ 2, "desc" ]]
	} );
    
} );
</script>
</body>
</html>