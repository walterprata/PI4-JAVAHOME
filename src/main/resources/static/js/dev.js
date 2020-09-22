//Sempre ofuscar antes de subir
//https://obfuscator.io/

//Apos o carregamento da pagina
$(document).ready(function () {
    $('#money').mask("##0.00", {reverse: true});
});

//Ocultar alert
$('.alert').click(function () {
    $(".alert").fadeOut();
});

//ao clicar no botão, ele cria a tabelas com campos
$('#btn-lista-produto').click(listarProdutos());

function listarProdutos() {
    $('#tabela tr').remove();//limpa a tabela
    $.get('/produto/listar',  // url
        function (data, textStatus, jqXHR) {  // success callback
            if (textStatus === "success") {
                data.content.forEach(criaTabela);

                function criaTabela(produtos) {
                    $('#tabela').append(createTabela(produtos) + estaAtivo(produtos))
                }
            }
        }
    );
}

function createTabela(produtos) {
    return "<tr><th scope=\"row\">"
        + produtos.id +
        "</th><td>"
        + produtos.nome +
        "</td><td>"
        + produtos.descricao +
        "</td><td>"
        + produtos.preco +
        "</td><td>" +
        produtos.categoria +
        "</td><td>" +
        produtos.quantidade +
        "</td><td>" +
        "<a type=\"button\" class=\"btn btn-primary\" style='margin-right: 10px;'>Editar</a>" +
        "<a type=\"button\" class=\"btn btn-primary\" style='margin-right: 10px;' onclick='buscarDetalhesDoProduto(" + produtos.id + ")' >Detahes</a>"
}

function estaAtivo(produto) {
    if (produto.ativo > 0) {
        return "<a type='button' class='btn btn-danger'  onclick='mudarStatusProduto(" + produto.id + ",\"desativa\")'>Desativar</a></td></tr>"
    } else {
        return "<a type='button' class='btn btn-success' onclick='mudarStatusProduto(" + produto.id + ",\"ativa\")\'>Ativar</a></td></tr>"
    }
}

function mudarStatusProduto(id, ativa) {
    let produtoEncontrado = buscarDetalhesDoProduto(id);
    if (!!produtoEncontrado) {
        if (ativa === "ativa") {
            produtoEncontrado.ativo = 1
        } else if (ativa === "desativa") {
            produtoEncontrado.ativo = 0
        } else {
            alert("Não foi possivel definir o status");
            return
        }

        $.ajax({
            url: '/produto/' + id,
            type: 'PUT',
            success: callback,
            data: JSON.stringify(produtoEncontrado),
            contentType: 'application/json'
        });

        function callback() {
            alert("Produto Editado com Sucesso.");
            listarProdutos();
        }
    }else{
        alert("Não foi possivel editar produto")
    }
}

function buscarDetalhesDoProduto(id){
    let resultado = null;
     $.ajax({
        url: '/produto/' + id,
        async:false,
        success:function (data) {
            resultado = data
        }
    })
    return resultado
}
