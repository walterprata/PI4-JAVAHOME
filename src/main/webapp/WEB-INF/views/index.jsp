<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<jsp:include page="header.jsp"/>
<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <h4 class="my-4">Categorias</h4>
            <div class="list-group">
                <a href="#" class="list-group-item">Consoles</a>
                <a href="#" class="list-group-item">Periféricos</a>
                <a href="#" class="list-group-item">Jogos</a>
            </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div id="carouselExampleIndicators" class="container-fluid carousel slide my-4" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>

                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img class="d-block img-fluid" src="/img/1.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="/img/2.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="/img/3.jpeg" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <div class="row" id="vitrine">
                <c:if test="${not empty produtos}">
                    <c:forEach var="produto" items="${produtos}">
                        <c:if test="${produto.ativo}">
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="/produto/detalhes/${produto.id}">
                                        <img class="card-img-top" src="/produto/imagens/${produto.caminhoDaImagem}" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="/produto/detalhes/${produto.id}">${produto.nome}</a>
                                        </h4>
                                        <h5>R$${produto.valor}</h5>
                                        <p class="card-text">${produto.descricao}</p>
                                    </div>
                                    <div class="card-footer">
                                        <a type="button" class="btn btn-dark" href="/javaHome/adcionar-produto">Adicionar ao carrinho</a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->
<jsp:include page="footer.jsp"/>
<script src="<c:url value="/js/index.js"/>"></script>