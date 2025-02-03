import refreshToken from "../services/refreshToken";
import { getAccessToken } from "./tokenStorage";

/**
 * Función para renovar el token de acceso.
 * @returns {Array|null} - Devuelve los nuevos tokens si se renueva correctamente, o null si falla.
 */

export default async function renewToken() {
    // Obtiene el token de acceso almacenado en localStorage
    const REFRESH_TOKEN_KEY = getAccessToken();
    console.log("REFRESH TOKEN KEY", REFRESH_TOKEN_KEY);

    // Si existe un token de acceso, intentamos renovarlo
    if (REFRESH_TOKEN_KEY) {
        try {
            const result = await refreshToken(REFRESH_TOKEN_KEY);
            console.log("Token renovado:", result);
            return result;
        } catch (error) {
            console.error("Error al renovar el token:", error);
            return null;
        }
        // Si no hay un token disponible, retornamos null
    } else {
        console.log("ERROR: Usuario no autenticado");
        return null;
    }
}   