<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- tiles 라이브러리를 사용할 수 있도록 선언 -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${category eq 'cu' }"><c:set var="title" value="고객관리"/> </c:when>
	<c:when test="${category eq 'hr' }"><c:set var="title" value="사원목록"/> </c:when>
	<c:when test="${category eq 'no' }"><c:set var="title" value="공지사항"/> </c:when>
	<c:when test="${category eq 'bo' }"><c:set var="title" value="방명록"/> </c:when>
	<c:when test="${category eq 'da' }"><c:set var="title" value="공공데이터"/> </c:when>
	<c:when test="${category eq 'join' }"><c:set var="title" value="회원가입"/> </c:when>
</c:choose>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <title>AlphaCar</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/style.css?v=<%=new java.util.Date().getTime() %>" />
  <link rel="stylesheet" 
    href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" 
    integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" 
    crossorigin="anonymous">
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header"/>	
	<tiles:insertAttribute name="content"/>
	<tiles:insertAttribute name="footer"/>	
</body>
</html>