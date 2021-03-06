<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<jsp:include page="${'header.jsp'}"/>
<div class="container">
    <div class="row">
        Status pedido
        <h1>Cliente:${usuarioLogado.usuario.nome}</h1>
    </div>
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>
    <div class="row container-cupom">
        <div class="col-md-6 pedido-box">
            <h3>Endereços já vinculados</h3>
            <c:forEach var="endereco" items="${usuarioLogado.usuario.endereco}">
                <form:form action="${s:mvcUrl('PC#addEndereco').arg(0,endereco.id).build()}" method="post">
                    <button class="compra-informacoes-btn-indereco" type="submit">
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">CEP: ${endereco.cep}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${endereco.localidade}</h6>
                                <p class="card-text">${endereco.bairro},${endereco.logradouro} - N ${endereco.complemento}</p>
                            </div>
                        </div>
                    </button>
                </form:form>
            </c:forEach>
            <c:if test="${not empty message}">
                <div class="alert alert-success col-md-6" role="alert">
                        ${message}
                </div>
            </c:if>
            <h3>Envia para um novo endereço</h3>
            <a href="${s:mvcUrl('UC#editarInfoDoCliente').build()}">Adicionar um novo endereço</a>
        </div>
        <div class="col-md-6 pedido-box">
            <h3>Forma de pagamento</h3>
            <div class="form-group col-md">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="formaDePagamento" id="cartao" value="cartao"
                           checked>
                    <label class="form-check-label" for="cartao">
                        Cartão
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="formaDePagamento" id="boleto" value="boleto">
                    <label class="form-check-label" for="boleto">
                        Boleto
                    </label>
                </div>
            </div>
            <form:form action="${s:mvcUrl('PC#cartao').build()}" cssClass="form-group compra-informacoes-form-cartao" method="post">
                <div class="form-group">
                    <div class="form-row">
                        <div class="form-group col-md">
                            <label for="nomeTitular">Nome Impresso no cartão</label>
                            <input type="text" class="form-control" id="nomeTitular" placeholder="Nome do Titular" name="nomeTitular" required >
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="numeroCartao">Digitos</label>
                            <input type="text" class="form-control" id="numeroCartao" name="numeroCartao" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="cpfTitular">CPF do titular</label>
                            <input type="text" class="form-control" id="cpfTitular" name="cpfTitular" minlength="14"
                                   maxlength="14" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="csvCartao">CSV</label>
                            <input type="text" class="form-control" id="csvCartao" name="csvCartao" minlength="3" maxlength="3" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="validadeCartao">validade</label>
                            <input type="text" class="form-control" id="validadeCartao" name="validadeCartao" minlength="7" maxlength="7" placeholder="dd/aaaa" required>
                        </div>

                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Finalizar Pagamento</button>
            </form:form>
            <form:form action="${s:mvcUrl('PC#boleto').build()}" cssClass="form-group compra-informacoes-form-boleto" method="get">
                <button type="submit" class="btn btn-primary">Gerar Boleto</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="${'footer.jsp'}"/>
<script src="/js/ComprasInformacoes.js"></script>
