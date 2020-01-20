<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 19/01/2020
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Affichage d'un client</title>
        <link type="text/css" rel="stylesheet"
              href="../../inc/style.css"/>
    </head>
    <body>
        <%-- Affichage de la chaine "message" transmise par la
        servlet --%>
        <p class="info">${ message }</p>
        <%-- Puis affichage des données enregistrées dans le bean
        "client" transmis par la servlet --%>
        <p>Nom : ${ client.nom }</p>
        <p>Prenom : ${ client.prenom }</p>
        <p>Adresse : ${ client.adresse }</p>
        <p>Numéro de téléphone : ${ client.telephone }</p>
        <p>Email : ${ client.email }</p>
    </body>
</html>

