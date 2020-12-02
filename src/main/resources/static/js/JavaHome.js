//Sempre ofuscar antes de subir
//https://obfuscator.io/
var idPergunta = 0;
var qtdPerguntas = 0;
//Apos o carregamento da pagina
$(document).ready(function () {
    $('#money').mask("##0.00", {reverse: true});
    $('#cpf').mask("###.###.###-##", {reverse: true});
    $('#cep').mask("#####-###", {reverse: true});
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
    $('#btn-listar-todos').addClass("active");
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

// Ao selecionar cliente na opção
$('#cargo').change(function () {
    mudaCargo()
});

// Ao mudar o foco
$('#cep').blur(function () {
    let cep = $('#cep').val();
    let cepFormatado = cep.replace("-", "");
    if (cep.length > 0) {
        $.get('https://viacep.com.br/ws/' + cepFormatado + '/json/',  // url
            function (data, textStatus, jqXHR) {  // success callback
                if (textStatus === "success") {
                    console.log(data);
                    if (data.erro) {
                        $('#cep').removeClass("is-valid");
                        $('#cep').addClass("is-invalid");
                        return
                    }
                    $('#cep').removeClass("is-invalid");
                    $('#cep').addClass("is-valid");
                    $('#bairro').val(data.bairro);
                    $('#localidade').val(data.localidade);
                    $('#logradouro').val(data.logradouro);
                    $('#uf').val(data.uf);
                }
            }
        );
    }
});

$('#cpf').blur(function () {
    let cpf = $('#cpf').val().replace(/[^a-z0-9\s]/gi, "");
    if (cpf.length > 0) {
        if (testaCPF(cpf)) {
            $('#cpf').removeClass("is-invalid");
            $('#cpf').addClass("is-valid");
        } else {
            $('#cpf').removeClass("is-valid");
            $('#cpf').addClass("is-invalid");
        }
    }
});

function testaCPF(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;
    if (strCPF == "00000000000") return false;

    for (i = 1; i <= 9; i++) Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10))) return false;

    Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11))) return false;
    return true;
}

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

$('#btn-add-endereco').click(function () {
    let enderecoObjeto = new Object();

    enderecoObjeto.cep = $('#cep').val() || " ";
    enderecoObjeto.bairro = $('#bairro').val() || " ";
    enderecoObjeto.localidade = $('#localidade').val() || " ";
    enderecoObjeto.logradouro = $('#logradouro').val() || " ";
    enderecoObjeto.uf = $('#uf').val() || " ";
    enderecoObjeto.complemento = $('#complemento').val() || " ";

    criaListaEndereco(enderecoObjeto);
});

function criaListaEndereco(enderecoObjeto) {
    let endereco = enderecoObjeto.cep + ";" + enderecoObjeto.bairro + ";" + enderecoObjeto.localidade + ";" + enderecoObjeto.logradouro + ";" + enderecoObjeto.uf + ";"+enderecoObjeto.complemento+";";

    $('#lista-endereco').append("<tr id='" + qtdPerguntas + "'>" +
        "<th scope=\"row\"><input value='" + endereco + "' name='enderecos[]' hidden/>" + enderecoObjeto.cep +
        "</th><td>" + enderecoObjeto.bairro +
        "</td><td>" + enderecoObjeto.localidade +
        "</td><td>" + enderecoObjeto.logradouro +
        "</td><td>" + enderecoObjeto.uf +
        "</td><td>" + enderecoObjeto.complemento +
        "</td><td><a type='button' class='text-warning'  onclick='removeEndereco(" + qtdPerguntas + ")'>Remover</a>" +
        "</td></tr>");
    qtdPerguntas++

}

function removeEndereco(index) {
    let campo = '#lista-endereco #' + index;
    $(campo).remove();
}

function resetBtnStatusUsuarios() {
    $('#btn-listar-ativos').removeClass("active");
    $('#btn-listar-desativados').removeClass("active");
    $('#btn-listar-todos').removeClass("active");
}

function addPerguntasNoHtmnl(id,pergunta, resposta) {
    if (pergunta !== "" || resposta !== "") {
        $('#lista-pergunta').append("<li class='list-group-item perguntas-class' id='pergunta" + idPergunta + "'>" +
            "<p><b>Pergunta:</b> " + pergunta + "</p><p><b>Resposta:</b> " + resposta + "</p>" +
            "<form action='/produto/remove/pergunta' method='post'>"+
            "<input type='hidden' name='idProduto' value='" + id + "'>" +
            "<input type='hidden' name='idPergunta' value='" + idPergunta + "'>" +
            "<button type='submit' class='btn btn-danger float-right'>Remover</button>"+
            "</form:form>") 
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
    if (status === 1 || status === 0) {
        let defineStatus = false;
        if (status === 1) {
            defineStatus = true
        }

        $('#tabela-user tr').remove();//limpa a tabela
        $.get('/javaHome/auth/listar-todos-usuarios',  // url
            function (data, textStatus, jqXHR) {  // success callback
                if (textStatus === "success") {
                    data.forEach(criaTabelaUser);

                    function criaTabelaUser(usuarios) {
                        console.log(usuarios);
                        if (usuarios.status === defineStatus) {
                            $('#tabela-user').append(createTabelaUsuario(usuarios) + estaAtivoUser(usuarios))
                        }
                    }
                }
            }
        );
    } else {
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
                if (data !== "BAD_REQUEST") {
                    alert("Usuário editado com sucesso.")
                } else {
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
        let perguntasDoProduto = Array.from(produto.duvidas);
        if (perguntasDoProduto.length === 0) {
            $('#lista-pergunta').append("<li class='list-group-item sem-pergunta'><h4>Este produto não comtem perguntas.</h4></li>");
        } else {
            for (i in perguntasDoProduto) {
                let p = perguntasDoProduto[i];
                addPerguntasNoHtmnl(produto.id,p.pergunta, p.resposta);
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
    $('#cpf').attr('readonly', true);
    $('#form-salvar').append("<input type='hidden' class='form-control' id='id' name='id' value=" + id + ">");
    let usuario = buscarDetalhesDoUsuario(id);
    if (!!usuario) {
        console.log(usuario);
        $('#nome').val(usuario.nome);
        $('#email').val(usuario.email);
        $('#cpf').val(usuario.cpf);
        $('#cargo').val(usuario.cargo).prop('selected', true);
        $('#lista-endereco tr').remove();
        for (test in usuario.endereco) {
            criaListaEndereco(usuario.endereco[test])
        }

        mudaCargo();
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
