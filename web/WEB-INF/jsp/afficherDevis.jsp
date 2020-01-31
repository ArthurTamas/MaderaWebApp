<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 29/01/2020
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Affichage d'un projet</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"></c:import>
        <div id="corps">
            <p class="info">${ form.resultat }</p>

            <p>Client</p>
            <p>Nom : <c:out value="${ projet.client.nom }"></c:out></p>
            <p>Prénom : <c:out value="${ projet.client.prenom }"></c:out></p>
            <p>Adresse : <c:out value="${ projet.client.adresse }"></c:out></p>
            <p>Numéro de téléphone : <c:out value="${ projet.client.telephone }"></c:out></p>
            <p>Email : <c:out value="${ projet.client.email }"></c:out></p>

            <p>Projet</p>
            <p>Numéro de projet : <c:out value="${ projet.numero_projet }"></c:out></p>
            <p>Date de début : <joda:format value="${ projet.date_debut_prestation }"
                                            pattern="dd/MM/yyyy HH:mm:ss"></joda:format></p>
            <p>Date de fin : <joda:format value="${ projet.date_fin_prestation }"
                                          pattern="dd/MM/yyyy HH:mm:ss"></joda:format></p>
            <p>Adresse : <c:out value="${ projet.adresse }"></c:out></p>
            <p>Commercial attribué au projet : <c:out value="${ projet.commercial }"></c:out></p>
            <p>Gamme : <c:out value="${ projet.gamme }"></c:out></p>

            <p>Devis</p>

            <p>Numero de devis : <joda:format value="${ projet.devis.numeroDevis }"
                                              pattern="dd/MM/yyyy HH:mm:ss"></joda:format></p>
            <p>Status du devis : <c:out value="${ projet.devis.status }"></c:out></p>
            <c:if test="${ !empty projet.devis.ligneDevis }">
                <div id="lignesDevis">
                    <p>Lignes du devis : <c:out value="${ projet.devis.ligneDevis }"></c:out></p>
                        <%-- Boucle sur la liste des lignes du devis--%>
                    <c:forEach items="${ projet.devis.ligneDevis }" var="ligne">
                        <%--  L'expression EL ${mapClients.value} permet de cibler l'objet Client stocké en tant que valeur dans la Map,
                              et on cible ensuite simplement ses propriétés nom et prenom comme on le ferait avec n'importe quel bean. --%>
                        <p> value="${ ligne }</p>
                    </c:forEach>
                </div>
            </c:if>

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
                    <tr class="totaHT">
                        <td>Total TTC</td>
                        <td><c:out value="${ projet.devis.prixTTC }"></c:out></td>
                    </tr>
                </table>
            </c:if>

            <p>Prix HT : <c:out value="${ projet.devis.prixHT }"></c:out></p>
            <p>Prix TTC : <c:out value="${ projet.devis.prixTTC }"></c:out></p>
        </div>
    </body>
</html>
