<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 23/01/2020
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Liste des clients existants</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"></c:import>
        <div id="corps">
            <c:choose>
                <%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
                <c:when test="${ empty sessionScope.clients }">
                    <p class="erreur">Aucun client enregistré.</p>
                </c:when>
                <%-- Sinon, affichage du tableau. --%>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Adresse</th>
                            <th>Téléphone</th>
                            <th>Email</th>
                            <th class="action">Supprimer</th>
                            <th class="actionDetail">Détails</th>

                        </tr>
                            <%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
                        <c:forEach items="${ sessionScope.clients }" var="mapClients" varStatus="boucle">
                            <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                            <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                                    <%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                                <td><c:out value="${ mapClients.value.nom }"></c:out></td>
                                <td><c:out value="${ mapClients.value.prenom }"></c:out></td>
                                <td><c:out value="${ mapClients.value.adresse }"></c:out></td>
                                <td><c:out value="${ mapClients.value.telephone }"></c:out></td>
                                <td><c:out value="${ mapClients.value.email }"></c:out></td>

                                <td class="action">
                                    <a href="<c:url value="/suppressionClient"><c:param name="idClient" value="${ mapClients.key }" ></c:param></c:url>">
                                        <img src="<c:url value="/inc/supprimer.png"></c:url>" alt="Supprimer"/>
                                    </a>
                                </td>
                                <td class="action">

                                    <a href="<c:url value="/detailsProjet"><c:param name="idProjet" value="${ mapProjets.key }" ></c:param></c:url>"
                                       onclick="return confirm('Etes vous sur ?')">
                                        <img src="<c:url value="/inc/edit-file-icon.png"></c:url>" alt="Details"/>
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