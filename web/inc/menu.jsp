<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 21/01/2020
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="menu">
    <c:if test="${ sessionScope.userGroup == 'admin'}">
        <p><a href="<c:url value="/creationClient"/>">Créer un nouveau client</a></p>
        <p><a href="<c:url value="/creationProjet"/>">Créer un nouveau projet</a></p>
        <p><a href="<c:url value="/listeClients"/>">Voir les clients existants</a></p>
    </c:if>
    <c:if test="${ sessionScope.userGroup == 'client'}">
        <p><a href="<c:url value="/notImplement.jsp"/>">Mes informations</a></p>
        <p><a href="<c:url value="/listeProjets"/>">Liste de mes projets</a></p>
        <p><a href="<c:url value="/notImplement.jsp"/>">Contact</a></p>
    </c:if>
    <p><a id="deconnexion" href="<c:url value="/deconnexion"/>">Déconnexion</a></p>

</div>



