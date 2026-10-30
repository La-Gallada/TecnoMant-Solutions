package com.tecnomant.controller;

import com.tecnomant.model.OrdenTrabajo;
import com.tecnomant.service.ServicioOrdenes;
import com.tecnomant.structure.Nodo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Adaptador entre el dominio y JavaFX. ObservableList es solo una proyección visual de ListaDoble. */
public final class AplicacionController {
    private final ServicioOrdenes servicio = new ServicioOrdenes();
    private final ObservableList<OrdenTrabajo> filas = FXCollections.observableArrayList();
    private Nodo seleccionado;
    public ServicioOrdenes servicio(){return servicio;}
    public ObservableList<OrdenTrabajo> filas(){return filas;}
    public Nodo seleccionado(){return seleccionado;}
    public void seleccionar(Nodo nodo){seleccionado=nodo;}
    public void refrescar(){filas.clear();for(OrdenTrabajo o:servicio.getLista().recorrerAdelante())filas.add(o);if(seleccionado!=null&&servicio.getLista().posicionDe(seleccionado)<0)seleccionado=null;}
    public void seleccionarPorCodigo(String codigo){seleccionado=servicio.getLista().buscarNodoPorCodigo(codigo);}
    public void anterior(){if(seleccionado!=null&&seleccionado.getAnterior()!=null)seleccionado=seleccionado.getAnterior();}
    public void siguiente(){if(seleccionado!=null&&seleccionado.getSiguiente()!=null)seleccionado=seleccionado.getSiguiente();}
}
