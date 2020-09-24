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
    <button type="button" class="btn btn-primary" id="btn-lista-produto">Ver todos produtos</button>
    <h1>Cadastrar novo produto</h1>
    <form method="POST" action="/produto/salvar" enctype="multipart/form-data" class="form" id="form-salvar">
        <div class="form-group">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="nome">Nome do produto</label>
                    <input type="text" class="form-control" id="nome" placeholder="Nome do produto" name="nome" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="descricao">Descrição curta</label>
                    <input type="text" class="form-control" id="descricao" name="descricao" maxlength="50" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="caracteristicas">Descrição</label>
                    <textarea class="form-control" id="caracteristicas" rows="3" name="caracteristicas"></textarea>
                </div>
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="categoria">Categoria</label>
                        <select class="form-control" id="categoria" name="categoria">
                            <option value="Console">Console</option>
                            <option value="Jogos">Jogos</option>
                            <option value="Periféricos">Periféricos</option>
                            <option value="Acessórios">Acessórios</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md">
                    <label for="palavraChave">Palavras-Chaves</label>
                    <input type="text" class="form-control" id="palavraChave" placeholder="Ex: jogo, periferico..." name="">
                </div>
                <div class="form-group col-md">
                    <label for="money">Preço: </label>
                    <input type="text" class="form-control" id="money" placeholder="00.00" name="valor">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="quantidade">Quantidade: </label>
                    <input type="number" class="form-control" id="quantidade" name="quantidade">
                </div>
                <div class="form-group col-md-4">
                    <h5>Adicionar perguntas e resposta</h5>
                </div>
            </div>

            <div class="form-group col-md">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="ativo" id="true" value="true" checked>
                    <label class="form-check-label" for="true">
                        Produto ativo
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="ativo" id="false" value="false">
                    <label class="form-check-label" for="false">
                        Produto não ativo
                    </label>
                </div>

            </div>
            <div class="form-group col-md">
                <label>Imagens do produto</label>
                <input class="form-control-file" type="file" name="file[]" id="img1"/><br/><br/>
                <input class="form-control-file" type="file" name="file[]" id="img2"/><br/><br/>
                <input class="form-control-file" type="file" name="file[]" id="img3"/><br/><br/>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </div>
    </form>
</div>

<jsp:include page="modalListaProdutos.jsp"/>

<jsp:include page="footer.jsp"/>
<script src="<c:url value="/js/produto.js"/>"></script>