//Sempre ofuscar antes de subir
//https://obfuscator.io/
var idPergunta = 0;
//Apos o carregamento da pagina
$(document).ready(function () {
    $('#money').mask("##0.00", {reverse: true});
});

//Ocultar alert
$('.alert').click(function () {
    $(".alert").fadeOut();
});
//ao clicar no botão, ele cria a tabelas com campos
$('#btn-lista-produto').click(function () {
    listarProdutos();
    listarUsuarios();
    resetBtnStatusUsuarios();
    $('#btn-listar-todos').addClass("active")
    showModal()
});
//ocultar modal

$('#btn-lista-produto').click(function () {
    $('.modal').modal('show')
    $("#body").removeClass("modal-open");
});

$('.btn-editar').click(function () {
    $('.modal').modal('hide');
    $("#body").removeClass("modal-open");

});

//Adicionar pergunta
$('#add-pergunta').click(function () {
    let pergunta = $('.pergunta').val();
    let resposta = $('.resposta').val();
    addPerguntasNoHtmnl(pergunta, resposta);
});
$('#btn-listar-ativos').click(function () {
    resetBtnStatusUsuarios();
    $('#btn-listar-ativos').addClass("active")
});
$('#btn-listar-desativados').click(function () {
    resetBtnStatusUsuarios();
    $('#btn-listar-desativados').addClass("active")
});
$('#btn-listar-todos').click(function () {
    resetBtnStatusUsuarios();
    $('#btn-listar-todos').addClass("active")
});

function resetBtnStatusUsuarios() {
    $('#btn-listar-ativos').removeClass("active");
    $('#btn-listar-desativados').removeClass("active");
    $('#btn-listar-todos').removeClass("active");
}

function addPerguntasNoHtmnl(pergunta, resposta) {
    if (pergunta !== "" || resposta !== "") {
        $('#lista-pergunta').append("<li class='list-group-item perguntas-class' id='pergunta" + idPergunta + "'>" +
            "<input type='hidden' name='perguntas[]' value='" + pergunta + "'>" +
            "<input type='hidden' name='respostas[]' value='" + resposta + "'>" +
            "<p><b>Pergunta:</b> " + pergunta + "</p><p><b>Resposta:</b> " + resposta + "</p>" +
            " <a type='button' class='btn btn-danger float-right' onclick='removerPergunta(" + idPergunta + ")'>Remover</a></li>")
    }
    idPergunta++;
}

//remove pergunta
function removerPergunta(idDaPergunta) {
    console.log('pergunta' + idDaPergunta);
    let campoDaPergunta = "pergunta" + idDaPergunta;
    $('#' + campoDaPergunta).remove();
}

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

function listarUsuarios() {
    $('#tabela-user tr').remove();//limpa a tabela
    $.get('/javaHome/auth/listar-todos-usuarios',  // url
        function (data, textStatus, jqXHR) {  // success callback
            if (textStatus === "success") {
                data.forEach(criaTabelaUser);

                function criaTabelaUser(usuarios) {
                    console.log(usuarios);
                    $('#tabela-user').append(createTabelaUsuario(usuarios) + estaAtivoUser(usuarios))
                }
            }
        }
    );
}

function listarUsuariosComBaseNoStatus(status) {
    if(status === 1 || status === 0){
        let defineStatus = false;
        if(status === 1){
            defineStatus = true
        }

        $('#tabela-user tr').remove();//limpa a tabela
        $.get('/javaHome/auth/listar-todos-usuarios',  // url
            function (data, textStatus, jqXHR) {  // success callback
                if (textStatus === "success") {
                    data.forEach(criaTabelaUser);

                    function criaTabelaUser(usuarios) {
                        console.log(usuarios);
                        if(usuarios.status === defineStatus){
                            $('#tabela-user').append(createTabelaUsuario(usuarios) + estaAtivoUser(usuarios))
                        }
                    }
                }
            }
        );
    }else{
        alert("Não foi possivel definir o status");
    }

}

function createTabela(produtos) {
    return "<tr><th scope=\"row\">"
        + produtos.id +
        "</th><td>"
        + produtos.nome +
        "</td><td>"
        + produtos.descricao +
        "</td><td> R$: "
        + produtos.valor +
        "</td><td>" +
        produtos.categoria +
        "</td><td>" +
        produtos.quantidade +
        "</td><td>" +
        "<a type=\"button\" class=\"btn btn-primary btn-editar\" style='margin-right: 10px;' onclick='editarProduto(" + produtos.id + ")'>Editar</a>" +
        "<a type=\"button\" class=\"btn btn-primary\" style='margin-right: 10px;' onclick='verDetalhes(" + produtos.id + ")' >Detahes</a>"
}

function createTabelaUsuario(usuario) {
    return "<tr><th scope=\"row\">"
        + usuario.id +
        "</th><td>"
        + usuario.nome +
        "</td><td>"
        + usuario.email +
        "</td><td>"
        + usuario.senha +
        "</td><td>" +
        "<a type=\"button\" class=\"btn btn-primary btn-editar\" style='margin-right: 10px;' onclick='editarUsuario(" + usuario.id + ")'>Editar</a>"
}

function estaAtivo(produto) {
    if (produto.ativo > 0) {
        return "<a type='button' class='btn btn-danger'  onclick='mudarStatusProduto(" + produto.id + ",\"desativa\")'>Desativar</a></td></tr>"
    } else {
        return "<a type='button' class='btn btn-success' onclick='mudarStatusProduto(" + produto.id + ",\"ativa\")\'>Ativar</a></td></tr>"
    }
}

