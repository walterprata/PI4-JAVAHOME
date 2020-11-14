<html>
<HEAD>
    <TITLE></TITLE>
    <STYLE>
        td.BoletoCodigoBanco {
            font-size: 6mm;
            font-family: arial, verdana;
            font-weight: bold;
            FONT-STYLE: italic;
            text-align: center;
            vertical-align: bottom;
            border-bottom: 0.15mm solid #000000;
            border-right: 0.15mm solid #000000;
            padding-bottom: 1mm
        }

        td.BoletoLogo {
            border-bottom: 0.15mm solid #000000;
            border-right: 0.15mm solid #000000;
            text-align: center;
            height: 10mm
        }

        td.BoletoLinhaDigitavel {
            font-size: 4mm;
            font-family: arial, verdana;
            font-weight: bold;
            text-align: center;
            vertical-align: bottom;
            border-bottom: 0.15mm solid #000000;
            padding-bottom: 1mm;
        }

        td.BoletoTituloEsquerdo {
            font-size: 0.2cm;
            font-family: arial, verdana;
            padding-left: 0.15mm;
            border-right: 0.15mm solid #000000;
            text-align: left
        }

        td.BoletoTituloDireito {
            font-size: 2mm;
            font-family: arial, verdana;
            padding-left: 0.15mm;
            text-align: left
        }

        td.BoletoValorEsquerdo {
            font-size: 3mm;
            font-family: arial, verdana;
            text-align: center;
            border-right: 0.15mm solid #000000;
            font-weight: bold;
            border-bottom: 0.15mm solid #000000;
            padding-top: 0.5mm
        }

        td.BoletoValorDireito {
            font-size: 3mm;
            font-family: arial, verdana;
            text-align: right;
            padding-right: 3mm;
            padding-top: 0.8mm;
            border-bottom: 0.15mm solid #000000;
            font-weight: bold;
        }

        td.BoletoTituloSacado {
            font-size: 2mm;
            font-family: arial, verdana;
            padding-left: 0.15mm;
            vertical-align: top;
            padding-top: 0.15mm;
            text-align: left
        }

        td.BoletoValorSacado {
            font-size: 3mm;
            font-family: arial, verdana;
            font-weight: bold;
            text-align: left
        }

        td.BoletoTituloSacador {
            font-size: 2mm;
            font-family: arial, verdana;
            padding-left: 0.15mm;
            vertical-align: bottom;
            padding-bottom: 0.8mm;
            border-bottom: 0.15mm solid #000000
        }

        td.BoletoValorSacador {
            font-size: 3mm;
            font-family: arial, verdana;
            vertical-align: bottom;
            padding-bottom: 0.15mm;
            border-bottom: 0.15mm solid #000000;
            font-weight: bold;
            text-align: left
        }

        td.BoletoPontilhado {
            border-top: 0.3mm dashed #000000;
            font-size: 1mm
        }

        ul.BoletoInstrucoes {
            font-size: 3mm;
            font-family: verdana, arial
        }
    </STYLE>
</HEAD>
<BODY>

