package com.trackmyfix.trackmyfix.entity;

import jakarta.persistence.Entity;

public enum State {
    RECIBIDO,            // El dispositivo ha sido recibido en el centro de reparación
    EN_DIAGNOSTICO,      // Se está evaluando el problema del dispositivo
    ESPERANDO_APROBACION,// Esperando aprobación del cliente para la reparación
    EN_REPARACION,       // El dispositivo está en proceso de reparación
    ESPERANDO_REPUESTOS, // Se necesitan repuestos para continuar la reparación
    REPARADO,            // La reparación ha sido completada
    EN_PRUEBAS,         // El dispositivo está siendo probado después de la reparación
    LISTO_PARA_RETIRO,  // El dispositivo está listo para que el cliente lo retire
    ENTREGADO,          // El dispositivo ha sido entregado al cliente
    NO_REPARABLE,       // El dispositivo no pudo ser reparado
    CANCELADO;          // La orden de reparación ha sido cancelada
}
