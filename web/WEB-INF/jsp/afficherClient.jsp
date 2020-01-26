<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 19/01/2020
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Affichage d'un client</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"/>
        <div id="corps">
            <p class="info">${ form.resultat }</p>
            <p>Nom : <c:out value="${ client.nom }"/></p>
            <p>Prénom : <c:out value="${ client.prenom }"/></p>
            <p>Adresse : <c:out value="${ client.adresse }"/></p>
            <p>Numéro de téléphone : <c:out value="${ client.telephone }"/></p>
            <%--<p>Email : <c:out value="${ client.email }"/></p>--%>
        </div>
    </body>
</html>