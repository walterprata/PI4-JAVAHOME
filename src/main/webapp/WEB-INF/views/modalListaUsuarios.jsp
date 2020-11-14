<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%>
<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="modal-detalhes">
    <div class="modal-dialog modal-xl">
        <div class="modal-content container-fluid">

          <h1>Listar Usuários</h1>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Código</th>
                    <th scope="col">Nome</th>
                    <th scope="col">email</th>
                    <th scope="col">Senha</th>
                    <th scope="col">Editar
                    </th>

                </tr>
                </thead>
                <tbody id="tabela-user">
                </tbody>
            </table>
            <div style="margin: 10px;">
                <p>Mostrar usuários:</p>
                <button type="button" class="btn btn-outline-primary" id="btn-listar-ativos" onclick="listarUsuariosComBaseNoStatus(1)">Ativados</button>
                <button type="button" class="btn btn-outline-danger" id="btn-listar-desativados" onclick="listarUsuariosComBaseNoStatus(0)">Desativados</button>
                <button type="button" class="btn btn-outline-success active" id="btn-listar-todos" onclick="listarUsuarios()">Todos</button>
            </div>

        </div>
    </div>
</div>