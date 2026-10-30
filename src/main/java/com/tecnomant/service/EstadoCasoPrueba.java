package com.tecnomant.service;
public enum EstadoCasoPrueba { PENDIENTE("Pendiente"), APROBADA("Aprobada"), FALLIDA("Fallida"); private final String texto; EstadoCasoPrueba(String texto){this.texto=texto;} @Override public String toString(){return texto;} }
