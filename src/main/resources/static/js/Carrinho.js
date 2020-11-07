$('#carrinho-btn-add').click(function () {
    let btn = $('#carrinho-qtd');
    let valUni = $('#carrinho-preco-uni');
    let valorUnitFormatado = valUni.val().replace(",",".");
    let valTotal = $('#carrinho-preco-total');
    let carrinhoQtd = btn.val();
    carrinhoQtd++;

    btn.val(carrinhoQtd);
    valTotal.val((valorUnitFormatado*carrinhoQtd).toFixed(2));
});

$('#carrinho-btn-rmv').click(function () {
    let btn = $('#carrinho-qtd');
    let valUni = $('#carrinho-preco-uni');
    let valorUnitFormatado = valUni.val().replace(",",".");
    let valTotal = $('#carrinho-preco-total');
    let carrinhoQtd = btn.val();
    if(carrinhoQtd >1){
        carrinhoQtd-=1;
        btn.val(carrinhoQtd);
        valTotal.val((valorUnitFormatado*carrinhoQtd).toFixed(2));
    }
});

function soma(id) {

}