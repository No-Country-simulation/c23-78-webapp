/*
     Función para obtener la lista de trabajadores almacenadas del backend
     Crear un hook para conectar el servicio con el componente
*/
import { getAccessToken } from "../../auth/libs/tokenStorage";
const {VITE_BACKEND_URL} = import.meta.env

export default async function getWorkersList() {
  try {
    const itsLogged = !!getAccessToken();
    if (!itsLogged) throw new Error("User not authenticated");

     // API link sujeto a cambios
     const response = await fetch(`${VITE_BACKEND_URL}/user/all`, {
          method: "GET",
          headers: {"authorization": `bearer ${getAccessToken}`},
    
      });

      if (!response.ok) {
          throw new Error(`Error al listar trabajadores: ${response.statusText}`);
      }

      return response;

  } catch (error) {
     return { success: false, error: error.message };
  }
}
