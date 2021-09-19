<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="main.css">
<meta charset="ISO-8859-1">
<title>Register User</title>
</head>
<body class="gradient-custom">
<jsp:include page="navbar.jsp"/>

<div class="bg">
		<div class="container">
		 <div class="row">
		    <div class="col-sm">
		    </div>
		    <div class="col-sm">
		    	<form method= "POST" action="registerUser">
				<div class="form-group">
				    <label for="username">Username</label>
				    <input type="text" class="form-control" id="username" name="username" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Username" required autofocus>
				</div>
		    	<div class="form-group">
		    		<label for="password">Password</label>
		    		<input type="password" class="form-control" id="password" name="password"  style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Password" required>
		    	</div>
		    	<div class="form-group">
		    		<label for="fname">First Name</label>
		    		<input type="text" class="form-control" id="fname" name="fname" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="First name" required>
		    	</div>
		    	<div class="form-group">
		    		<label for="lname">Last Name</label>
		    		<input type="text" class="form-control" id="lname" name="lname" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Last name" required>
		  		</div>
		  		<div class="form-group">
		    		<label for="email">Email address</label>
		    		<input type="email" class="form-control" id="email" name="email" style="background-color: rgba(0, 0, 0, 0.1)" aria-describedby="emailHelp" placeholder="Enter email" required>
		  		</div>
		  		<div class="form-group col-xs-4">
		    		<textarea id="resume_skills" name="resume_skills" rows="7" cols="100" style="background-color: rgba(0, 0, 0, 0.1)" required>Write about your skills and/or previous jobs in less than 765 characters(or copy paste your CV)</textarea>
		  		
		  		</div>
		  		<button type="submit" class="btn btn-primary btn-dark">Submit</button>
			</form>
		    </div>
		    <div class="col-sm">
		    </div>
		  </div>
			
		</div>	
	</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>