<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<div class="container">
        <p>Nome: ${ produto.nome } </p>
        <p>Descrição curta: ${ produto.descricao } </p>
        <p>Descrição: ${ produto.caracteristicas } </p>
        <p>Preço:R$ ${ produto.valor }</p>
        <p>Categoria: ${ produto.categoria }</p>
        <p>Quantidade: ${ produto.quantidade }</p>
        <img class=\"card-img-top\" src='/produto/imagens${produto.caminhoDaImagem}' alt=\"\" width="250" height="250">
</div>


<!-- Quickbeam cart end -->
<jsp:include page="footer.jsp"/>
<script src="<c:url value="/js/DetalhesProduto.js"/>"></script>