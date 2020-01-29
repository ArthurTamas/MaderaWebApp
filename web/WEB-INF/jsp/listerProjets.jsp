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
    <title>Liste des projets existants</title>
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
            <table>
                <tr>
                    <th>Numéro de projet</th>
                    <th>Avancement</th>
                    <th>Date de création</th>
                    <th>Modalités de paiement</th>
                    <th>Date de début de prestation</th>
                    <th>Date de fin de prestation</th>
                    <th>Adresse</th>
                    <th>Nom du commercial</th>
                    <th class="action">Supprimer</th>
                </tr>
                    <%-- Parcours de la Map des projets en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.projets }" var="mapProjets" varStatus="boucle">
                    <%-- N'affiche que les projets correspondant au client en comparant l'id --%>
                    <c:if test="${ mapProjets.value.client.id == sessionUtilisateur.id }" >
                        <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                        <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                                <%-- Affichage des propriétés du bean Projet, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                            <td><c:out value="${ mapProjets.value.numero_projet }"></c:out></td>
                            <td><c:out value="${ mapProjets.value.avancement }"></c:out></td>
                            <td><c:out value="${ mapProjets.value.date_creation }"></c:out></td>
                            <td><c:out value="${ mapProjets.value.modalite_paiement }"></c:out></td>
                            <td><c:out value="${ mapProjets.value.date_debut_prestation }"></c:out></td>
                            <td><c:out value="${ mapProjets.value.date_fin_prestation }"></c:out></td>
                            <td><c:out value="${ mapProjets.value.adresse }"></c:out></td>
                            <td><c:out value="${ mapProjets.value.commercial.nom }"></c:out></td>
                                <%-- Lien vers la servlet de suppression, avec passage du nom du projet - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param></c:param>. --%>
                            <td class="action">
                                <a href="<c:url value="/suppressionProjet"><c:param name="idProjet" value="${ mapProjets.key }" ></c:param></c:url>">
                                    <img src="<c:url value="/inc/supprimer.png"></c:url>" alt="Supprimer" />
                                </a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
