import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

async function getTechnicianList() {
    const accessToken = getAccessToken();  // Corregido aquí
    console.log("accessToken getTechnicianList: ", accessToken);

    const myHeaders = new Headers();
    myHeaders.append("Authorization", `Bearer ${accessToken}`);

    const requestOptions = {
        method: "GET",
        headers: myHeaders,
        redirect: "follow"
    };

    try {
        const response = await fetch(`${VITE_BACKEND_URL}/user/all?role=TECHNICIAN`, requestOptions);

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

export default getTechnicianList;
