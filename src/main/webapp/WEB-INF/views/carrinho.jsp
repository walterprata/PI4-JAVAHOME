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
                    <td class="img-thumbnail">
                        <img class="img-fluid" src="/produto/imagens/${item.produto.caminhoDaImagem}" alt="" width="70">
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
        <c:if test="${carrinho.quantidade > 0}">
            <form:form action="${ s:mvcUrl('CC#buscaCep').build() }" method="get"
                       cssClass="form-inline carrinho-form-cupom col-md-12">
                <label>Calcular valor do frete</label>
                <c:if test="${not empty usuarioLogado.getCep()}">
                    <input class="form-control mr-sm-2" type="search" placeholder="Digite seu CEP"
                           aria-label="Search" value="${usuarioLogado.getCep()}" name="cep" required>
                </c:if>
                <c:if test="${empty usuarioLogado.getCep()}">
                    <input class="form-control mr-sm-2" type="search" placeholder="Digite seu CEP"
                           aria-label="Search" name="cep" required>
                </c:if>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="carrinho-btn-add-frete">Aplicar</button>
            </form:form>
            <c:if test="${not empty carrinho.fretes}">
                <div class="form-group carrinho-container-frete col-md-12">
                    <c:if test="${not empty carrinho.freteSelecionado.freteNome}">
                        <form:form action="${s:mvcUrl('CC#addRemove').build()}" method="get">
                            <input type="text" hidden name="${carrinho.freteSelecionado.freteId}">
                            <div class="alert alert-success col-md-6" role="alert">
                                Frete Selecionado: ${carrinho.freteSelecionado.freteNome} -
                                R$ ${carrinho.freteSelecionado.freteValor} - ${carrinho.freteSelecionado.fretePrazo}
                            </div>
                            <button class="btn btn-danger" type="submit">Remover Frete</button>
                        </form:form>

                    </c:if>
                    <c:if test="${empty carrinho.freteSelecionado.freteNome}">
                        <form:form action="${s:mvcUrl('CC#addCep').build()}" method="get">
                            <select class="form-control col-md-3" id="fretes" name="freteid">
                                <c:forEach items="${carrinho.fretes}" var="item">
                                    <option value="${item.freteId}">${item.freteNome} - R$ ${item.freteValor}
                                        - ${item.fretePrazo} (dias)</option>
                                </c:forEach>
                            </select>
                            <button class="btn btn-success " type="submit" >Aplicar Frete</button>
                        </form:form>
                    </c:if>
                </div>
            </c:if>
        </c:if>
    </div>

    <div class="container-cupom row">
        <div class="d-flex flex-row-reverse col-md-12 carrinho-footer-info">
            <div>
                <ul class="list-group">
                    <li class="list-group-item">R$ ${carrinho.subTotal}</li>
                    <li class="list-group-item">R$ ${carrinho.freteSelecionado.freteValor}</li>
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
                <a href="${s:mvcUrl('PC#finalizar').build()}" class="btn btn-success float-right" style="margin-right: 20px ">FINALIZAR COMPRA
            </a>
        </div>
    </div>
</div>
</div>
<jsp:include page="${'footer.jsp'}"/>
