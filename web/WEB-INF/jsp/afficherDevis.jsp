<%--
  Created by IntelliJ IDEA.
  User: arthu
  Date: 29/01/2020
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"></c:url>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" ></c:import>
        <div id="corps">
            <p class="info">${ form.resultat }</p>
            <p>Client</p>
            <p>Nom : <c:out value="${ commande.client.nom }"></c:out></p>
            <p>Prénom : <c:out value="${ commande.client.prenom }"></c:out></p>
            <p>Adresse : <c:out value="${ commande.client.adresse }"></c:out></p>
            <p>Numéro de téléphone : <c:out value="${ commande.client.telephone }"></c:out></p>
            <p>Email : <c:out value="${ commande.client.email }"></c:out></p>
            <p>Commande</p>
            <p>Date  : <joda:format value="${ commande.date }" pattern="dd/MM/yyyy HH:mm:ss"></joda:format></p>
            <p>Montant  : <c:out value="${ commande.montant }"></c:out></p>
            <p>Mode de paiement  : <c:out value="${ commande.modePaiement }"></c:out></p>
            <p>Statut du paiement  : <c:out value="${ commande.statutPaiement }"></c:out></p>
            <p>Mode de livraison  : <c:out value="${ commande.modeLivraison }"></c:out></p>
            <p>Statut de la livraison  : <c:out value="${ commande.statutLivraison }"></c:out></p>
        </div>
    </body>
</html>
