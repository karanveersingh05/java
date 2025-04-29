<html>
    <head>
        <title>Greeting</title>
    </head>
    <body>
        <%
        String user= request.getParameter("username");
        if(user== null|| user.trim().equals("")){
            user="Stranger";
        }
        %>
        <h2>Hello,<%= user%> </h2>
    </body>
</html>