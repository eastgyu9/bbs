<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ include file="include/header.jsp" %>
	<title>회원가입</title>
	
    <form action="/member/join" method="post">
        이메일:&nbsp;<input type="text" name="memberEmail" id="memberEmail" onblur="emailCheck()" placeholder="이메일">
        <p id="check-result"></p>
        비밀번호:&nbsp;<input type="text" name="memberPassword" placeholder="비밀번호"><br>
        이름:&nbsp;<input type="text" name="memberName" placeholder="이름"><br>
        나이:&nbsp;<input type="text" name="memberAge" placeholder="나이"><br>
        전화번호:&nbsp;<input type="text" name="memberPhone" placeholder="전화번호"><br>
        <input type="submit" value="회원가입">
    </form>
</body>
</html>
