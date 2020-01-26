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
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Création d'une projet</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>"/>
    </head>
    <body>
        <c:import url="/inc/menu.jsp"></c:import>
        <div>
            <form method="post" action="<c:url value="/creationProjet"></c:url>" <%--enctype="multipart/form-data"--%>>
                <fieldset>
                    <legend>Informations client</legend>
                    <%-- Si et seulement si la Map des clients en session n'est pas vide, alors on propose un choix à l'utilisateur --%>
                    <c:if test="${ !empty sessionScope.clients }">
                        <label for="choixNouveauClient">Nouveau client ? <span class="requis">*</span></label>
                        <input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="nouveauClient"
                               checked/> Oui
                        <input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="ancienClient"/> Non
                        <br/><br/>
                    </c:if>

                    <c:set var="client" value="${ projet.client }" scope="request"></c:set>
                    <div id="nouveauClient">
                        <c:import url="/inc/inc_client_form.jsp"></c:import>
                    </div>

                    <%-- Si et seulement si la Map des clients en session n'est pas vide, alors on crée la liste déroulante --%>
                    <c:if test="${ !empty sessionScope.clients }">
                        <div id="ancienClient">
                            <select name="listeClients" id="listeClients">
                                <option value="">Choisissez un client...</option>
                                    <%-- Boucle sur la map des clients --%>
                                <c:forEach items="${ sessionScope.clients }" var="mapClients">
                                    <%--  L'expression EL ${mapClients.value} permet de cibler l'objet Client stocké en tant que valeur dans la Map,
                                          et on cible ensuite simplement ses propriétés nom et prenom comme on le ferait avec n'importe quel bean. --%>
                                    <option value="${ mapClients.key }">${ mapClients.value.prenom } ${ mapClients.value.nom }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                </fieldset>
                <fieldset>
                    <legend>Informations projet</legend>

                    <label for="dateProjet">Date de création <span class="requis">*</span></label>
                    <input type="text" id="dateProjet" name="dateProjet"
                           value="<joda:format value="${ projet.date_creation }" pattern="dd-MM-yyyy"></joda:format>"
                           size="10" maxlength="10" disabled/>
                    <span class="erreur">${form.erreurs['dateProjet']}</span>
                    <br/>

                    <label for="numeroProjet">Numéro de projet <span class="requis">*</span></label>
                    <input type="text" id="numeroProjet" name="numeroProjet"
                           value="<c:out value="${projet.numero_projet}"></c:out>" size="10" maxlength="10"/>
                    <span class="erreur">${form.erreurs['numeroProjet']}</span>
                    <br/>

                    <label for="avancementProjet">Avancement <span class="requis">*</span></label>
                    <input type="text" id="avancementProjet" name="avancementProjet"
                           value="<c:out value="${projet.avancement}"></c:out>" size="30" maxlength="30"/>
                    <span class="erreur">${form.erreurs['avancementProjet']}</span>
                    <br/>

                    <label for="modePaiementProjet">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementProjet" name="modePaiementProjet"
                           value="<c:out value="${projet.modalite_paiement}"></c:out>" size="30" maxlength="30"/>
                    <span class="erreur">${form.erreurs['modePaiementProjet']}</span>
                    <br/>

                    <label for="dateDebutProjet">Date de début de préstation<span class="requis">*</span></label>
                    <input type="text" id="dateDebutProjet" name="dateDebutProjet"
                           value="<joda:format value="${ projet.date_debut_prestation }" pattern="dd-MM-yyyy"></joda:format>"
                           size="10" maxlength="10"/>
                    <span class="erreur">${form.erreurs['dateDebutProjet']}</span>
                    <br/>

                    <label for="dateFinProjet">Date de fin de préstation<span class="requis">*</span></label>
                    <input type="text" id="dateFinProjet" name="dateFinProjet"
                           value="<joda:format value="${ projet.date_fin_prestation }" pattern="dd-MM-yyyy"></joda:format>"
                           size="10" maxlength="10"/>
                    <span class="erreur">${form.erreurs['dateFinProjet']}</span>
                    <br/>

                    <label for="adresseProjet">Adresse <span class="requis">*</span></label>
                    <input type="text" id="adresseProjet" name="adresseProjet"
                           value="<c:out value="${projet.adresse}"></c:out>" size="20" maxlength="200"/>
                    <span class="erreur">${form.erreurs['adresseProjet']}</span>
                    <br/>

                    <p class="info">${ form.resultat }</p>
                </fieldset>
                <input type="submit" value="Valider"/>
                <input type="reset" value="Remettre à zéro"/> <br/>
            </form>
        </div>

        <%-- Inclusion de la bibliothèque jQuery. Vous trouverez des cours sur JavaScript et jQuery aux adresses suivantes :
               - http://www.siteduzero.com/tutoriel-3-309961-dynamisez-vos-sites-web-avec-javascript.html
               - http://www.siteduzero.com/tutoriel-3-659477-un-site-web-dynamique-avec-jquery.html

             Si vous ne souhaitez pas télécharger et ajouter jQuery à votre projet, vous pouvez utiliser la version fournie directement en ligne par Google :
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
        --%>
        <script src="<c:url value="/inc/jquery-3.4.1.js"></c:url>"></script>

        <%-- Petite fonction jQuery permettant le remplacement de la première partie du formulaire par la liste déroulante, au clic sur le bouton radio. --%>
        <script>
            jQuery(document).ready(function () {
                /* 1 - Au lancement de la page, on cache le bloc d'éléments du formulaire correspondant aux clients existants */
                $("div#ancienClient").hide();
                /* 2 - Au clic sur un des deux boutons radio "choixNouveauClient", on affiche le bloc d'éléments correspondant (nouveau ou ancien client) */
                jQuery('input[name=choixNouveauClient]:radio').click(function () {
                    $("div#nouveauClient").hide();
                    $("div#ancienClient").hide();
                    var divId = jQuery(this).val();
                    $("div#" + divId).show();
                });
            });
        </script>
    </body>
</html>