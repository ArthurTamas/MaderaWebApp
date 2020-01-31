<%--
  Created by IntelliJ IDEA.
  User: Hugo
  Date: 29/01/2020
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Détails du projet</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>" />
</head>
<body>
<c:import url="/inc/menu.jsp" ></c:import>
<div id="corps">
    <c:choose>
        <%-- Si aucun projet n'existe en session, affichage d'un message par défaut. --%>
        <c:when test="${ empty sessionScope.projets }">
            <p class="erreur">Aucun projet enregistré.</p>
        </c:when>
        <%-- Sinon, affichage du tableau. --%>
        <c:otherwise>

                    <p>Numéro de projet</p>
                    <p>Avancement</p>
                    <p>Date de création</p>
                    <p>Modalités de paiement</p>
                    <p>Date de début de prestation</p>
                    <p>Date de fin de prestation</p>
                    <p>Adresse</p>
                    <p>Nom du commercial</p>



                            <p><c:out value="${ requestScope.projet.numero_projet }"></c:out></p>
                            <p><c:out value="${ requestScope.projet.avancement }"></c:out></p>
                            <p><joda:format value="${ requestScope.projet.date_creation }" pattern="dd/MM/yyyy HH:mm:ss"></joda:format></p>
                            <p><c:out value="${ requestScope.projet.modalite_paiement }"></c:out></p>
                            <p><joda:format value="${ requestScope.projet.date_debut_prestation }" pattern="dd/MM/yyyy HH:mm:ss"></joda:format></p>
                            <p><joda:format value="${ requestScope.projet.date_fin_prestation }" pattern="dd/MM/yyyy HH:mm:ss"></joda:format></p>
                            <p><c:out value="${ requestScope.projet.adresse }"></c:out></p>
                            <p><c:out value="${ requestScope.projet.commercial.nom }"></c:out></p>


        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
