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
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"/>
        <div>
            <h1 id="acceuil">Bonjour <c:out value="${sessionUtilisateur.prenom} ${sessionUtilisateur.nom}"></c:out></h1>
        </div>
    </body>
</html>