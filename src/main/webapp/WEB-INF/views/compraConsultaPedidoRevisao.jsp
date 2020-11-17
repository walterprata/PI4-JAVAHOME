<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="${'header.jsp'}"/>
<div class="container container-cupom border-custom">
    <h3>Informações do pedido</h3>
    <div class="row ">
        <div class="form-group col-md border-custom">
            <label> Numero do pedido: ${pedido.id}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label>Valor do pedido: R$ ${pedido.valorTotal}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label>Data da compra ${pedido.dataCompra}</label>
        </div>
    </div>
    <h5>Produtos</h5>
        <c:forEach var="item" items="${pedido.itensPedido}">
            <div class="row border-custom" style="padding-left: 20px">
                <p> nome: ${item.produto.nome} - Quantidade: ${item.quantidade} - Valor unitário:
                    R$ ${item.produto.valor} - Valor Total: R$ ${item.total}</p>
            </div>
        </c:forEach>
    <h5>Informações pagamento</h5>
    <c:if test="${pedido.tipoPagamento == 'BOLETO'}">
        <div class="form-group col-md border-custom">
            <label> Tipo de pagamento: ${pedido.tipoPagamento}</label>
        </div>
        <div class="form-group col-md border-custom">
            <p> Sub-Total: R$ ${pedido.valorTotal - pedido.freteValor}</p>
            <p> Valor Total: R$ ${pedido.valorTotal}</p>
        </div>
        <a href="${s:mvcUrl('PC#imprimirBoleto').build()}" class="btn btn-warning border-custom" target="_blank">Segunda Via do Boleto</a>
    </c:if>

    <c:if test="${pedido.tipoPagamento == 'CARTAO_DE_CREDITO'}">
        <div class="row">
            <div class="form-group col-md border-custom">
                <label> Tipo de pagamento: ${pedido.tipoPagamento}</label>
            </div>
            <div class="form-group col-md border-custom">
                <p> Sub-Total: R$ ${pedido.valorTotal - pedido.freteValor}</p>
                <p> Valor Total: R$ ${pedido.valorTotal}</p>
            </div>
        </div>
    </c:if>

    <h5>Informações De Envio</h5>
    <div class="row">
        <div class="form-group col-md border-custom">
            <label> Nome: ${pedido.freteNome}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label> Valor: R$ ${pedido.freteValor}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label> Prazo: ${pedido.fretePrazo} Dias</label>
        </div>
        <div class="form-group col-md border-custom">
            <p>Endereço de entrega: </p>
            <p> ${pedido.endereco.logradouro} -
                ${pedido.endereco.localidade} -
                ${pedido.endereco.uf} -
                ${pedido.endereco.cep} -
                ${pedido.endereco.bairro} - N º
                ${pedido.endereco.complemento}</p>
            <p>Quem Recebe: ${pedido.usuario.nome} </p>
        </div>
    </div>
    <h3>Informações do Comprador</h3>
    <div class="row">
        <div class="form-group col-md border-custom">
            <label> Nome completo: ${pedido.usuario.nome} </label>
        </div>
        <div class="form-group col-md border-custom">
            <label> CPF: ${pedido.usuario.cpf} </label>
        </div>
        <div class="form-group col-md border-custom">
            <label> E-mail: ${pedido.usuario.email} </label>
        </div>
    </div>
</div>
<jsp:include page="${'footer.jsp'}"/>