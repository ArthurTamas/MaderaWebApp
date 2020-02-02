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
        <c:import url="/inc/menu.jsp"/>
        <div class="container">
            <form method="POST" action="<c:url value="/creationClient"/>" <%--enctype="multipart/form-data"--%>>
                <fieldset>
                    <legend>Création d'un client</legend>
                    <c:import url="/inc/inc_client_form.jsp"/>
                </fieldset>
                <input class="btn btn-primary" id="btn" type="submit" value="Valider"/>
                <input class="btn btn-dark" id="btn" type="reset" value="Remettre à zéro"/>
                <br/>
            </form>
        </div>
    </body>
</html>