<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="main.css">
<title>Search a member</title>

</head>
<body class="gradient-custom">
<jsp:include page="navbarUser.jsp"/>
<div class="container">
  <div class="row justify-content-center align-items-center">
	<h1>Search users to invite them</h1>
	<form method= "POST" action="searchUser" id="search">
					<input type="text" name="query" id="query" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Keywords">
					<input type="hidden" name="page" id="page" value="<c:out value="1"/>">
		  			<button type="submit" class="btn btn-primary btn-dark">Search</button>
	</form>

			<c:if test="${not empty searchedUsers}">
			
				<table id="memBers" class="table table-dark table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Username</th>
						      <th scope="col">First name</th>
						      <th scope="col">Last name</th>
						      <th scope="col">Email address</th>
						      <th scope="col">ResumeSkills</th>
						    </tr>
						  </thead>
						  
						  <tbody>
						  
						  <c:forEach items="${searchedUsers}" var="item" varStatus="i">
						    <tr>
						      <th scope="row">${i.count}
						      
						      <form method= "POST" action="inviteUser" id="invite">
						      <select name="project" id="project" form="invite" >
								<c:forEach items="${ownedProjects}" var="project">
									<option value="${project.title}"><c:out value="${project.title}"/></option>
								</c:forEach>
							  </select>
						      <input type="hidden" name="username" value="<c:out value="${item.username}"/>" form="invite">
            				  <button type="submit" class="btn btn-primary btn-dark" form="invite">Invite</button>
            				  </form>
            				  
            				  </th>
						      <td><c:out value="${item.username}"/></td>
						      <td><c:out value="${item.firstName}"/></td>
						      <td><c:out value="${item.lastName}"/></td>
						      <td><c:out value="${item.email}"/></td>
						      <td><c:out value="${item.resumeSkills}"/></td>
						    </tr>
						  </c:forEach>
						  </tbody>
						</table>
						
			
				
				
				<form method= "POST" action="searchUser" id="search">
						<input type="hidden" name="query" id="query" value="<c:out value="${query}"/>">
						<select name="page" id="page" form="search">
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