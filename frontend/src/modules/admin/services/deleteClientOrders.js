/*
    deleteClientOrders.js
    Función para eliminar las órdenes de un cliente
    Crear un hook para conectar el servicio con el componente
*/

import { getAccessToken, getRole } from "../../auth/libs/tokenStorage";

export default async function deleteClientOrders(clientId) {
    try {
        const itsAdmin = getRole() === "admin";
        if (!itsAdmin) throw new Error("Your role is not authorized");
        const itsLogged = (!!getAccessToken());
        if (!itsLogged) throw new Error("User not authenticated");

        // API link sujeto a cambios
        const response = await fetch(`api${clientId}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            throw new Error(`Error al eliminar órdenes: ${response.statusText}`);
        }

        return { success: true };
    } catch (error) {
        return { success: false, error: error.message };
    }
};