function estaAtivoUser(produto) {
    if (produto.status === true) {
        return "<a type='button' class='btn btn-danger'  onclick='mudarStatusUsuario(" + produto.id + ",\"desativa\")'>Desativar</a></td></tr>"
    } else {
        return "<a type='button' class='btn btn-success' onclick='mudarStatusUsuario(" + produto.id + ",\"ativa\")\'>Ativar</a></td></tr>"
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
    } else {
        alert("Não foi possivel editar produto")
    }
}

function mudarStatusUsuario(id, ativa) {
    let usuarioEncontrado = buscarDetalhesDoUsuario(id);
    let acao;
    if (ativa === "ativa") {
        usuarioEncontrado.status = 1;
        acao = confirm("Tem certeza que deseja Ativar esse usuário?")
    } else if (ativa === "desativa") {
        usuarioEncontrado.status = 0;
        acao = confirm("Tem certeza que deseja desativar esse usuário?")
    } else {
        alert("Não foi possivel definir o status");
        return
    }

    if (!!usuarioEncontrado && acao) {
        $.ajax({
            url: '/javaHome/auth/deleta-usuario/' + id + '/' + usuarioEncontrado.status,
            type: 'POST',
            dataType: "json",
            data: JSON.stringify(usuarioEncontrado),
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                if (data !== "BAD_REQUEST"){
                    alert("Usuário editado com sucesso.")
                }else{
                    alert("Não foi possivel editar o Usuário")
                }
                listarUsuarios()
            }
        });

    } else {
        alert("Não foi possivel editar o Usuário")
    }
}

function buscarDetalhesDoProduto(id) {
    let resultado = null;
    $.ajax({
        url: '/produto/' + id,
        async: false,
        success: function (data) {
            resultado = data
        }
    });
    return resultado
}

function buscarDetalhesDoUsuario(id) {
    let resultado = null;
    $.ajax({
        url: '/javaHome/auth/usuario/' + id,
        async: false,
        success: function (data) {
            resultado = data
        }
    });
    return resultado
}

function verDetalhes(id) {
    window.location.href = "/produto/detalhes/" + id;
}

function reiniciaCampos() {
    $('#nome').val("");
    $('#descricao').val("");
    $('#caracteristicas').val("");
    $('#categoria').val("").prop('selected', true);
    $('#palavraChave').val("");
    $('#money').val("");
    $('#quantidade').val("");
    $('.img-pergunta').remove();
    $('.sem-pergunta').remove();
    $('.perguntas-class').remove();
}

function editarProduto(id) {
    showModal();
    reiniciaCampos();
    $('#form-salvar').attr('action', '/produto/' + id);
    $('#form-salvar').attr('method', 'POST');
    let produto = buscarDetalhesDoProduto(id);
    if (!!produto) {
        console.log(produto);
        $('#nome').val(produto.nome);
        $('#descricao').val(produto.descricao);
        $('#caracteristicas').val(produto.caracteristicas);
        $('#categoria').val(produto.categoria).prop('selected', true);
        $('#palavraChave').val(produto.palavraChave);
        $('#money').val(produto.valor);
        $('#quantidade').val(produto.quantidade);
        if (produto.ativo > 0) {
            $('#true').prop('checked', true)
        } else {
            $('#false').prop('checked', true)
        }
        let urlDasImagens = $.parseJSON(produto.caminhoDaImagem);
        for (i in urlDasImagens) {
            $('#img1').append("<img src='/produto/imagens/" + urlDasImagens[i] + "' class='img-fluid img-thumbnail img-pergunta' alt='' width='90'>");
        }
        let perguntasDoProduto = Array.from(buscarPerguntasDoProduto(id));
        if (perguntasDoProduto.length === 0) {
            $('#lista-pergunta').append("<li class='list-group-item sem-pergunta'><h4>Este produto não comtem perguntas.</h4></li>");
        } else {
            for (i in perguntasDoProduto) {
                let p = perguntasDoProduto[i];
                addPerguntasNoHtmnl(p.pergunta, p.resposta);
            }
        }
    }
}

function editarUsuario(id) {
    showModal();
    reiniciaCampos();
    $('#form-salvar').attr('action', '/javaHome/auth/edita-usuario');
    $('#form-salvar').attr('method', 'POST');
    $('#email').attr('readonly', true);
    $('#form-salvar').append("<input type='hidden' class='form-control' id='id' name='id' value=" + id + ">");
    let usuario = buscarDetalhesDoUsuario(id);
    if (!!usuario) {
        console.log(usuario);
        $('#nome').val(usuario.nome);
        $('#email').val(usuario.email);
        $('#senha').val(usuario.senha);
        $('#cargo').val(usuario.cargo).prop('selected', true);
        $('#id').val(usuario.id);
        if (usuario.status > 0) {
            $('#true').prop('checked', true)
        } else {
            $('#false').prop('checked', true)
        }
    }
}

function buscarPerguntasDoProduto(id) {
    let resultado = null;
    $.ajax({
        url: '/javaHome/filtrar-pergunta/' + id,
        async: false,
        success: function (data) {
            resultado = data
        }
    });
    return resultado
}

function showModal() {
    $('.modal-backdrop').toggle();
    $('#modal-detalhes').toggle();
    $('.modal-backdrop').addClass("show");
    $('#modal-detalhes').addClass("show");
    $('#modal-detalhes').css("background", "#00000047");
}
