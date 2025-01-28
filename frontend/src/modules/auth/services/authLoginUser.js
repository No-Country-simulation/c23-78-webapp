export default async function authLoginUser(username, password) {
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

    console.log("Enviando solicitud al backend...");

    try {
        const response = await fetch(
            "http://trackmyfix-backend.eqgrhtbfgsa4ggdk.brazilsouth.azurecontainer.io:9091/user/login",
            requestOptions
        );

        if (!response.ok) {
            const errorText = await response.text(); // Inspecciona el error como texto
            console.error(`Error HTTP: ${response.status}. Detalles: ${errorText}`);
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        // Verifica si hay contenido en la respuesta antes de analizarla
        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            const result = await response.json();
            console.log("Respuesta exitosa:", result);
            return result;
        } else {
            console.warn("La respuesta no es JSON válida. Analizando como texto...");
            const result = await response.text();
            console.log("Respuesta exitosa (texto):", result);
            return result; // Retorna el texto si no es JSON
        }
    } catch (error) {
        console.error("Error durante el login:", error);
        throw error;
    }
}