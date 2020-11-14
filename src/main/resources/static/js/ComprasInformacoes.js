$(document).ready(function () {
    $('#cpfTitular').mask("###.###.###-##", {reverse: true});
    $('#validadeCartao').mask("##/####");
});
$('#boleto').click(function () {
    if ($('#boleto').prop('checked')){
        $('.compra-informacoes-form-cartao').hide();
        $('.compra-informacoes-form-boleto').show();
    }
});
$('#cartao').click(function () {
    if ($('#cartao').prop('checked')){
        $('.compra-informacoes-form-cartao').show();
        $('.compra-informacoes-form-boleto').hide();
    }
});
