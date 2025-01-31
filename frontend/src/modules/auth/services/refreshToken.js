export default async function refreshToken(refreshToken) {
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
            console.log("Token renovado con éxito:", result);
            return result;
        } else {
            console.warn("La respuesta no es JSON válida. Analizando como texto...");
            const result = await response.text();
            console.log("Respuesta (texto):", result);
            return result;
        }
    } catch (error) {
        console.error("Error durante la renovación del token:", error);
        throw error;
    }
}