<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 21/01/2020
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- div clas="menu" --%>
<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbarDark">

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${ sessionScope.userGroup == 'admin' || sessionScope.userGroup == 'commercial'}">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/creationClient"/>">Créer un nouveau client</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/creationProjet"/>">Créer un nouveau projet</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/listeClients"/>">Voir les clients existants</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/listeProjets"/>">Liste de tous les projets</a>
                </li>
            </c:if>
            <c:if test="${  sessionScope.userGroup == 'client'}">
                <li class="nav-item">
                    <a class="nav-link"
                       href="<c:url value="/detailsClient"><c:param name="idClient" value="${ sessionScope.sessionUtilisateur.id}" ></c:param></c:url>">Mes
                        informations</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/listeProjets"/>">Liste de mes projets</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/notImplement.jsp"/>">Contact</a>
                </li>
            </c:if>
            <li class="nav-item align-content-md-end">
                <a class="nav-link" id="deconnexion" href="<c:url value="/deconnexion"/>">Déconnexion</a>
            </li>
        </ul>
    </div>
</nav>

<%--<c:if test="${ sessionScope.userGroup == 'admin' || sessionScope.userGroup == 'commercial'}">
        <p><a href="<c:url value="/creationClient"/>">Créer un nouveau client</a></p>
        <p><a href="<c:url value="/creationProjet"/>">Créer un nouveau projet</a></p>
        <p><a href="<c:url value="/listeClients"/>">Voir les clients existants</a></p>
        <p><a href="<c:url value="/listeProjets"/>">Liste de tous les projets</a></p>
    </c:if>
    <c:if test="${ sessionScope.userGroup == 'client'}">
        <p>
            <a href="<c:url value="/detailsClient"><c:param name="idClient" value="${ sessionScope.sessionUtilisateur.id}" ></c:param></c:url>">Mes
                informations</a></p>
        <p><a href="<c:url value="/listeProjets"/>">Liste de mes projets</a></p>
        <p><a href="<c:url value="/notImplement.jsp"/>">Contact</a></p>
    </c:if>
    <p><a id="deconnexion" href="<c:url value="/deconnexion"/>">Déconnexion</a></p>
--%>



