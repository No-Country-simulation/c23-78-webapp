import { saveTokens } from "../libs/tokenStorage";

/**
 * Función para autenticar a un usuario mediante una solicitud al backend.
 * @param {string} username - El nombre de usuario o correo electrónico del usuario.
 * @param {string} password - La contraseña del usuario.
 * @returns {Object|string} - Retorna la respuesta del servidor en formato JSON o texto si no es JSON.
 */

export default async function authLoginUser(username, password) {
    // Header de la solicitud
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

    const urlencoded = new URLSearchParams();
    console.log(username, password)
    urlencoded.append("username", username);
    urlencoded.append("password", password);

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: urlencoded,
        redirect: "follow",
    };

    try {
        const response = await fetch(
            "http://trackmyfix-backend.eqgrhtbfgsa4ggdk.brazilsouth.azurecontainer.io:9091/user/login",
            requestOptions
        );

        if (!response.ok) {
            const errorText = await response.text();
            console.error(`Error HTTP: ${response.status}. Detalles: ${errorText}`);
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            const result = await response.json();

            console.log("Respuesta exitosa:", result);
            return result;
        } else {
            console.warn("La respuesta no es JSON válida. Analizando como texto...");
            const result = await response.text();
            console.log("Respuesta exitosa (texto):", result);
            saveTokens(result)
            return result;
        }
    } catch (error) {
        console.error("Error durante el login:", error);
        throw error;
    }
}