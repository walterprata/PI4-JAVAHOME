<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="${'header.jsp'}"/>
<div class="container">
    <div class="row">
        <h1>Meu Carrinho</h1>
        <table class="table table-striped col-md-12">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Produto</th>
                <th scope="col">Valor da Entrega</th>
                <th scope="col">Quantidade</th>
                <th scope="col">Preço uni</th>
                <th scope="col">Preço total</th>
            </tr>
            </thead>

            <tbody id="lista-endereco">
            <tr>
                <td class="img-thumbnail"><img class="img-fluid" src="/produto/imagens/31tlol_.jpg" alt="" width="70">
                </td>
                <td><a href="#"><h5>The las of us 2</h5></a></td>
                <td>R$ 36,00</td>
                <td>
                    <button class="carrinho-btn-qtd" id="carrinho-btn-add">+</button>
                    <input id="carrinho-qtd" type="text" onKeyDown="return false" value="1" readonly/>
                    <button class="carrinho-btn-qtd" id="carrinho-btn-rmv">─</button>
                </td>
                <td>R$ <input class="carrinho-valor-uni" id="carrinho-preco-uni" type="text" value="250,21"
                              onKeyDown="return false" readonly disabled></td>
                <td>R$ <input class="carrinho-valor-uni" id="carrinho-preco-total" type="text" value="0"
                              onKeyDown="return false" readonly disabled></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="container-cupom row">
        <form class="form-inline carrinho-form-cupom" action="#">
            <label>Cupom de desconto</label>
            <input class="form-control mr-sm-2" type="search" placeholder="Digite seu Cumpom..."
                   aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Aplicar</button>
        </form>
    </div>

    <div class="container-cupom row">
        <div class="d-flex flex-row-reverse col-md-12 carrinho-footer-info">
            <div>
                <ul class="list-group">
                    <li class="list-group-item">R$ 1200,00</li>
                    <li class="list-group-item">R$ 1200,00</li>
                    <li class="list-group-item">R$ 1200,00</li>
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
            <button type="button" class="btn btn-primary">Continuar Comprando</button>
            <button type="button" class="btn btn-success float-right" style="margin-right: 20px ">FINALIZAR COMPRA</button>
        </div>
    </div>
</div>
</div>
<jsp:include page="${'footer.jsp'}"/>
