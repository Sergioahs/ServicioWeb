var mensaje

function ini(){
    mensaje = '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">'+
'<Body>' +
    '<ModificarVentaRequest xmlns="http://www.example.org/venta">' +
        '<id>'+ document.getElementById('id').value +'</id>' +
        '<nombre>'+ document.getElementById('nombre').value +'</nombre>' +
        '<codigo>'+ document.getElementById('codigo').value +'</codigo>' +
        '<cantidad>'+ document.getElementById('cantidad').value +'</cantidad>' +
        '<precio>'+ document.getElementById('precio').value +'</precio>' +
        '<fecha>'+ document.getElementById('fecha').value +'</fecha>' +
    '</ModificarVentaRequest>' +
'</Body>' +
'</Envelope>'
}
function soapModificar(){
    ini()
    axios.post('http://localhost:8080/ws/venta', mensaje,{
        headers : {
            'Content-Type' : 'text/xml',
            'SOAPAction' : '',
        }
    })
    .then(function(response){
        console.log(response.data);
        document.getElementById('r').value = resul(response.data);
    })
    .catch(err => console.log(err))
}

function resul(rXml){
    var parser = new DOMParser();
    var xmlDoc = parser.parseFromString(rXml, "text/xml");
    var resultado = xmlDoc.getElementsByTagName("ns2:mensaje")[0].childNodes[0].nodeValue;//.childNodes[0].childNodes[0].nodeValue;
    console.log(resultado);
    return resultado;
}