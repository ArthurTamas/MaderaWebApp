<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 18/01/2020
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Test</title>
    </head>
    <body>
        <p>Ceci est une page générée depuis une JSP.</p>
        <p>
            <%
                String attribut = (String) request.getAttribute("test2");
                out.println(attribut);
            %>
        </p>
        <p>
            Récupération du bean :
            <%
                com.beans.Coyote notreBean = (com.beans.Coyote)
                        request.getAttribute("coyote");
                out.println(notreBean.getPrenom());
                out.println(notreBean.getNom());
            %>
            <%= "Bip bip !" %>

        </p>
    </body>
</html>