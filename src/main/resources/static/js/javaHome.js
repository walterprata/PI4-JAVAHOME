//Hey you! Thanks for checking this pen. :) I open source it as library. https://github.com/greenwoodents/quickbeam.js
$(document).ready(function () {
    $('#money').mask("##0.00", {reverse: true});
    $('.alert').click(function () {
        $( ".alert" ).fadeOut();
    });

});

function requestListProdutos(){
    $.get('/produto/listar',  // url
        function (data, textStatus, jqXHR) {  // success callback
            for (var produtos in data.content){
                $("#tabela").append("<tr><th scope=\"row\">"+produtos.id+"</th><td>"+produtos.nome+"</td><td>"+produtos.valor+"</td><td>"+produtos.descricao+"</td>\</tr>");
                console.log(produtos.id);
            }
        });
}