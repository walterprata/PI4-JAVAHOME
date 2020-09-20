<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<jsp:include page="header.jsp"/>
<form method="POST" action="/produto/salvar" enctype="multipart/form-data" class="container">
    <div class="form-group">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="nome">Nome do produto</label>
                <input type="text" class="form-control" id="nome" placeholder="Nome do produto" name="nome">
            </div>
            <div class="form-group col-md-6">
                <label for="descricao">Descrição curta</label>
                <input type="text" class="form-control" id="descricao" name="descricao" maxlength="10">
            </div>
            <div class="form-group col-md-6">
                <label for="caracteristicas">Descrição</label>
                <textarea class="form-control" id="caracteristicas" rows="3" name="caracteristicas"></textarea>
            </div>
            <div class="form-group col-md-6">
                <label for="categoria">Categoria</label>
                <input class="form-control" id="categoria"name="categoria">
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
                <h1>Adicionar perguntas e resposta</h1>
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
            <input class="form-control-file" type="file" name="file[]"/><br/><br/>
            <input class="form-control-file" type="file" name="file[]"/><br/><br/>
            <input class="form-control-file" type="file" name="file[]"/><br/><br/>
        </div>
        <button type="submit" class="btn btn-primary">Salvar</button>
    </div>
</form>

<jsp:include page="footer.jsp"/>