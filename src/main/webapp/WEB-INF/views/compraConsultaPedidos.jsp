<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<jsp:include page="${'header.jsp'}"/>
<div class="container">
	<div class="row"><h1>Meus Pedidos</h1></div>
	<div class="row">
		<ul class="list-group col-md">
		<c:forEach items="${pedidos}" var="pedido">
			<a href="${s:mvcUrl('PC#buscaPedidoDoCliente').arg(0,pedido.id).build()}" class="list-group-item list-group-item-action list-group-item-secondary">
			<b>numero:</b> ${pedido.id} | 
			<b>data:</b> ${pedido.dataCompra} | 
			<b>status:</b> ${pedido.statusCompra.status} | 
			<b>valor total:</b> R$ ${pedido.valorTotal }
			<c:if test="${usuarioLogado.usuario.cargo eq 'estoque' }">
			<b> | Você pode mudar o status desse pedido</b>
			</c:if>
			</a>
		</c:forEach>
	</ul>
	</div>
</div>
<jsp:include page="${'footer.jsp'}"/>