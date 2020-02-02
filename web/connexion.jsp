<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 24/01/2020
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="<c:url value="/inc/jquery-3.4.1.js"></c:url>"></script>
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.css"></c:url>"/>
        <link rel="stylesheet" href="<c:url value="/inc/floating-labels.css"></c:url>"/>
        <script src="<c:url value="/inc/bootstrap/js/bootstrap.js"></c:url>"></script>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>
    </head>
    <body>
        <form class="form-signin" method="post" action="connexion">
            <div class="text-center mb-4">
                <img class="mb-4" src="<c:url value="/inc/LogoColossus.png"></c:url>" alt="" width="200" height="100">
                </br>
                <%--<h1 class="h3 mb-3 font-weight-normal">Colossus</h1>--%>
            </div>
            <input style="opacity: 0; position: absolute" />
            <div class="form-label-group">
                <input type="email" id="email" name="email" class="form-control" placeholder="Adresse email" required
                       autocomplete="new-password" autofocus>
                <label for="email">Adresse email</label>
                <span class="erreur">${form.erreurs['email']}</span>
            </div>

            <input style="opacity: 0; position: absolute" />
            <div class="form-label-group">
                <input type="password" id="motdepasse" name="motdepasse" class="form-control" placeholder="Mot depasse"
                       autocomplete="new-password" required>
                <label for="motdepasse">Mot de passe</label>
                <span class="erreur">${form.erreurs['motdepasse']}</span>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
            <p class="${empty form.erreurs && utilsateur != null ? 'succes' : 'erreur'}">${form.resultat}</p>

            <p class="mt-5 mb-3 text-muted text-center">&copy;2020</p>


        </form>
    </body>
    <%--<div class="container-md">
        <form method="post" action="connexion">
            <fieldset>
                <legend>Connexion</legend>

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="" name="email" value="<c:out value="${utilisateur.email}"/>" size="20"
                       maxlength="60"/>
                <span class="erreur">${form.erreurs['email']}</span>
                <br/>

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="" name="motdepasse" value="" size="20" maxlength="20"/>
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br/>

                <input class="btn btn-primary" type="submit" value="Connexion" class="sansLabel"/>
                <br/>

            </fieldset>
        </form>
    </div>--%>
</html>