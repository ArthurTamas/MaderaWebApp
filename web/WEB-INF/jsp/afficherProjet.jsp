<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 26/01/2020
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 23/01/2020
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Affichage d'un projet</title>
        <script src="<c:url value="/inc/jquery-3.4.1.js"></c:url>"></script>
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.css"></c:url>"/>
        <script src="<c:url value="/inc/bootstrap/js/bootstrap.js"></c:url>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"></c:import>
        <div class="container" id="margintop">
            <p class="info">${ form.resultat }</p>
            <div class="row">
                <div class="col-lg-4">
                    <p id="composMaison">Client</p>
                    <p>Nom : <c:out value="${ projet.client.nom }"></c:out></p>
                    <p>Prénom : <c:out value="${ projet.client.prenom }"></c:out></p>

                    <c:if test="${ !empty projet.client.adresse }">
                        <p>Adresse : <c:out value="${ projet.client.adresse }"></c:out></p>
                    </c:if>

                    <c:if test="${ !empty projet.client.telephone }">
                        <p>Numéro de téléphone : <c:out value="${ projet.client.telephone }"></c:out></p>
                    </c:if>

                    <c:if test="${ !empty projet.client.email }">
                        <p>Email : <c:out value="${ projet.client.email }"></c:out></p>
                    </c:if>
                </div>
                <div class="col-lg-4">
                    <p id="composMaison">Projet</p>
                    <p>Numéro de projet : <c:out value="${ projet.numero_projet }"></c:out></p>
                    <p>Date de début : <joda:format value="${ projet.date_debut_prestation }"
                                                    pattern="dd/MM/yyyy"></joda:format></p>
                    <p>Date de fin : <joda:format value="${ projet.date_fin_prestation }"
                                                  pattern="dd/MM/yyyy"></joda:format></p>
                    <p>Modalité de paiement : <c:out value="${ projet.modalite_paiement }"></c:out></p>
                    <p>Avancement : <c:out value="${ projet.avancement }"></c:out></p>
                    <p>Adresse : <c:out value="${ projet.adresse }"></c:out></p>
                    <p>Commercial attribué au projet : <c:out
                            value="${ projet.commercial.prenom } ${ projet.commercial.nom}"></c:out></p>
                    <p>Gamme : <c:out value="${ projet.gamme.libelle }"></c:out></p>
                </div>
                <div class="col-lg-4">
                    <p id="composMaison">Devis</p>
                    <p>Numero de devis : <c:out value="${ projet.devis.numeroDevis }"></c:out></p>
                    <p>Status du devis : <c:out value="${ projet.devis.status }"></c:out></p>
                    <c:if test="${ !empty projet.devis.ligneDevis }">
                        <table>
                            <tr>
                                <th>Libéllé de l'item</th>
                                <th>Prix</th>
                            </tr>
                                <%-- Parcours des lignes du devis, et utilisation de l'objet varStatus. --%>
                            <c:forEach items="${ projet.devis.ligneDevis }" var="ligne" varStatus="boucle">
                                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">

                                    <td><c:out value="${ ligne.get(0) }"></c:out></td>
                                    <td><c:out value="${ ligne.get(1) }"></c:out></td>

                                </tr>
                            </c:forEach>
                            <tr class="totalHT">
                                <td>Total HT</td>
                                <td><c:out value="${ projet.devis.prixHT }"></c:out></td>
                            </tr>
                            <tr class="totalTTC">
                                <td>Total TTC</td>
                                <td><c:out value="${ projet.devis.prixTTC }"></c:out></td>
                            </tr>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
