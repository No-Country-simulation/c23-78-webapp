package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.Entity;

public enum Action {
    CREO_ORDEN_TRABAJO,         // Creó una nueva orden de trabajo
    MODIFICO_ORDEN_TRABAJO,     // Modificó una orden de trabajo existente
    ELIMINO_ORDEN_TRABAJO,
    ACTIVO_ORDEN_TRABAJO,// Eliminó una orden de trabajo
    AGREGO_DISPOSITIVO,         // Agregó un dispositivo a la orden
    ELIMINO_DISPOSITIVO,        // Eliminó un dispositivo de la orden
    MODIFICO_DATOS_DISPOSITIVO, // Modificó los datos de un dispositivo en reparación
    CAMBIO_ESTADO_DISPOSITIVO,
    CAMBIO_TIPO_DISPOSITIVO,
    CAMBIO_REPORTE_TECNICO_DISPOSITIVO,
    CAMBIO_REPORTE_CLIENTE_DISPOSITIVO,
    CAMBIO_PRECIO_FINAL_DISPOSITIVO,
    CAMBIO_PRECIO_INICIAL_DISPOSITIVO,
    CAMBIO_ACCESORIOS_DISPOSITIVO,
    CAMBIO_MODELO_DISPOSITIVO,
    CAMBIO_NROSERIE_DISPOSITIVO  // Cambió el estado del dispositivo en reparación
}
