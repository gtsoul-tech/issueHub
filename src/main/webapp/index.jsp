<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Home</title>
  <!--
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <meta charset="ISO-8859-1">
   <link rel="stylesheet" href="main.css" />  
   
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"-->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="main.css">
	 <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
</head>
<body class="gradient-custom">
<jsp:include page="navbar.jsp"/>

<div class="row">
	<div class="col-sm">
    </div>
	<div class="col-sm">
		<div class="container" >
		  <form action="login" method="post" class="form-signin text-center">
			  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
			  <label for="inputEmail" class="sr-only">Username</label>
			  <input type="text" id="username" name="username" class="form-control" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Username" required autofocus>
			  <label for="inputPassword" class="sr-only">Password</label>
			  <input type="password" id="inputPassword" name="password" class="form-control" style="background-color: rgba(0, 0, 0, 0.1)" placeholder="Password" required>
			  
			  <button class="btn btn-lg btn-dark" type="submit">Sign in</button>
			</form>
			<br>
			<form action="registerUser.jsp" method="get" class="form-signin text-center">
			  <p>Not Registered?<button class="btn btn-lg btn-primary btn-dark" type="submit">Create An account</button></p>
			</form>
			
		</div>
	</div>
    <div class="col-sm">
    </div>
</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>