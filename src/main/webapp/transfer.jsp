<%@page import="clients.BankClient"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

 
<html>
<head>
  <title>CNVR</title>
  <style></style>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://bootswatch.com/3/cosmo/bootstrap.min.css" crossorigin="anonymous">
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>

  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">
          CNVR
        </a>
      </div>
    </div>
  </nav>
  <div class="container"> 

    <br>
    <div class="row">
      <div class="col-xs-10 col-xs-push-1 col-md-push-2 col-md-8 col-lg-6 col-lg-push-3" id="results">
         <div class="panel panel-danger">
            <div class="panel-heading">Resultado</div>
            <div class="panel-body">
            <c:if test="${amount != null and not empty clientBean1 and not empty clientBean2}">
            
            <div class="panel panel-danger"><div class="panel-body">
	            <h5><strong>Cantidad transferida:</strong> <%= request.getAttribute("amount") %> €</h5></div></div>
	            <h4>Cuenta origen</h4>
	              <table class="table table-striped">
					<tr><td class="col-xs-6"><strong><span class="glyphicon glyphicon-user"></span> Nombre  </strong></td><td> <c:out value="${clientBean1.name}"/> 
	                </td></tr>
	                <tr><td class="col-xs-6"><strong><span class="glyphicon glyphicon-piggy-bank"></span> Nº de cuenta  </strong></td><td><c:out value="${clientBean1.account}"/>
	</td></tr>
	                <tr><td class="col-xs-6"><strong><span class="glyphicon glyphicon-eur"></span> Saldo  </strong></td><td><c:out value="${clientBean1.balance}"/> €
	</td></tr>
	              </table>
	              <h4>Cuenta destino</h4>
	              <table class="table table-striped">
	                <tr><td class="col-xs-6"><strong><span class="glyphicon glyphicon-user"></span> Nombre  </strong></td><td> <c:out value="${clientBean2.name}"/> 
	                </td></tr>
	                <tr><td class="col-xs-6"><strong><span class="glyphicon glyphicon-piggy-bank"></span> Nº de cuenta  </strong></td><td><c:out value="${clientBean2.account}"/>
	</td></tr>
	                <tr><td class="col-xs-6"><strong><span class="glyphicon glyphicon-eur"></span> Saldo  </strong></td><td><c:out value="${clientBean2.balance}"/> €
	</td></tr>
	              </table>
              </c:if>
              <p id="msg"><span class="glyphicon glyphicon-<%= request.getAttribute("icon") %>"></span>  <%= request.getAttribute("msg") %></p>
              
            </div>
          </div>
         <p id="back-button"><a href="/cnvr" ><span class="glyphicon glyphicon-backward"></span>  Volver</a></p>
      	
      </div>

    </div>
  </div>
  <style>
  #msg, #back-button {
 	text-align:center
 }
  a:hover, a:focus {
    text-decoration: none;
    outline: none;
  }
  #back-button{
  font-size: 16px;}

  
  </style>
  <script>
    console.log("<%= request.getAttribute("ip") %> ");
  </script>
</body>
</html>

