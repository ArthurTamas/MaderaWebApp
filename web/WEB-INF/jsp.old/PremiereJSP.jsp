<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Test</title>
    </head>
    <body>
        <p>Ceci est une page générée depuis une JSP.</p>
        <p>
            Parametre du GET si ils existent
            ${test}
            ${auteur}
        </p>
        <p>
            Récupération du bean :
            ${coyote.prenom}
            ${coyote.nom}
        </p>
        <p>
            Récupération de la liste :
            <%
                List<Integer> liste = (List<Integer>) request.getAttribute("liste");
                for (Integer i : liste) {
                    out.println(i + " : ");
                }
            %>
        </p>
        <p>
            Récupération du jour du mois :
            <%
                Integer jourDuMois = (Integer) request.getAttribute("jour");
                if (jourDuMois % 2 == 0) {
                    out.println("Jour pair : " + jourDuMois);
                } else {
                    out.println("Jour impair : " + jourDuMois);
                }
            %>
        </p>

    </body>
</html>