<%
String username = request.getParameter("username");
String password = request.getParameter("password");
if ("1234".equals(password)) {
    session.setAttribute("username", username);
    response.sendRedirect("welcome.jsp");
} else {
    session.setAttribute("username", username);
    response.sendRedirect("wrong.jsp");
}
%>
