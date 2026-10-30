package com.tecnomant.service;

import com.tecnomant.model.*;
import com.tecnomant.structure.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Ejecuta CP-01..CP-15 sobre nuevas instancias, sin tocar nunca la lista de la aplicación. */
public final class LaboratorioPruebas {
    private final ObservableList<CasoPruebaResultado> resultados = FXCollections.observableArrayList();
    public LaboratorioPruebas() {
        agregar("CP-01","Insertar en lista vacía","Se crea el primer nodo"); agregar("CP-02","Insertar al inicio","El nuevo nodo queda como primero"); agregar("CP-03","Insertar al final","El nuevo nodo queda como último");
        agregar("CP-04","Insertar en posición intermedia","Los enlaces quedan correctos"); agregar("CP-05","Buscar existente","Se muestra la orden"); agregar("CP-06","Buscar inexistente","Se informa que no existe");
        agregar("CP-07","Eliminar primero","Se actualiza primero"); agregar("CP-08","Eliminar último","Se actualiza último"); agregar("CP-09","Eliminar intermedio","Los vecinos quedan enlazados");
        agregar("CP-10","Eliminar único","La lista queda vacía"); agregar("CP-11","Recorrer adelante","Orden directo"); agregar("CP-12","Recorrer atrás","Orden inverso");
        agregar("CP-13","Código duplicado","Registro rechazado"); agregar("CP-14","Modificar existente","Datos actualizados"); agregar("CP-15","Eliminar inexistente","La lista no cambia");
    }
    private void agregar(String id,String descripcion,String esperado){resultados.add(new CasoPruebaResultado(id,descripcion,esperado));}
    public ObservableList<CasoPruebaResultado> getResultados(){return resultados;}
    public void ejecutarTodas(){for(CasoPruebaResultado r:resultados) ejecutar(r);}
    public void limpiar(){for(CasoPruebaResultado r:resultados)r.reiniciar();}
    public void ejecutar(CasoPruebaResultado r) {
        try { String detalle = switch(r.getId()) {
            case "CP-01" -> cp01(); case "CP-02" -> cp02(); case "CP-03" -> cp03(); case "CP-04" -> cp04(); case "CP-05" -> cp05(); case "CP-06" -> cp06();
            case "CP-07" -> cp07(); case "CP-08" -> cp08(); case "CP-09" -> cp09(); case "CP-10" -> cp10(); case "CP-11" -> cp11(); case "CP-12" -> cp12();
            case "CP-13" -> cp13(); case "CP-14" -> cp14(); case "CP-15" -> cp15(); default -> throw new IllegalStateException("Caso desconocido"); };
            r.aprobar("Condición esperada verificada",detalle);
        } catch (RuntimeException e) { r.fallar(e.getMessage()); }
    }
    private OrdenTrabajo orden(String codigo){return new OrdenTrabajo(codigo,"Cliente "+codigo,"Descripción "+codigo,LocalDate.of(2026,1,1),"Técnico",Prioridad.MEDIA,EstadoOrden.PENDIENTE,new BigDecimal("1"));}
    private ListaDoble base(){ListaDoble l=new ListaDoble();l.insertarFinal(orden("OT-1"));l.insertarFinal(orden("OT-2"));l.insertarFinal(orden("OT-3"));return l;}
    private void exigir(boolean condicion,String mensaje){if(!condicion)throw new IllegalStateException(mensaje);}
    private String cp01(){ListaDoble l=new ListaDoble();l.insertarInicio(orden("OT-1"));exigir(l.getCantidad()==1&&l.getPrimero()==l.getUltimo(),"No se creó un único nodo.");return "Primer nodo creado correctamente.";}
    private String cp02(){ListaDoble l=base();l.insertarInicio(orden("OT-0"));exigir(l.getPrimero().getOrden().getCodigo().equals("OT-0")&&l.verificarIntegridad().valida(),"El primero o sus enlaces no se actualizaron.");return "Nuevo primero y enlaces válidos.";}
    private String cp03(){ListaDoble l=base();l.insertarFinal(orden("OT-4"));exigir(l.getUltimo().getOrden().getCodigo().equals("OT-4")&&l.verificarIntegridad().valida(),"El último o sus enlaces no se actualizaron.");return "Nuevo último y enlaces válidos.";}
    private String cp04(){ListaDoble l=base();l.insertarEnPosicion(orden("OT-X"),2);exigir(l.nodoEnPosicion(2).getOrden().getCodigo().equals("OT-X")&&l.verificarIntegridad().valida(),"Inserción intermedia incorrecta.");return "Nodo insertado en posición 2 con enlaces coherentes.";}
    private String cp05(){ListaDoble l=base();exigir(l.buscarPorCodigo("OT-2")!=null,"No se encontró una orden existente.");return "OT-2 encontrada.";}
    private String cp06(){ListaDoble l=base();exigir(l.buscarPorCodigo("OT-99")==null,"Se encontró una orden inexistente.");return "La búsqueda devolvió ausencia correctamente.";}
    private String cp07(){ListaDoble l=base();l.eliminarInicio();exigir(l.getPrimero().getOrden().getCodigo().equals("OT-2")&&l.verificarIntegridad().valida(),"No se actualizó el primero.");return "Primero actualizado a OT-2.";}
    private String cp08(){ListaDoble l=base();l.eliminarFinal();exigir(l.getUltimo().getOrden().getCodigo().equals("OT-2")&&l.verificarIntegridad().valida(),"No se actualizó el último.");return "Último actualizado a OT-2.";}
    private String cp09(){ListaDoble l=base();l.eliminarPorCodigo("OT-2");exigir(l.getPrimero().getSiguiente()==l.getUltimo()&&l.verificarIntegridad().valida(),"Los vecinos no quedaron enlazados.");return "Vecinos OT-1 y OT-3 enlazados.";}
    private String cp10(){ListaDoble l=new ListaDoble();l.insertarFinal(orden("OT-1"));l.eliminarPorCodigo("OT-1");exigir(l.estaVacia()&&l.getPrimero()==null&&l.getUltimo()==null,"La lista única no quedó vacía.");return "Lista vacía y extremos null.";}
    private String cp11(){ListaDoble l=base();StringBuilder s=new StringBuilder();for(OrdenTrabajo o:l.recorrerAdelante())s.append(o.getCodigo());exigir(s.toString().equals("OT-1OT-2OT-3"),"Orden de recorrido directo incorrecto.");return "OT-1 → OT-2 → OT-3.";}
    private String cp12(){ListaDoble l=base();StringBuilder s=new StringBuilder();for(OrdenTrabajo o:l.recorrerAtras())s.append(o.getCodigo());exigir(s.toString().equals("OT-3OT-2OT-1"),"Orden de recorrido inverso incorrecto.");return "OT-3 → OT-2 → OT-1.";}
    private String cp13(){ListaDoble l=base();try{l.insertarFinal(orden("OT-2"));throw new IllegalStateException("Se aceptó un código duplicado.");}catch(IllegalArgumentException ok){return "Duplicado rechazado: "+ok.getMessage();}}
    private String cp14(){ListaDoble l=base();l.modificar("OT-2","Nueva descripción","Nuevo técnico",Prioridad.CRITICA,EstadoOrden.FINALIZADA,new BigDecimal("99"));OrdenTrabajo o=l.buscarPorCodigo("OT-2");exigir(o.getTecnicoAsignado().equals("Nuevo técnico")&&o.getEstado()==EstadoOrden.FINALIZADA,"La modificación no se aplicó.");return "Campos autorizados actualizados en el mismo nodo.";}
    private String cp15(){ListaDoble l=base();int antes=l.getCantidad();try{l.eliminarPorCodigo("OT-99");throw new IllegalStateException("Se eliminó una orden inexistente.");}catch(IllegalArgumentException ok){exigir(l.getCantidad()==antes&&l.verificarIntegridad().valida(),"La lista cambió tras la eliminación inexistente.");return "Lista sin cambios tras rechazo.";}}
}
