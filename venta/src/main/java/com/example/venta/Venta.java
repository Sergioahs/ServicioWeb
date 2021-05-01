package com.example.venta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.example.venta.BuscarVentaResponse;
import org.example.venta.EliminarVentaRequest;
import org.example.venta.EliminarVentaResponse;
import org.example.venta.ModificarVentaRequest;
import org.example.venta.ModificarVentaResponse;
import org.example.venta.RegistarVentaRequest;
import org.example.venta.RegistarVentaResponse;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private Integer id;
    private String nombre;
    private String codigo;
    private Integer cantidad;
    private Integer precio;
    private String fecha;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public Integer getPrecio() {
        return precio;
    }
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
