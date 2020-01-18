<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Test des expressions EL</title>
    </head>
    <body>
        <p>
            <!-- Initialisation d'un bean de type Coyote avec une
            action standard, pour l'exemple : -->
            <jsp:useBean id="coyote" class="com.beans.Coyote"/>
            <!-- Initialisation de sa propriété 'prénom' : -->
            <jsp:setProperty name="coyote" property="prenom" value="WileE."/>
            <!-- Et affichage de sa valeur : -->
            ${ coyote.prenom}

        </p>
    </body>
</html>