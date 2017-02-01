/**
 * Created by jgodoi on 01/02/2017.
 */
//FIXME There is a better way
host="http://localhost:8080/cervejaria/services/"
function listarCervejas() {
    $.ajax({
        url: host+'cervejas',
        type: 'GET',
        success: function (data) {
            alert("Dados retornados com sucesso");
        },
        error: function (data) {
            alert("Erro na invocação");
        }
    });
}
$(listarCervejas);