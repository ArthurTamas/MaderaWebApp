<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Création d'un client</title>
        <link rel="stylesheet" href="<c:url value="/inc/boostrap/css/bootstrap.min.css"></c:url>"/>
        <script src="<c:url value="/inc/boostrap/js/bootstrap.min.css"></c:url>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"/>
        <div>
            <form method="POST" action="<c:url value="/modifierInfosClient"><c:param name="idClient" value="${ requestScope.client.id }" ></c:param></c:url>">
                <fieldset>
                    <legend>Modification d'un client</legend>
                    <c:import url="/inc/inc_client_form.jsp"/>
                </fieldset>
                <p class="info">${ form.resultat }</p>
                <input type="submit" value="Valider"/>
                <input type="reset" value="Remettre à zéro"/> <br/>
            </form>
        </div>
    </body>
</html>