<% String username = (String) session.getAttribute("username");%>
<html>
<head>
    <title>Wrong Password</title>
</head>
<body>
    <h2>Hello <%= username %> Your password is wrong.</h2>
</body>
</html>
