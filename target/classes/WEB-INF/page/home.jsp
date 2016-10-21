<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
	<head>  
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
		<title>Insert title here</title>  
	</head>  
	<script src="<%=request.getContextPath()%>/styles/js/angular.min.js"></script>
	<body>
	   	<div ng-app>
			<p>名字 : <input type="text" ng-model="name"></p>
			<h1>Hello {{name}}</h1>
		</div>
	</body>
</html>  
