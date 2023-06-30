<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="include/header.jsp" %>
	<title>index</title>

	<h3>${ today }</h3>
	<h1>Welcome MyProject</h1>
	<h2>getRequestURI: <%=request.getRequestURI() %></h2>
	<h2>getServletPath: <%=request.getServletPath() %></h2>
	<h2>getServletContext.ServerInfo: <%=request.getServletContext().getServerInfo() %></h2>
	<h2>getServletContext.SessionTimeout: <%=request.getServletContext().getSessionTimeout() %>(분)</h2>

	<c:choose>
	<c:when test="${empty sessionScope.sessionEmail}">
		<%-- 로그인되지 않은 상태 --%>
		<h3>회원가입/로그인 해주세요!</h3>
	</c:when>
	<c:otherwise>
		<%-- 로그인 상태 --%>
		<%-- 세션에 저장된 값을 쓴다는 것을 명시 세션.저장된 키이름.속성 --%>
		<h3>"${sessionScope.sessionName.memberName}(${sessionScope.sessionEmail.memberEmail})"님 안녕하세요!</h3>
	</c:otherwise>
	</c:choose>
</body>
</html>