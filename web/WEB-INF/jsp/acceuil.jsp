<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 28/01/2020
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Acceuil</title>
        <script src="<c:url value="/inc/jquery-3.4.1.js"></c:url>"></script>
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.css"></c:url>"/>
        <script src="<c:url value="/inc/bootstrap/js/bootstrap.js"></c:url>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"/>
        <div class="container-md">
            <div>
                <h1 id="acceuil">Bonjour <c:out
                        value="${sessionUtilisateur.prenom} ${sessionUtilisateur.nom}"></c:out></h1>
            </div>
        </div>
    </body>
</html>
