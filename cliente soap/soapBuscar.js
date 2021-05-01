var mensaje

function ini(){
    mensaje = '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">'+
'<Body>' +
    '<BuscarVentaRequest xmlns="http://www.example.org/venta"></BuscarVentaRequest>' +
'</Body>' +
'</Envelope>'
}
function soapBuscar(){
    ini()
    axios.post('http://localhost:8080/ws/venta', mensaje,{
        headers : {
            'Content-Type' : 'text/xml',
            'SOAPAction' : '',
        }
    })
    .then(function(response){
        console.log(response.data);
        var parser = new DOMParser();
        var xmlDoc = parser.parseFromString(response.data, "text/xml");
        var resultado = xmlDoc.getElementsByTagName("ns2:BuscarVentaResponse")[0].childNodes[0];

        do {
            var id = resultado.getElementsByTagName("ns2:id")[0].childNodes[0].nodeValue
            var nombre = resultado.getElementsByTagName("ns2:nombre")[0].childNodes[0].nodeValue
            var codigo = resultado.getElementsByTagName("ns2:codigo")[0].childNodes[0].nodeValue
            var cantidad = resultado.getElementsByTagName("ns2:cantidad")[0].childNodes[0].nodeValue
            var precio = resultado.getElementsByTagName("ns2:precio")[0].childNodes[0].nodeValue
            var fecha = resultado.getElementsByTagName("ns2:fecha")[0].childNodes[0].nodeValue

            fila ="<tr><td>"+id+"</td><td>"+nombre+"</td><td>"+codigo+"</td><td>"+cantidad+"</td><td>"+precio+"</td><td>"+fecha+"</td></tr>";
            var tablaRef = document.getElementById('table').getElementsByTagName('tbody')[0];
            var newRow = tablaRef.insertRow(tablaRef.rows.length);
            newRow.innerHTML = fila;   

            console.log(id + ", "+ nombre + ", "+ codigo + ", "+ cantidad + ", "+ precio + ", "+ fecha);
            resultado = resultado.nextSibling;
        } while (resultado.hasChildNodes() != null);        

        //var fila ="<tr><td>"+id+"</td><td>"+nombre+"</td><td>"+codigo+"</td><td>"+cantidad+"</td></tr>"+precio+"</td></tr>"+fecha+"</td></tr>";
        //var btn = createElement("tr");
        //btn.innerHTML=fila;
        //document.getElementById("r").appendChild(btn);
        
    })
    .catch(err => console.log(err))
}

/*function resul(rXml){   
    var parser = new DOMParser();
    var xmlDoc = parser.parseFromString(rXml, "text/xml");

    var resultado = xmlDoc.getElementsByTagName("ns2:BuscarVentaResponse")[0].childNodes[0];
    do {
        console.log(resultado);
        resultado = resultado.nextSibling;
        if(resultado.childNodes == resultado.lastChild){
            console.log(resultado);
        }
    }while (resultado.hasChildNodes() != null);
    return resultado;
}*/