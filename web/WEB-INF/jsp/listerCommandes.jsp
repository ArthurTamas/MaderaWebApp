<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 23/01/2020
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des commandes existantes</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" ></c:import>
        <div id="corps">
            <c:choose>
                <%-- Si aucune commande n'existe en session, affichage d'un message par défaut. --%>
                <c:when test="${ empty sessionScope.commandes }">
                    <p class="erreur">Aucune commande enregistrée.</p>
                </c:when>
                <%-- Sinon, affichage du tableau. --%>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>Client</th>
                            <th>Date</th>
                            <th>Montant</th>
                            <th>Mode de paiement</th>
                            <th>Statut de paiement</th>
                            <th>Mode de livraison</th>
                            <th>Statut de livraison</th>
                            <th class="action">Action</th>
                        </tr>
                            <%-- Parcours de la Map des commandes en session, et utilisation de l'objet varStatus. --%>
                        <c:forEach items="${ sessionScope.commandes }" var="mapCommandes" varStatus="boucle">
                            <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                            <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                                    <%-- Affichage des propriétés du bean Commande, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                                <td><c:out value="${ mapCommandes.value.client.prenom } ${ mapCommandes.value.client.nom }"></c:out></td>
                                <td><joda:format value="${ mapCommandes.value.date }" pattern="dd/MM/yyyy HH:mm:ss"></joda:format></td>
                                <td><c:out value="${ mapCommandes.value.montant }"></c:out></td>
                                <td><c:out value="${ mapCommandes.value.modePaiement }"></c:out></td>
                                <td><c:out value="${ mapCommandes.value.statutPaiement }"></c:out></td>
                                <td><c:out value="${ mapCommandes.value.modeLivraison }"></c:out></td>
                                <td><c:out value="${ mapCommandes.value.statutLivraison }"></c:out></td>
                                    <%-- Lien vers la servlet de suppression, avec passage de la date de la commande - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param></c:param>. --%>
                                <td class="action">
                                    <a href="<c:url value="/suppressionCommande"><c:param name="idCommande" value="${ mapCommandes.key }" ></c:param></c:url>">
                                        <img src="<c:url value="/inc/supprimer.png"></c:url>" alt="Supprimer" />
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>