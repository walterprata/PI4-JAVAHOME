<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="${'header.jsp'}"/>
<div class="container">
    <div class="row">
        <h1>Meu Carrinho</h1>
        <table class="table table-striped col-md-12">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Produto</th>
                <th scope="col">Quantidade</th>
                <th scope="col">Preço uni</th>
                <th scope="col">Preço total</th>
            </tr>
            </thead>

            <tbody id="lista-endereco">
            <c:forEach var="item" items="${carrinho.itens}">
                <tr>
                    <td class="img-thumbnail"><img class="img-fluid"
                                                   src="/produto/imagens/${item.produto.caminhoDaImagem}" alt=""
                                                   width="70">
                    </td>
                    <td><a href="${s:mvcUrl('PC#detalhes').arg(0,item.produto.id).build()}">
                        <h5>${item.produto.nome}</h5></a></td>
                    <td>
                        <div class="row">
                            <form:form action="${s:mvcUrl('CC#add').arg(0,item.produto.id).build()}" method="post"
                                       cssClass="carrinho-form-add">
                                <button type="submit" class="carrinho-btn-qtd" id="carrinho-btn-add">+</button>
                            </form:form>

                            <input id="carrinho-qtd" type="text" onKeyDown="return false"
                                   value="${carrinho.getQuantidade(item)}" readonly/>

                            <form:form action="${s:mvcUrl('CC#removeQuatidade').arg(0,item.produto.id).build()}"
                                       method="post" cssClass="carrinho-form-add">
                                <button type="submit" class="carrinho-btn-qtd" id="carrinho-btn-rmv">─</button>
                            </form:form>
                        </div>
                        <div class="row">
                            <a href="${s:mvcUrl('CC#remove').arg(0,item.produto.id).build()}"
                               style="color: red">excluir</a>
                        </div>

                    </td>
                    <td>R$ ${item.preco}</td>
                    <td>R$ ${carrinho.getTotal(item)}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="container-cupom row">
        <form:form action="${ s:mvcUrl('CC#buscaValores').build() }" method="post"
                   cssClass="form-inline carrinho-form-cupom col-md-12">
            <label>Calcular valor do frete</label>
            <c:if test="${not empty usuarioLogado.getCep()}">
                <input class="form-control mr-sm-2" type="search" placeholder="Digite seu CEP"
                       aria-label="Search" value="${usuarioLogado.getCep()}">
            </c:if>
            <c:if test="${empty usuarioLogado.getCep()}">
                <input class="form-control mr-sm-2" type="search" placeholder="Digite seu CEP"
                       aria-label="Search">
            </c:if>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Aplicar</button>
        </form:form>
        <c:if test="${carrinho.isMostrarCep()}">
            <form:form action="${s:mvcUrl('CC#addCep').build()}" method="post">
                <div class="form-group col-md-12 carrinho-container-frete">
                    <c:forEach items="${carrinho.destino.getCeps()}" var="item">
                        <div class="form-row">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="cepValor" value="${item.value}">${item.key} - R$ ${item.value}
                            </label>
                        </div>
                    </c:forEach>
                    <button class="btn btn-success " type="submit">Aplicar Cep</button>
                </div>
            </form:form>
        </c:if>

    </div>

    <div class="container-cupom row">
        <div class="d-flex flex-row-reverse col-md-12 carrinho-footer-info">
            <div>
                <ul class="list-group">
                    <li class="list-group-item">R$ ${carrinho.subTotal}</li>
                    <li class="list-group-item">R$ ${carrinho.destino.valor}</li>
                    <li class="list-group-item">R$ ${carrinho.total}</li>
                </ul>
            </div>
            <div>
                <ul class="list-group">
                    <li class="list-group-item">SUBTOTAL</li>
                    <li class="list-group-item">ENTREGA</li>
                    <li class="list-group-item">VALOR TOTAL</li>
                </ul>
            </div>
        </div>
        <div class="col-md-12 carrinho-footer">
            <a href="${s:mvcUrl('HC#index').build()}" class="btn btn-primary">Continuar Comprando</a>
            <button type="button" class="btn btn-success float-right" style="margin-right: 20px ">FINALIZAR COMPRA
            </button>
        </div>
    </div>
</div>
</div>
<jsp:include page="${'footer.jsp'}"/>
