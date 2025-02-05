// hooks/useTrackingTimeline.js
import { useState, useEffect } from 'react';

const options = [
    "RECIBIDO",
    "EN_DIAGNOSTICO",
    "ESPERANDO_APROBACION",
    "EN_REPARACION",
    "ESPERANDO_REPUESTOS",
    "REPARADO",
    "EN_PRUEBAS",
    "LISTO_PARA_RETIRO",
    "ENTREGADO",
    "NO_REPARABLE",
    "CANCELADO",
];

const trackingData = [
    {
        status: "RECIBIDO",
        description: "Tu producto ha sido recibido. Estamos preparando el diagnóstico inicial.",
    },
    {
        status: "EN_DIAGNOSTICO",
        description: "Nuestro equipo está evaluando el problema para encontrar la mejor solución.",
    },
    {
        status: "ESPERANDO_APROBACION",
        description: "Estamos esperando recibir la aprobación para continuar con la reparación.",
    },
    {
        status: "EN_REPARACION",
        description: "Estamos trabajando en la reparación de tu producto.",
    },
    {
        status: "ESPERANDO_REPUESTOS",
        description: "Estamos esperando recibir las piezas necesarias para completar la reparación.",
    },
    {
        status: "REPARADO",
        description: "La reparación ha sido completada, y el producto está listo para su verificación.",
    },
    {
        status: "EN_PRUEBAS",
        description: "Estamos verificando que tu producto funcione correctamente después de la reparación.",
    },
    {
        status: "LISTO_PARA_RETIRO",
        description: "Tu producto está reparado y listo para que lo retires.",
    },
    {
        status: "ENTREGADO",
        description: "Ya retiraste tu producto. ¡Gracias por confiar en nosotros!",
    },
    {
        status: "NO_REPARABLE",
        description: "Tu producto no es reparable debido a la gravedad del daño o la falta de piezas.",
    },
    {
        status: "CANCELADO",
        description: "La reparación ha sido cancelada. No se continuará con el proceso. Acerquese a la central de reparación.",
    },
];

export function useTrackingTimeline(orderData) {
    const [orderState, setOrderState] = useState({
        state: "CANCELADO",
    });

    useEffect(() => {
        if (orderData && orderData.devices && orderData.devices.length > 0) {
            setOrderState({ state: orderData.devices[0].state });
        }
    }, [orderData]);

    const generateTimeline = (currentState) => {
        const timeline = [];
        const stateIndex = options.indexOf(currentState);

        for (let i = 0; i <= stateIndex; i++) {
            const state = options[i];
            const matchingData = trackingData.find(item => item.status === state);
            if (matchingData) {
                timeline.push(matchingData);
            }
        }

        if (currentState === "NO_REPARABLE" || currentState === "CANCELADO") {
            const specialMessage = trackingData.find(item => item.status === currentState);
            if (specialMessage) {
                timeline.push(specialMessage);
            }
        }

        return timeline;
    };

    const timelineItems = generateTimeline(orderState.state);

    return timelineItems;
}
