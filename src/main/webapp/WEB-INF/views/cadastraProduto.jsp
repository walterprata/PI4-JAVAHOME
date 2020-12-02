<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="${'header.jsp'}" />
<div class="container">
	<c:if test="${messageSucces != null}">
		<div class="alert alert-success" role="alert">${messageSucces}</div>
	</c:if>
	<c:if test="${error != null}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>
	<button type="button" class="btn btn-dark btn-lg col-md"
		id="btn-lista-produto">Ver todos produtos</button>
	<h1>Cadastrar novo produto</h1>
	<c:if test="${usuarioLogado.usuario.cargo != 'estoque'}">
		<h2>Adicionar perguntas e resposta ao novo produto</h2>
		<form:form action="${s:mvcUrl('PC#cadastrarPergunta').build()}" method="post" class="form" modelAttribute="duvidaProduto">
			<div class="form-group">
				<input type="text" class="form-control pergunta" placeholder="Faça sua pergunta" name="pergunta">
					 <input type="text" class="form-control resposta mt-3" placeholder="Resposta" name="resposta">
				<button type="submit" class="btn btn-primary mt-3"" >Adicionar pergunta</button>
			</div>
		</form:form>
		<div class="form-group">
			<ul class="list-group">
				<li class="list-group-item active">Duvidas</li>
				<c:if test="${not empty duvidas.duvidas }">
					<c:forEach var="duvida" items="${duvidas.duvidas}">
						<li class='list-group-item perguntas-class'>
							<p>
								<b>Pergunta:</b> ${duvida.pergunta }
							</p>
							<p>
								<b>Resposta:</b>${duvida.resposta}
							</p>
							<form:form action="${s:mvcUrl('PC#removerPergunta').arg(0,duvida).build()}" method="post" modelAttribute="duvidaProduto">
							<button type="submit" class='btn btn-danger float-right'>Remover</button>
							</form:form> 
							
						</li>
					</c:forEach>
				</c:if>
				<ul class="list-group" id="lista-pergunta"></ul>
			</ul>
		</div>
	</c:if>
	<form method="POST" action="/produto/salvar"
		enctype="multipart/form-data" class="form" id="form-salvar">
		<div class="form-group">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="nome">Nome do produto</label>
					<c:if test="${usuarioLogado.usuario.cargo == 'estoque'}">
						<input type="text" class="form-control" id="nome"
							placeholder="Nome do produto" name="nome" readonly>
					</c:if>
					<c:if test="${usuarioLogado.usuario.cargo != 'estoque'}">
						<input type="text" class="form-control" id="nome"
							placeholder="Nome do produto" name="nome" required>
					</c:if>
				</div>
				<div class="form-group col-md-6">
					<label for="descricao">Descrição curta</label>
					<c:if test="${usuarioLogado.usuario.cargo == 'estoque'}">
						<input type="text" class="form-control" id="descricao"
							name="descricao" maxlength="50" readonly>
					</c:if>
					<c:if test="${usuarioLogado.usuario.cargo != 'estoque'}">
						<input type="text" class="form-control" id="descricao"
							name="descricao" maxlength="50" required>
					</c:if>
				</div>
				<div class="form-group col-md-6">
					<label for="caracteristicas">Descrição</label>

					<c:if test="${usuarioLogado.usuario.cargo == 'estoque'}">
						<textarea class="form-control" id="caracteristicas" rows="3"
							name="caracteristicas" maxlength="200" readonly></textarea>
					</c:if>
					<c:if test="${usuarioLogado.usuario.cargo != 'estoque'}">
						<textarea class="form-control" id="caracteristicas" rows="3"
							name="caracteristicas" maxlength="200"></textarea>
					</c:if>
				</div>
				<div class="form-group col-md-6">
					<div class="form-group">
						<label for="categoria">Categoria</label>
						<c:if test="${usuarioLogado.usuario.cargo == 'estoque'}">
							<select class="form-control" id="categoria" name="categoria"
								readonly>
						</c:if>
						<c:if test="${usuarioLogado.usuario.cargo != 'estoque'}">
							<select class="form-control" id="categoria" name="categoria">
						</c:if>

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

					<c:if test="${usuarioLogado.usuario.cargo == 'estoque'}">
						<input type="text" class="form-control" id="palavraChave"
							placeholder="Ex: jogo, periferico..." name="" readonly>
					</c:if>
					<c:if test="${usuarioLogado.usuario.cargo != 'estoque'}">
						<input type="text" class="form-control" id="palavraChave"
							placeholder="Ex: jogo, periferico..." name="">
					</c:if>
				</div>
				<div class="form-group col-md">
					<label for="money">Preço: </label>
					<c:if test="${sessionScope.get('cargo') == 'estoque'}">
						<input type="text" class="form-control" id="money"
							placeholder="00.00" name="valor" readonly>
					</c:if>
					<c:if test="${sessionScope.get('cargo') != 'estoque'}">
						<input type="text" class="form-control" id="money"
							placeholder="00.00" name="valor">
					</c:if>

				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="quantidade">Quantidade: </label> <input type="number"
						class="form-control" id="quantidade" name="quantidade">
				</div>
			</div>

			<div class="form-group col-md">
				<div class="form-check">
					<input class="form-check-input" type="radio" name="ativo" id="true"
						value="true" checked> <label class="form-check-label"
						for="true"> Produto ativo </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="ativo"
						id="false" value="false"> <label class="form-check-label"
						for="false"> Produto não ativo </label>
				</div>

			</div>
			<div class="form-row">
				<div class="form-group col-md">
					<label>Imagens do produto</label>
					<c:if test="${sessionScope.get('cargo') == 'estoque'}">
						<input class="form-control-file" type="file" name="file[]"
							disabled />
						<br />
						<br />
						<input class="form-control-file" type="file" name="file[]"
							disabled />
						<br />
						<br />
						<input class="form-control-file" type="file" name="file[]"
							disabled />
						<br />
						<br />
					</c:if>
					<c:if test="${sessionScope.get('cargo') != 'estoque'}">
						<input class="form-control-file" type="file" name="file[]" />
						<br />
						<br />
						<input class="form-control-file" type="file" name="file[]" />
						<br />
						<br />
						<input class="form-control-file" type="file" name="file[]" />
						<br />
						<br />
					</c:if>
					<div class="form-group col-md" id="img1"></div>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar novo produto</button>
		</div>
	</form>
</div>
<jsp:include page="${'modalListaProdutos.jsp'}" />
<jsp:include page="${'footer.jsp'}" />
<script src="<c:url value="/js/JavaHome.js"/>"></script>