package com.example.venta;
import java.util.Optional;


import org.example.venta.BuscarVentaResponse;
import org.example.venta.EliminarVentaRequest;
import org.example.venta.EliminarVentaResponse;
import org.example.venta.ModificarVentaRequest;
import org.example.venta.ModificarVentaResponse;
import org.example.venta.RegistarVentaRequest;
import org.example.venta.RegistarVentaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class VentaEndPoint {
    @Autowired
    private IVentas iVentas;
    @PayloadRoot(namespace ="http://www.example.org/venta", localPart="RegistarVentaRequest" )    
    @ResponsePayload
    public RegistarVentaResponse registraVenta(@RequestPayload RegistarVentaRequest peticion){
        RegistarVentaResponse respuesta = new RegistarVentaResponse();
        respuesta.setMensaje("Se agregó correctamente la venta " + peticion.getNombre());

        Venta venta = new Venta();
        venta.setNombre(peticion.getNombre());
        venta.setCodigo(peticion.getCodigo());
        venta.setCantidad(peticion.getCantidad());
        venta.setPrecio(peticion.getPrecio());
        venta.setFecha(peticion.getFecha());

        iVentas.save(venta);

        return respuesta;
    }

    @PayloadRoot(namespace ="http://www.example.org/venta", localPart="BuscarVentaRequest" )
    @ResponsePayload
    public BuscarVentaResponse dameVentas(){
        BuscarVentaResponse respuesta = new BuscarVentaResponse();
        Iterable<Venta> listaVentas = iVentas.findAll();

        for(Venta ls: listaVentas){
            BuscarVentaResponse.Elemento elemento = new BuscarVentaResponse.Elemento();
            elemento.setId(ls.getId());
            elemento.setNombre(ls.getNombre());
            elemento.setCodigo(ls.getCodigo());
            elemento.setCantidad(ls.getCantidad());
            elemento.setPrecio(ls.getPrecio());
            elemento.setFecha(ls.getFecha());
            respuesta.getElemento().add(elemento);
        }
        return respuesta;
    }

    @PayloadRoot(namespace ="http://www.example.org/venta", localPart="ModificarVentaRequest" )
    @ResponsePayload
    public ModificarVentaResponse modificarVenta (@RequestPayload ModificarVentaRequest peticion){
        ModificarVentaResponse respuesta = new ModificarVentaResponse();
        Optional<Venta> s = iVentas.findById(peticion.getId());

        if(s.isPresent()){
            Venta venta = new Venta();
            venta = s.get();
            venta.setNombre(peticion.getNombre());
            venta.setCodigo(peticion.getCodigo());
            venta.setPrecio(peticion.getPrecio());
            venta.setCantidad(peticion.getCantidad());
            venta.setFecha(peticion.getFecha());
            iVentas.save(venta);
            respuesta.setMensaje("Se modificó la venta: " + peticion.getNombre());

        }else{
            respuesta.setMensaje("Id no existe: " + peticion.getId());
        }
        return respuesta;
    }

    @PayloadRoot(namespace ="http://www.example.org/venta", localPart="EliminarVentaRequest" )

    @ResponsePayload
    public EliminarVentaResponse eVentaResponse(@RequestPayload EliminarVentaRequest peticion){
        EliminarVentaResponse respuesta = new EliminarVentaResponse();
        Optional<Venta> s = iVentas.findById(peticion.getId());

        if(s.isPresent()){
            respuesta.setMensaje("Se eliminó la venta " + peticion.getId());
            iVentas.deleteById(peticion.getId());

        }else{
            respuesta.setMensaje("Id no existe: " + peticion.getId());
        }       
    
        return respuesta;
    } 
}
