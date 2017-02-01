/**
 * Created by jgodoi on 01/02/2017.
 */
//FIXME Must be a better way
host="http://localhost:8080/cervejaria/services/";
function listarCervejas() {
    $.ajax({
        url: host+'cervejas',
        type: 'GET',
        success: function (data) {
            $('#grid tr:gt(0)').remove();
            if($.isArray(data.cervejas.link)){
                for(var i=0;i<data.cervejas.link.length;i++){
                    var link = data.cervejas.link[i]['@href'];
                    segueLinkCerveja(link);
                }
            } else {
                var link = data.cervejas.link['@href'];
                segueLinkCerveja(link);
            }
        },
        error: function (data) {
            alert("Erro na invocação:"+data.status+" "+data.statusText);
        }
    });
};

function adicionaCerveja() {
    var data = $("#criarCervejaForm").serializeJSON();
    data = "{'cerveja':"+JSON.stringify(data)+"}";
    $.ajax({
        url:host+'cervejas',
        type:'POST',
        contentType:'application/json',
        data:data,
        success:function (data) {
            alert("Incluído com sucesso!");
            listarCervejas();
            $("#criarCervejaForm")[0].reset();
        },
        error: function (data) {
            alert("Ocorreuum erro:"+data.status+" "+data.statusText);
        }
    })
};

function apagaCerveja(id) {
    $.ajax({
        url:host+'cervejas/'+id,
        type:'DELETE',
        success:function (data) {
            listarCervejas();
        },
        error:function (data) {
            alert("Ocorreu um erro:"+data.status+" "+data.statusText);
        }
    });
}

function adicionaCervejaNovaAoGrid(cerveja) {
    var data = "<tr>"
        +"<td>"+cerveja.nome+"</td>"
        +"<td>"+cerveja.cervejaria+"</td>"
        +"<td>"+cerveja.descricao+"</td>"
        +"<td>"+cerveja.tipo+"</td>"
        +"<td><input type=\"button\" value=\"Apagar\" onclick=\"apagaCerveja('"+cerveja.nome+"');\"/> </td>"
        +"</tr>";
    $("#grid").append(data);
};

function segueLinkCerveja(link) {
    $.ajax({
        url: host+link,
        type: 'GET',
        success: function (data) {
            adicionaCervejaNovaAoGrid(data.cerveja);
        },
        error: function (data) {
            alert("Ocorreu um erro:"+data.status+" "+data.statusText);
        }
    })
};

//observer to when page loaded
$(listarCervejas);