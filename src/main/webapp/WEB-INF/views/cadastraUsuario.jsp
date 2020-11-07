<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="${'header.jsp'}"/>
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
        <C:if test="${empty cliente}">
            <c:set var="url" value="/javaHome/uth/cadastrar-usuario"/>
        </C:if>
        <c:if test="${not empty cliente}">
            <c:set var="url" value="/javaHome/auth/edita-usuario"/>
        </c:if>
        <%--@elvariable id="cliente" type="Usuario"--%>
        <form:form action="${url}" enctype="multipart/form-data" class="form" id="form-salvar" modelAttribute="cliente" >
            <c:if test="${not empty cliente}">
                <h1>Editar perfil</h1>
            </c:if>
            <c:if test="${empty cliente}">
                <h1>Novo usuário</h1>
            </c:if>

            <div class="form-group">
                <div class="form-row">
                    <div class="form-group col-md">
                        <label for="nome">Nome completo</label>
                        <input type="text" class="form-control" id="nome" placeholder="Nome do usuário" name="nome" minlength="5" required value="${nome}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md">
                        <label for="email">Email</label>
                        <c:if test="${not empty cliente}">
                            <input type="email" class="form-control" id="email" name="email" minlength="5" maxlength="50" required value="${email}" readonly>
                        </c:if>
                        <c:if test="${empty cliente}">
                            <input type="email" class="form-control" id="email" name="email" minlength="5" maxlength="50" required >
                        </c:if>

                    </div>
                    <div class="form-group col-md-6">
                        <label for="cpf">CPF</label>
                        <c:if test="${not empty cliente}">
                            <input type="text" class="form-control" id="cpf" name="cpf" minlength="14" maxlength="14" value="${cliente.cpf}" readonly>
                        </c:if>
                        <c:if test="${empty cliente}">
                            <input type="text" class="form-control" id="cpf" name="cpf" minlength="14" maxlength="14" >
                        </c:if>

                        <div class="invalid-feedback">
                            CPF informado é invalido!
                        </div>
                    </div>
                </div>
                <c:if test="${not empty sessionScope.get('cargo') && sessionScope.get('cargo') == 'admin' }">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="cargo">Cargo</label>
                            <select class="form-control" id="cargo" name="cargo" >
                                <option value="admin">admin</option>
                                <option value="estoque">estoque</option>
                                <option value="Cliente">cliente</option>
                            </select>
                        </div>
                    </div>
                </c:if>

                <div class="form-row" id="campo-cliente">
                    <c:if test="${not empty cliente}">
                        <input type="text" name="cargo" value="Cliente" readonly hidden>
                        <input type="text" name="id" value="${id}" readonly hidden>
                    </c:if>
                    <div class="form-group col-md-12">
                        <h3>Endereço</h3>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cep">Cep</label>
                        <input type="text" class="form-control" id="cep" name="cep" minlength="9" maxlength="9" >
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
                        <input type="text" class="form-control" id="localidade" name="cidade">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="logradouro">Logradouro</label>
                        <input type="text" class="form-control" id="logradouro" name="logradouro">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="uf">UF</label>
                        <input type="text" class="form-control" id="uf" name="uf">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="complemento">Complemento</label>
                        <input type="text" class="form-control" id="complemento" name="complemento" >
                    </div>
                    <div class="form-group col-md-12">
                        <button type="button" class="btn btn-primary" style="margin-top: 20px;margin-bottom: 20px;"
                                id="btn-add-endereco">Adicionar endereço
                        </button>
                        <div class="flex-row">
                            <h3 align="center" class="">Lista De endereços</h3>
                        </div>
                        <div class="table-responsive-md">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th scope="col">cep</th>
                                    <th scope="col">bairro</th>
                                    <th scope="col">Cidade</th>
                                    <th scope="col">Logradouro</th>
                                    <th scope="col">UF</th>
                                    <th scope="col">Complemento</th>
                                    <th scope="col">Ações</th>
                                </tr>
                                </thead>

                                <tbody id="lista-endereco">
                                <c:forEach var="enderecos" items="${cliente.endereco}">
                                    <tr id="${enderecos.id}">
                                        <th scope=\"row\"><input value='" + endereco + "' name='enderecos[]' hidden/> ${enderecos.cep}
                                        </th><td>${enderecos.bairro}
                                    </td><td>${enderecos.localidade}
                                    </td><td>${enderecos.logradouro}
                                    </td><td>${enderecos.uf}
                                    </td><td>${enderecos.complemento}
                                    </td><td><a type='button' class='text-warning'  onclick="removeEndereco(${enderecos.id})">Remover</a>
                                    </td></tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md">
                        <label for="senha">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" minlength="5">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md">
                        <label for="senhaConfirm">Confirmar Senha</label>
                        <input type="password" class="form-control" id="senhaConfirm" name="senhaConfirm" minlength="5"
                               readonly>
                    </div>
                </div>
                <c:if test="${empty sessionScope.get('cargo')}">
                    <% request.setAttribute("status", true); %>
                </c:if>
                <c:if test="${not empty sessionScope.get('cargo')}">
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
                </c:if>

                <button type="submit" class="btn btn-primary">Salvar</button>
                <c:if test="${not empty sessionScope.get('cargo') && sessionScope.get('cargo') == 'admin' }">
                    <button type="button" class="btn btn-dark" id="btn-lista-produto">Ver todos Usários</button>
                </c:if>
            </div>
        </form:form>
</div>
<jsp:include page="${'modalListaUsuarios.jsp'}"/>
<jsp:include page="${'footer.jsp'}"/>
<script src="<c:url value="/js/JavaHome.js"/>"></script>