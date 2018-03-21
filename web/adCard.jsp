<%@ page import="fr.epsi.myEpsi.misc.ServletUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="card border-secondary mb-3" style="max-width: 20rem;">
    <div class="card-header">
        <h5 class="card-title">${param.title}</h5>
    </div>

    <div class="card-body text-secondary">
        <p class="card-text">${param.desc}</p>
        <p class="card-text">${param.price} €</p>
    </div>

    <div class="card-footer">
        <% if (!ServletUtil.retrieveValue(request, "detail").isEmpty()) { %>
        <a href="${pageContext.request.contextPath}/detail" class="btn btn-secondary">Détail</a>
        <% } %>

        <% if (!ServletUtil.retrieveValue(request, "canBuy").isEmpty()) { %>
        <a href="${pageContext.request.contextPath}/buy?id=${param.id}" class="btn btn-secondary">Acheter</a>
        <% } %>
    </div>
</div>