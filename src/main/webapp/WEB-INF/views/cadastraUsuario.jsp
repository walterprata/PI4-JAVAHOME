<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <c:if test="${messageSucces != null}">
        <div class="alert alert-success" role="alert">
                ${messageSucces}
        </div>
    </c:if>
    <c:if test="${error != null}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>
    <c:if test="${sessionScope.get('cargo') != null && sessionScope.get('cargo') == 'admin' }">
        <button type="button" class="btn btn-dark btn-lg col-md" id="btn-lista-produto">Ver todos Usários</button>
    </c:if>
    <h1>Cadastrar novo Usuário</h1>
    <form method="POST" action="/javaHome/auth/cadastrar-usuario" enctype="multipart/form-data" class="form"
          id="form-salvar">
        <div class="form-group">
            <div class="form-row">
                <div class="form-group col-md">
                    <label for="nome">Nome completo</label>
                    <input type="text" class="form-control" id="nome" placeholder="Nome do usuário" name="nome"
                           minlength="5" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" minlength="5" maxlength="50"
                           required>
                </div>
            </div>
            <c:if test="${sessionScope.get('cargo') != null && sessionScope.get('cargo') == 'admin' }">
            <div class="form-row">
                <div class="form-group">
                    <label for="cargo">Cargo</label>
                    <select class="form-control" id="cargo" name="cargo">
                        <option value="admin">admin</option>
                        <option value="estoque">estoque</option>
                        <option value="Cliente">cliente</option>
                    </select>
                </div>
            </div>
            </c:if>
            <c:if test="${sessionScope.get('cargo') != null && sessionScope.get('cargo') == 'admin' }">
            <div class="form-row" id="campo-cliente" hidden>
                </c:if>
                <c:if test="${sessionScope.get('cargo') == null }">
                <div class="form-row" id="campo-cliente">
                    <input type="text" value="Cliente" name="cargo" hidden>
                    </c:if>
                    <div class="form-group col-md-6">
                        <label for="cpf">CPF</label>
                        <input type="text" class="form-control" id="cpf" name="cpf" minlength="14" maxlength="14">
                        <div class="invalid-feedback">
                            CPF informado é invalido!
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cep">Cep</label>
                        <input type="text" class="form-control" id="cep" name="cep" minlength="9" maxlength="9">
                        <div class="invalid-feedback">
                            Cep informado é invalido!
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="bairro">Bairro</label>
                        <input type="text" class="form-control" id="bairro" name="bairro">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="localidade">Cidade</label>
                        <input type="text" class="form-control" id="localidade" name="localidade">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="logradouro">Logradouro</label>
                        <input type="text" class="form-control" id="logradouro" name="logradouro">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="uf">UF</label>
                        <input type="text" class="form-control" id="uf" name="uf">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md">
                        <label for="senha">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" minlength="5" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md">
                        <label for="senhaConfirm">Confirmar Senha</label>
                        <input type="password" class="form-control" id="senhaConfirm" name="senhaConfirm" minlength="5">
                    </div>
                </div>
                <div class="form-group col-md">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="status" id="true" value="true" checked>
                        <label class="form-check-label" for="true">
                            Usuário ativo
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="status" id="false" value="false">
                        <label class="form-check-label" for="false">
                            Usuário não ativo
                        </label>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Salvar</button>
            </div>
    </form>
</div>

<jsp:include page="modalListaUsuarios.jsp"/>

<jsp:include page="footer.jsp"/>
<script src="<c:url value="/js/JavaHome.js"/>"></script>