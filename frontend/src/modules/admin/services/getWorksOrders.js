import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env

async function getWorksOrders() {
    const accessToken = getAccessToken();
    console.log("accessToken getWorksOrders: ", accessToken)
    const myHeaders = new Headers();
    myHeaders.append("Authorization", `Bearer ${getAccessToken()}`);

    const requestOptions = {
        method: "GET",
        headers: myHeaders,
        redirect: "follow"
    };
    try {
        const response = await fetch(`${VITE_BACKEND_URL}/work-order`, requestOptions);

        if (!response.ok) {
            const errorText = await response.text();
            console.error(`Error HTTP: ${response.status}. Detalles: ${errorText}`);
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            console.log("Respuesta exitosa:", response);
            return await response.json();
        } else {
            return await response.text();
        }
    } catch (error) {
        console.error("Error en la petición:", error);
        throw error;
    }
}

export default getWorksOrders;
