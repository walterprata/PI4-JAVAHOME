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
    $('#btn-lista-produto').click(function () {
        listarProdutos()
        showModal()
    });
    //ocultar modal

    $('#btn-lista-produto').click(function () {
        $('.modal').modal('show')
    });
    $('#btn-editar').click(function () {
        $('.modal').modal('hide')
    });
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
            "</td><td> R$: "
            + produtos.valor +
            "</td><td>" +
            produtos.categoria +
            "</td><td>" +
            produtos.quantidade +
            "</td><td>" +
            "<a type=\"button\" class=\"btn btn-primary\" id='btn-editar' style='margin-right: 10px;' onclick='editarProduto(" + produtos.id + ")'>Editar</a>" +
            "<a type=\"button\" class=\"btn btn-primary\" style='margin-right: 10px;' onclick='verDetalhes(" + produtos.id + ")' >Detahes</a>"
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
    
    function verDetalhes(id) {
        window.location.href = "/produto/detalhes/"+id;
    }
    function editarProduto(id) {
        showModal()

        $('#form-salvar').attr('action','/produto/'+id);
        $('#form-salvar').attr('method','POST');
        let produto = buscarDetalhesDoProduto(id);
        if (!!produto) {
            console.log(produto);
            $('#nome').val(produto.nome);
            $('#descricao').val(produto.descricao);
            $('#caracteristicas').val(produto.caracteristicas);
            $('#categoria').val(produto.categoria).prop('selected',true);
            $('#palavraChave').val(produto.palavraChave);
            $('#money').val(produto.valor);
            $('#quantidade').val(produto.quantidade);
            if (produto.ativo >0){
                $('#true').prop('checked',true)
            }else{
                $('#false').prop('checked',true)
            }
            let urlDasImagens = $.parseJSON(produto.caminhoDaImagem);
            for(i in urlDasImagens){

                console.log(urlDasImagens[i]);
                $('#img1').append("teste<img src='"+urlDasImagens[i]+"' class='img-fluid img-thumbnail' alt=''>");
                let idImg = "img"+1;
                console.log(idImg);
                $("input:file[id*="+idImg+"]").attr('value',produto.quantidade);
            }


        }
    }

    function showModal() {
        $('.modal-backdrop').toggle();
        $('#modal-detalhes').toggle();
        $('.modal-backdrop').addClass("show");
        $('#modal-detalhes').addClass("show");
        $('#modal-detalhes').css("background","#00000047");
    }
