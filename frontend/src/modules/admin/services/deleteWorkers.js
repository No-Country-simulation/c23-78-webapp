/*
    Función para eliminar un trabajador
    Crear un hook para conectar el servicio con el componente
    recomendación: verificar si el que realiza la acción es admin (siempre es bueno meter todas las verificaciones posibles)
*/

import { getAccessToken, getRole } from "../../auth/libs/tokenStorage";
const {VITE_BACKEND_URL} = import.meta.env

export default async function deleteWorkers(workerId) {
    try {
        const itsLogged = (!!getAccessToken());
        if (!itsLogged) throw new Error("User not authenticated");

        const role = getRole();
        if(!role === 'ADMIN') throw new Error("Your role is not authorized")

        // API link sujeto a cambios - TECHNICIAN
        const response = await fetch(`${VITE_BACKEND_URL}/user/${workerId}`, {
            method: "DELETE",
            headers: {"authorization": `bearer ${getAccessToken}`}
        });

        if (!response.ok) {
            throw new Error(`Error al eliminar trabajador: ${response.statusText}`);
        }

        return { success: true };
    } catch (error) {
        return { success: false, error: error.message };
    }
};