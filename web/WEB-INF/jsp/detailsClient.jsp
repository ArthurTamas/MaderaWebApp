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
        <meta charset="utf-8"/>
        <title>Détails du client</title>
        <script src="<c:url value="/inc/jquery-3.4.1.js"></c:url>"></script>
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.css"></c:url>"/>
        <script src="<c:url value="/inc/bootstrap/js/bootstrap.js"></c:url>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"></c:import>
        <div class="container-md">
            <div id="corps">
                <c:choose>
                    <%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
                    <c:when test="${ empty requestScope.client }">
                        <p class="erreur">Aucun client enregistré.</p>
                    </c:when>
                    <%-- Sinon, affichage du tableau. --%>
                    <c:otherwise>

                        <h1 id="composMaison">Details du client <c:out value="${ requestScope.client.id }"></c:out></h1>
                        <p>Nom : <c:out value="${ requestScope.client.nom }"></c:out></p>
                        <p>Prénom : <c:out value="${ requestScope.client.prenom }"></c:out></p>
                        <c:if test="${ !empty requestScope.client.adresse }">
                            <p>Adresse : <c:out value="${ requestScope.client.adresse }"></c:out></p>
                        </c:if>

                        <c:if test="${ !empty requestScope.client.telephone }">
                            <p>Numéro de téléphone : <c:out value="${ requestScope.client.telephone }"></c:out></p>
                        </c:if>

                        <c:if test="${ !empty requestScope.client.email }">
                            <p>Email : <c:out value="${ requestScope.client.email }"></c:out></p>
                        </c:if>
                        <p>
                            <a href="<c:url value="/modifierInfosClient"><c:param name="idClient" value="${ requestScope.client.id }" ></c:param></c:url>">Modifier
                                les informations</a></p>

                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
