import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

const createUserOrder = async (clientData) => {
    const accessToken = getAccessToken();
    const url = `${VITE_BACKEND_URL}/user/register`; 
    const headers = new Headers();
    headers.append("Content-Type", "application/json");
    headers.append("Authorization", `Bearer ${accessToken}`);

    const requestOptions = {
        method: "POST",
        headers: headers,
        body: JSON.stringify(clientData),
        redirect: "follow",
    };

    try {
        console.log("Intentando registrar al cliente");
        const response = await fetch(url, requestOptions);
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        const result = await response.json();
        return result;
    } catch (error) {
        console.error("Error al registrar al cliente:", error);
        return null;
    }
};

export default createUserOrder;
