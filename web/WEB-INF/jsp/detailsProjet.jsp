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
        <title>Détails du projet</title>
        <script src="<c:url value="/inc/jquery-3.4.1.js"></c:url>"></script>
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.css"></c:url>"/>
        <script src="<c:url value="/inc/bootstrap/js/bootstrap.js"></c:url>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"></c:import>
        <div class="container" id="margintop">
            <div class="row">
                <c:choose>
                    <%-- Si aucun projet n'existe en session, affichage d'un message par défaut. --%>
                    <c:when test="${ empty requestScope.projet }">
                        <p class="erreur">Aucun projet enregistré.</p>
                    </c:when>
                    <%-- Sinon, affichage du tableau. --%>
                    <c:otherwise>
                        <div class="col-lg-6">

                            <h1 id="composMaison">Projet numéro <c:out
                                    value="${ requestScope.projet.numero_projet }"></c:out></h1>
                            <p>Date de début : <joda:format value="${ requestScope.projet.date_debut_prestation }"
                                                            pattern="dd/MM/yyyy"></joda:format></p>
                            <p>Date de fin : <joda:format value="${ requestScope.projet.date_fin_prestation }"
                                                          pattern="dd/MM/yyyy"></joda:format></p>
                            <p>Modalité de paiement : <c:out value="${ projet.modalite_paiement }"></c:out></p>
                            <p>Avancement : <c:out value="${ projet.avancement }"></c:out></p>
                            <p>Adresse : <c:out value="${ requestScope.projet.adresse }"></c:out></p>
                            <p>Commercial en charge : <c:out
                                    value="${ requestScope.projet.commercial.prenom } ${ requestScope.projet.commercial.nom}"></c:out></p>
                            <p>Gamme : <c:out value="${ requestScope.projet.gamme.libelle }"></c:out></p>
                            <p>Module : <c:out value="${ requestScope.projet.module.libelle }"></c:out></p>
                        </div>
                        <div class="col-lg-6">

                            <h1 id="composMaison">Devis numéro <c:out
                                    value="${ requestScope.projet.devis.numeroDevis }"></c:out></h1>
                            <p>Status du devis : <c:out value="${ requestScope.projet.devis.status }"></c:out></p>
                            <c:if test="${ !empty requestScope.projet.devis.ligneDevis }">
                                <table>
                                    <tr>
                                        <th>Libéllé de l'item</th>
                                        <th>Prix</th>
                                    </tr>
                                        <%-- Parcours des lignes du devis, et utilisation de l'objet varStatus. --%>
                                    <c:forEach items="${ requestScope.projet.devis.ligneDevis }" var="ligne"
                                               varStatus="boucle">
                                        <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                                        <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">

                                            <td><c:out value="${ ligne.get(0) }"></c:out></td>
                                            <td><c:out value="${ ligne.get(1) }"></c:out></td>

                                        </tr>
                                    </c:forEach>
                                    <tr class="totalHT">
                                        <td>Total HT</td>
                                        <td><c:out value="${ requestScope.projet.devis.prixHT }"></c:out></td>
                                    </tr>
                                    <tr class="totalTTC">
                                        <td>Total TTC</td>
                                        <td><c:out value="${ requestScope.projet.devis.prixTTC }"></c:out></td>
                                    </tr>
                                </table>
                            </c:if>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
