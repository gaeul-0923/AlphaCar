<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${category eq 'login' }"><c:set var="title" value="로그인" /></c:when>
	<c:when test="${category eq 'error' }"><c:set var="title" value="오류" /></c:when>
</c:choose>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>${title }</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" 
			href="css/style.css?v=<%=new java.util.Date().getTime() %>" />

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>


<body>
	<div>	
		<tiles:insertAttribute name='content' />
	</div>
</body>
</html>