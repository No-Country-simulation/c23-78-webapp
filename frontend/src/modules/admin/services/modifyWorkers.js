/*
    Función para modificar un trabajador
    Crear un hook para conectar el servicio con el componente
    recomendación: verificar si el que realiza la acción es admin (siempre es bueno meter todas las verificaciones posibles)
*/

import { getAccessToken, getRole } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

export const modifyWorker = async (workerId, workerData) => {
  try {
    const itsLogged = !!getAccessToken();
    if (!itsLogged) throw new Error("User not authenticated");

    const role = getRole();
    if(!role === 'admin') throw new Error("Your role is not authorized")

    const response = await fetch(`${VITE_BACKEND_URL}/${workerId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(workerData),
    });

    if (!response.ok) {
      throw new Error("Error al modificar datos del trabajador");
    }

    return await response.json();
  } catch (error) {
    console.error("Error en modifyWorkers:", error);
    throw error;
  }
};