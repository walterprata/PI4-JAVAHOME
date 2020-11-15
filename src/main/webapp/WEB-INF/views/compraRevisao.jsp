<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="${'header.jsp'}"/>
<div class="container container-cupom border-custom">
    <h3>Informações do pedido</h3>
    <div class="row ">
        <div class="form-group col-md border-custom">
            <label> Numero do pedido: 121212</label>
        </div>
        <div class="form-group col-md border-custom">
            <label>Valor do pedido: R$ ${carrinho.novoPedido.valorTotal}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label>Data da compra ${carrinho.novoPedido.dataCompra}</label>
        </div>
    </div>
    <h5>Produtos</h5>

        <c:forEach var="item" items="${carrinho.novoPedido.itensPedido}">
            <div class="row border-custom" style="padding-left: 20px">
                <p> nome: ${item.produto.nome} - Quantidade: ${item.quantidade} - Valor unitário:
                    R$ ${item.produto.valor} - Valor Total: R$ ${item.total}</p>
            </div>
        </c:forEach>
    <h5>Informações pagamento</h5>
    <c:if test="${carrinho.novoPedido.tipoPagamento == 'BOLETO'}">
        <div class="form-group col-md border-custom">
            <label> Tipo de pagamento: ${carrinho.novoPedido.tipoPagamento}</label>
        </div>
        <div class="form-group col-md border-custom">
            <p> Sub-Total: R$ ${carrinho.getSubTotalPedido()}</p>
            <p> Valor Total: R$ ${carrinho.novoPedido.valorTotal}</p>
        </div>
        <a href="${s:mvcUrl('PC#imprimirBoleto').build()}" class="btn btn-warning border-custom" target="_blank">Gerar Boleto</a>
    </c:if>

    <c:if test="${carrinho.novoPedido.tipoPagamento == 'CARTAO_DE_CREDITO'}">
        <div class="row">
            <div class="form-group col-md border-custom">
                <label> Tipo de pagamento: ${carrinho.novoPedido.tipoPagamento}</label>
            </div>
            <div class="form-group col-md border-custom">
                <label> Numero de parcelas:${carrinho.pagamento.cartao.indexParcela}x
                    R$ ${carrinho.getValorParcelas()}</label>
            </div>
            <div class="form-group col-md border-custom">
                <p> Sub-Total: R$ ${carrinho.getSubTotalPedido()}</p>
                <p> Valor Total: R$ ${carrinho.novoPedido.valorTotal}</p>
            </div>
            <div class="form-group col-md border-custom">
                <p> Cartão Final: ${carrinho.pagamento.cartao.numeroCartao}</p>
                <p> nome do Titular: ${carrinho.pagamento.cartao.nomeTitular}</p>
                <p> CPF do Titular: ${carrinho.pagamento.cartao.cpfTitular}</p>
            </div>
        </div>
    </c:if>

    <h5>Informações De Envio</h5>
    <div class="row">
        <div class="form-group col-md border-custom">
            <label> Nome: ${carrinho.novoPedido.freteNome}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label> Valor: R$ ${carrinho.novoPedido.freteValor}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label> Prazo: ${carrinho.novoPedido.fretePrazo} Dias</label>
        </div>
        <div class="form-group col-md border-custom">
            <p>Endereço de entrega: </p>
            <p> ${carrinho.novoPedido.endereco.logradouro} -
                ${carrinho.novoPedido.endereco.localidade} -
                ${carrinho.novoPedido.endereco.uf} -
                ${carrinho.novoPedido.endereco.cep} -
                ${carrinho.novoPedido.endereco.bairro} - N º
                ${carrinho.novoPedido.endereco.complemento}</p>
            <p>Quem Recebe: ${carrinho.novoPedido.usuario.nome} </p>
        </div>
    </div>
    <h3>Informações do Comprador</h3>
    <div class="row">
        <div class="form-group col-md border-custom">
            <label> Nome completo: ${carrinho.novoPedido.usuario.nome} </label>
        </div>
        <div class="form-group col-md border-custom">
            <label> CPF: ${carrinho.novoPedido.usuario.cpf} </label>
        </div>
        <div class="form-group col-md border-custom">
            <label> E-mail: ${carrinho.novoPedido.usuario.email} </label>
        </div>
    </div>
</div>
<div class="container" style="margin-bottom: 30px">
    <div class="row">
        <a href="${s:mvcUrl('PC#finalizaCompra').build()}" class="btn btn-success right">Finalizar Compra</a>
        <a href="${s:mvcUrl('PC#finalizar').build()}" class="btn btn-primary right" style="margin-left: 5px">Mudar forma de pagamento</a>
    </div>
</div>
<jsp:include page="${'footer.jsp'}"/>