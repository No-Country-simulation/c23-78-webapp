import { saveTokens, getRefreshToken } from "../libs/tokenStorage";

const { VITE_BACKEND_URL } = import.meta.env;

export default async function refreshToken() {
    try {
        const refreshToken = getRefreshToken();
        if (!refreshToken) {
            throw new Error("No refresh token found.");
        }

        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

        const urlencoded = new URLSearchParams();
        urlencoded.append("refresh_token", refreshToken);

        const requestOptions = {
            method: "POST",
            headers: myHeaders,
            body: urlencoded,
            redirect: "follow",
        };

        const response = await fetch(`${VITE_BACKEND_URL}/user/login`, requestOptions);

        if (!response.ok) {
            const errorText = await response.text();
            console.error(`Error HTTP: ${response.status}. Detalles: ${errorText}`);
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const contentType = response.headers.get("content-type");
        let result;

        if (contentType && contentType.includes("application/json")) {
            result = await response.json();
        } else {
            result = await response.text();
            console.warn("La respuesta no es JSON válida. Analizando como texto...");
        }

        console.log("Token renovado con éxito:", result);

        // Guardar los nuevos tokens en localStorage
        saveTokens(result);

        return result;
    } catch (error) {
        console.error("Error durante la renovación del token:", error);
        throw error;
    }
}