<P align=center>
<TABLE cellSpacing=0 cellPadding=0 border=0 class=Boleto>

    <TR>
        <TD style='width: 0.9cm'></TD>
        <TD style='width: 1cm'></TD>
        <TD style='width: 1.9cm'></TD>

        <TD style='width: 0.5cm'></TD>
        <TD style='width: 1.3cm'></TD>
        <TD style='width: 0.8cm'></TD>
        <TD style='width: 1cm'></TD>

        <TD style='width: 1.9cm'></TD>
        <TD style='width: 1.9cm'></TD>

        <TD style='width: 3.8cm'></TD>

        <TD style='width: 3.8cm'></TD>
    <tr>
        <td colspan=11>
            <ul class=BoletoInstrucoes>
                <li>Imprima em papel A4 ou Carta</li>
                <li>Utilize margens m�nimas a direita e a esquerda</li>
                <li>Recorte na linha pontilhada</li>
                <li>N�o rasure o c�digo de barras</li>
            </ul>
        </td>
    </tr>
    </TR>
    <tr>
        <td colspan=11 class=BoletoPontilhado>&nbsp;</td>
    </tr>
    <TR>
        <TD colspan=4 class=BoletoLogo><img src='/img/104.jpg'></TD>
        <TD colspan=2 class=BoletoCodigoBanco>104-0</TD>
        <TD colspan=6 class=BoletoLinhaDigitavel>LinhaDigitavel</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoTituloEsquerdo>Local de Pagamento</TD>
        <TD class=BoletoTituloDireito>Vencimento</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoValorEsquerdo style='text-align: left; padding-left : 0.1cm'>LocalDePagamento</TD>
        <TD class=BoletoValorDireito>Vencimento</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoTituloEsquerdo>Cedente</TD>
        <TD class=BoletoTituloDireito>Ag�ncia/C�digo do Cedente</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoValorEsquerdo style='text-align: left; padding-left : 0.1cm'>Cedente</TD>
        <TD class=BoletoValorDireito>AgenciaCodDoCedente</TD>
    </TR>
    <TR>
        <TD colspan=3 class=BoletoTituloEsquerdo>Data do Documento</TD>
        <TD colspan=4 class=BoletoTituloEsquerdo>N�mero do Documento</TD>
        <TD class=BoletoTituloEsquerdo>Esp�cie</TD>
        <TD class=BoletoTituloEsquerdo>Aceite</TD>
        <TD class=BoletoTituloEsquerdo>Data do Processamento</TD>
        <TD class=BoletoTituloDireito>Nosso N�mero</TD>
    </TR>
    <TR>
        <TD colspan=3 class=BoletoValorEsquerdo>DataDoDoc</TD>
        <TD colspan=4 class=BoletoValorEsquerdo>NumeroDodoc</TD>
        <TD class=BoletoValorEsquerdo>RC</TD>
        <TD class=BoletoValorEsquerdo>N</TD>
        <TD class=BoletoValorEsquerdo>DataDoProces</TD>
        <TD class=BoletoValorDireito>NossoNumero</TD>
    </TR>
    <TR>
        <TD colspan=3 class=BoletoTituloEsquerdo>Uso do Banco</TD>
        <TD colspan=2 class=BoletoTituloEsquerdo>Carteira</TD>
        <TD colspan=2 class=BoletoTituloEsquerdo>Moeda</TD>
        <TD colspan=2 class=BoletoTituloEsquerdo>Quantidade</TD>
        <TD class=BoletoTituloEsquerdo>(x) Valor</TD>
        <TD class=BoletoTituloDireito>(=) Valor do Documento</TD>
    </TR>
    <TR>
        <TD colspan=3 class=BoletoValorEsquerdo>&nbsp;</TD>
        <TD colspan=2 class=BoletoValorEsquerdo>SR</TD>
        <TD colspan=2 class=BoletoValorEsquerdo>R$</TD>
        <TD colspan=2 class=BoletoValorEsquerdo>&nbsp;</TD>
        <TD class=BoletoValorEsquerdo>&nbsp;</TD>
        <TD class=BoletoValorDireito>ValorDocumento</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoTituloEsquerdo>Instruco</TD>
        <TD class=BoletoTituloDireito>(-) Desconto</TD>
    </TR>
    <TR>
        <TD colspan=10 rowspan=9 class=BoletoValorEsquerdo
            style='text-align: left; vertical-align:top; padding-left : 0.1cm'>Instrucoes
        </TD>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD class=BoletoTituloDireito>(-) Outras Dedu��es/Abatimento</TD>
    </TR>
    <TR>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD class=BoletoTituloDireito>(+) Mora/Multa/Juros</TD>
    </TR>
    <TR>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD class=BoletoTituloDireito>(+) Outros Acr�scimos</TD>
    </TR>
    <TR>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD class=BoletoTituloDireito>(=) Valor Cobrado</TD>
    </TR>
    <TR>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD rowspan=3 Class=BoletoTituloSacado>Sacado:</TD>
        <TD colspan=8 Class=BoletoValorSacado>NomedoSacado</TD>
        <TD colspan=2 Class=BoletoValorSacado>CpfDoSacado</TD>
    </TR>
    <TR>
        <TD colspan=10 Class=BoletoValorSacado>RuaNumeroBairro</TD>
    </TR>
    <TR>
        <TD colspan=10 Class=BoletoValorSacado>CidadeUf&nbsp;&nbsp;&nbsp;CEP</TD>
    </TR>
    <TR>
        <TD colspan=2 Class=BoletoTituloSacador>Sacador / Avalista:</TD>
        <TD colspan=9 Class=BoletoValorSacador>NomeSacador</TD>
    </TR>
    <TR>
        <TD colspan=11 class=BoletoTituloDireito style='text-align: right; padding-right: 0.1cm'>Recibo do Sacado -
            Autentica��o Mec�nica
        </TD>
    </TR>
    <TR>
        <TD colspan=11 height=60 valign=top>&nbsp</TD>
    </TR>
    <tr>
        <td colspan=11 class=BoletoPontilhado>&nbsp;</td>
    </tr>
    <TR>
        <TD colspan=4 class=BoletoLogo><img src='/img/104.jpg'></TD>
        <TD colspan=2 class=BoletoCodigoBanco>104-0</TD>
        <TD colspan=6 class=BoletoLinhaDigitavel>LinhaDigitavel</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoTituloEsquerdo>Local de Pagamento</TD>
        <TD class=BoletoTituloDireito>Vencimento</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoValorEsquerdo style='text-align: left; padding-left : 0.1cm'>LocalDePagamento</TD>
        <TD class=BoletoValorDireito>Vencimento</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoTituloEsquerdo>Cedente</TD>
        <TD class=BoletoTituloDireito>Ag�ncia/C�digo do Cedente</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoValorEsquerdo style='text-align: left; padding-left : 0.1cm'>Cedente</TD>
        <TD class=BoletoValorDireito>AgenciCodDoCedente</TD>
    </TR>
    <TR>
        <TD colspan=3 class=BoletoTituloEsquerdo>Data do Documento</TD>
        <TD colspan=4 class=BoletoTituloEsquerdo>N�mero do Documento</TD>
        <TD class=BoletoTituloEsquerdo>Esp�cie</TD>
        <TD class=BoletoTituloEsquerdo>Aceite</TD>
        <TD class=BoletoTituloEsquerdo>Data do Processamento</TD>
        <TD class=BoletoTituloDireito>Nosso N�mero</TD>
    </TR>
    <TR>
        <TD colspan=3 class=BoletoValorEsquerdo>DataDoDoc</TD>
        <TD colspan=4 class=BoletoValorEsquerdo>NumeroDodoc</TD>
        <TD class=BoletoValorEsquerdo>RC</TD>
        <TD class=BoletoValorEsquerdo>N</TD>
        <TD class=BoletoValorEsquerdo>DataDoProces</TD>
        <TD class=BoletoValorDireito>NossoNumero</TD>
    </TR>
    <TR>
        <TD colspan=3 class=BoletoTituloEsquerdo>Uso do Banco</TD>
        <TD colspan=2 class=BoletoTituloEsquerdo>Carteira</TD>
        <TD colspan=2 class=BoletoTituloEsquerdo>Moeda</TD>
        <TD colspan=2 class=BoletoTituloEsquerdo>Quantidade</TD>
        <TD class=BoletoTituloEsquerdo>(x) Valor</TD>
        <TD class=BoletoTituloDireito>(=) Valor do Documento</TD>
    </TR>
    <TR>
        <TD colspan=3 class=BoletoValorEsquerdo>&nbsp;</TD>
        <TD colspan=2 class=BoletoValorEsquerdo>SR</TD>
        <TD colspan=2 class=BoletoValorEsquerdo>R$</TD>
        <TD colspan=2 class=BoletoValorEsquerdo>&nbsp;</TD>
        <TD class=BoletoValorEsquerdo>&nbsp;</TD>
        <TD class=BoletoValorDireito>ValorDocumento</TD>
    </TR>
    <TR>
        <TD colspan=10 class=BoletoTituloEsquerdo>Instruco</TD>
        <TD class=BoletoTituloDireito>(-) Desconto</TD>
    </TR>
    <TR>
        <TD colspan=10 rowspan=9 class=BoletoValorEsquerdo
            style='text-align: left; vertical-align:top; padding-left : 0.1cm'>Instrucoes
        </TD>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD class=BoletoTituloDireito>(-) Outras Dedu��es/Abatimento</TD>
    </TR>
    <TR>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD class=BoletoTituloDireito>(+) Mora/Multa/Juros</TD>
    </TR>
    <TR>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD class=BoletoTituloDireito>(+) Outros Acr�scimos</TD>
    </TR>
    <TR>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD class=BoletoTituloDireito>(=) Valor Cobrado</TD>
    </TR>
    <TR>
        <TD class=BoletoValorDireito>&nbsp;</TD>
    </TR>
    <TR>
        <TD rowspan=3 Class=BoletoTituloSacado>Sacado:</TD>
        <TD colspan=8 Class=BoletoValorSacado>NomedoSacado</TD>
        <TD colspan=2 Class=BoletoValorSacado>CpfDoSacado</TD>
    </TR>
    <TR>
        <TD colspan=10 Class=BoletoValorSacado>RuaNumeroBairro</TD>
    </TR>
    <TR>
        <TD colspan=10 Class=BoletoValorSacado>CidadeUf&nbsp;&nbsp;&nbsp;CEP</TD>
    </TR>
    <TR>
        <TD colspan=2 Class=BoletoTituloSacador>Sacador / Avalista:</TD>
        <TD colspan=9 Class=BoletoValorSacador>NomeSacador</TD>
    </TR>
    <TR>
        <TD colspan=11 class=BoletoTituloDireito style='text-align: right; padding-right: 0.1cm'>Ficha de Compensa��o -
            Autentica��o Mec�nica
        </TD>
    </TR>
    <TR>
        <TD colspan=11 height=60 valign=top><img src='/img/codbar.jpg'></TD>
    </TR>
    <tr>
        <td colspan=11 class=BoletoPontilhado>&nbsp;</td>
    </tr>
</TABLE>
</P>

</BODY>
</HTML>
