$(document).ready(function () {
    $.get('/produto/listar',  // url
        function (data, textStatus, jqXHR) {  // success callback
            if (textStatus === "success") {
                data.content.forEach(criaTabela);

                function criaTabela(produtos) {

                    $('#vitrine').append(
                        "<div class=\"col-lg-4 col-md-6 mb-4\">" +
                        "            <div class=\"card h-100\">" +
                        "              <a href=\"#\"><img class=\"card-img-top\" src='/produto/imagens"+$.parseJSON(produtos.caminhoDaImagem)[0]+"' alt=\"\"></a>" +
                        "              <div class=\"card-body\">" +
                        "                <h4 class=\"card-title\">" +
                        "                  <a href='/produto/detalhes/"+ produtos.id +"'>"+produtos.nome+"</a>" +
                        "                </h4>" +
                        "                <h5>R$ "+produtos.valor+"</h5>" +
                        "                <p class=\"card-text\">"+produtos.caracteristicas+"</p>" +
                        "              </div>" +
                        "              <div class=\"card-footer\">" +
                        "                <small class=\"text-muted\">&#9733; &#9733; &#9733; &#9733; &#9734;</small>" +
                        "              </div>" +
                        "            </div>" +
                        "          </div>"
                    )
                }
            }
        }
    );
});
