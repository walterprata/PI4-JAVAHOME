<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="${'header.jsp'}"/>
<div class="container container-cupom border-custom">
    <h3>Informações do pedido</h3>
     <c:if test="${not empty message}">
        <div class="row">
            <div class="alert alert-success col-md" role="alert">
                    ${message}
            </div>
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="row">
            <div class="alert alert-danger col-md" role="alert">
                    ${error}
            </div>
        </div>
    </c:if>
    <div class="row ">
        <div class="form-group col-md border-custom">
            <label> <b>Numero do pedido:</b> ${pedido.id}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label> <b>Status:</b> ${pedido.statusCompra.status}</label>
            <c:if test="${not empty status}">
             	<c:if test="${usuarioLogado.usuario.cargo eq 'estoque' }">
             	 <form:form action="${s:mvcUrl('PC#atualizaStatus').build()}" method="post">
             	 <input type="hidden" name="pedidoId" value="${pedido.id}"/>
             		<div class="form-group">
                        <label for="status"><b>Alterar status</b></label>
                        <select class="form-control" id="status" name="status">
	                        <c:forEach var="status" items="${status}">
	                        	<option value="${status.id}">${status.status }</option>
	                        </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-warning mb-3">Editar</button>
             	</form:form>
        		</c:if>
            </c:if>
        </div>
        <div class="form-group col-md border-custom">
            <label><b>Valor do pedido:</b> R$ ${pedido.valorTotal}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label><b>Data da compra:</b> ${pedido.dataCompra}</label>
        </div>
    </div>
    <h5>Produtos</h5>
        <c:forEach var="item" items="${pedido.itensPedido}">
            <div class="row border-custom" style="padding-left: 20px">
                <p> <b>nome:</b> ${item.produto.nome} - <b>Quantidade:</b> ${item.quantidade} - <b>Valor unitário:</b>
                    R$ ${item.produto.valor} - <b>Valor Total:</b> R$ ${item.total}</p>
            </div>
        </c:forEach>
    <h5>Informações pagamento</h5>
    <c:if test="${pedido.tipoPagamento == 'BOLETO'}">
        <div class="form-group col-md border-custom">
            <label> <b>Tipo de pagamento:</b> ${pedido.tipoPagamento}</label>
        </div>
        <div class="form-group col-md border-custom">
            <p> <b>Sub-Total:</b> R$ ${pedido.valorTotal - pedido.freteValor}</p>
            <p> <b>Valor Total:</b> R$ ${pedido.valorTotal}</p>
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
            <label> <b>Nome:</b> ${pedido.freteNome}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label> <b>Valor:</b> R$ ${pedido.freteValor}</label>
        </div>
        <div class="form-group col-md border-custom">
            <label> <b>Prazo:</b> ${pedido.fretePrazo} Dias</label>
        </div>
        <div class="form-group col-md border-custom">
            <p><b>Endereço de entrega:</b></p>
            <p> ${pedido.endereco.logradouro} -
                ${pedido.endereco.localidade} -
                ${pedido.endereco.uf} -
                ${pedido.endereco.cep} -
                ${pedido.endereco.bairro} - N º
                ${pedido.endereco.complemento}</p>
            <p><b>Quem Recebe:</b> ${pedido.usuario.nome} </p>
        </div>
    </div>
    <h3>Informações do Comprador</h3>
    <div class="row">
        <div class="form-group col-md border-custom">
            <label> <b>Nome completo:</b> ${pedido.usuario.nome} </label>
        </div>
        <div class="form-group col-md border-custom">
            <label> <b>CPF:</b> ${pedido.usuario.cpf} </label>
        </div>
        <div class="form-group col-md border-custom">
            <label> <b>E-mail:</b> ${pedido.usuario.email} </label>
        </div>
    </div>
</div>
<jsp:include page="${'footer.jsp'}"/>