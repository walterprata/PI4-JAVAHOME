<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="${'header.jsp'}"/>
<div class="container">
    <c:if test="${not empty produto}">
        <h1 align="center" style="margin: 30px;">${ produto.nome }</h1>
        <div class="row" style="margin: 100px;">
            <div class="col-md">
                <p style="color:dodgerblue;">Categoria: ${ produto.categoria }</p>
                <div class="row">
                    <div class="col-md-4">
                        <ul class="list-group">
                            <c:if test="${not empty produto.caminhoImagensLista}">
                                <c:forEach var="img" items="${produto.caminhoImagensLista}">
                                    <li class="list-group-item"><img class="img-fluid" src='/produto/imagens${img}'
                                                                     alt=""></li>
                                </c:forEach>
                            </c:if>

                        </ul>
                    </div>
                    <div class="col-md-8 list-group-item">
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">

                                <c:if test="${not empty produto.caminhoImagensLista}">
                                    <% int count = 0; %>
                                    <c:forEach var="img" items="${produto.caminhoImagensLista}">
                                        <%if (count == 0) {%>
                                        <div class="carousel-item active">
                                            <img class="img-fluid " src='/produto/imagens${img}'
                                                 alt="">
                                        </div>
                                        <%
                                            count++;
                                        } else {
                                        %>
                                        <div class="carousel-item">
                                            <img class="img-fluid " src='/produto/imagens${img}'
                                                 alt="">
                                        </div>
                                        <%}%>

                                    </c:forEach>

                                </c:if>
                            </div>
                            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                               data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                               data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-md">
                <h2> ${ produto.descricao } </h2>
                <p>
                <h1 style="color: green">à Vista R$${ produto.valor }</h1></p>
                <button type="button" class="btn btn-success btn-lg">Comprar</button>
                <p>Quantidade Disponivel: ${ produto.quantidade }</p>

                <h2>Detalhes</h2>
                <p>${ produto.caracteristicas } </p>
            </div>
        </div>
        <div class="row">
            <div class="col-md">
                <h2 class="list-group-item" style="background-color: #343a40;color: white">Duvidas</h2>
                <ul class="list-group">
                    <c:if test="${not empty perguntas}">
                        <c:forEach var="pergunta" items="${perguntas}">
                            <li class="list-group-item">
                                <p style="margin: 0;"><b>Pergunta:</b> ${pergunta.pergunta}</p>
                                <p style="margin: 0;"><b>Resposta:</b> ${pergunta.resposta}</p>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
    </c:if>
</div>

<footer class="container" style="margin-top: 50px">
    <div class="row">
        <nav class="col-md" style="margin-right: 6%;">
            <h5>Formas de Pagamento</h5>
            <img width="100"
                 src="https://www.pichau.com.br/static/version1601576688/frontend/Pichau/base/pt_BR/images/footer/formas-pagamento/boleto.png">
            <img src="https://www.pichau.com.br/static/version1601576688/frontend/Pichau/base/pt_BR/images/footer/formas-pagamento/visa.png">
            <img src="https://www.pichau.com.br/static/version1601576688/frontend/Pichau/base/pt_BR/images/footer/formas-pagamento/master.png">
            <img src="https://www.pichau.com.br/static/version1601576688/frontend/Pichau/base/pt_BR/images/footer/formas-pagamento/hipercard.png">
            <img src="https://www.pichau.com.br/static/version1601576688/frontend/Pichau/base/pt_BR/images/footer/formas-pagamento/paypal.jpg">
        </nav>
        <nav class="nav-security" style="margin-right: 0;">
            <h5>Certificados de Segurança</h5>
            <img src="https://www.siteblindado.com/images/ssl.png">
            <img src="https://www.pichau.com.br/static/version1601576688/frontend/Pichau/base/pt_BR/images/footer/certificados/pichau-google.png"
                 alt="">
            <img src="https://newimgebit-a.akamaihd.net/ebitBR/selo/img_6261.png" style="border: 0px;">
        </nav>
    </div>
</footer>
<!-- Bootstrap core JavaScript -->
<script src="<c:url value="/webjars/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/4.5.2/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/webjars/jquery-mask-plugin/1.14.15/src/jquery.mask.js"/>"></script>

</body>
</html>