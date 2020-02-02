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
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Création d'un client</title>
        <script src="<c:url value="/inc/jquery-3.4.1.js"></c:url>"></script>
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.css"></c:url>"/>
        <script src="<c:url value="/inc/bootstrap/js/bootstrap.js"></c:url>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"/>7
        <div class="container-md">
            <div>
                <form method="POST"
                      action="<c:url value="/modifierInfosClient"><c:param name="idClient" value="${ requestScope.client.id }" ></c:param></c:url>">
                    <fieldset>
                        <legend>Modification d'un client</legend>
                        <c:import url="/inc/inc_client_form.jsp"/>
                    </fieldset>
                    <p class="info">${ form.resultat }</p>
                    <input class="btn btn-primary" id="btn" type="submit" value="Valider"/>
                    <input class="btn btn-dark" id="btn" type="reset" value="Remettre à zéro"/>
                    <br/>
                </form>
            </div>
        </div>
    </body>
</html>