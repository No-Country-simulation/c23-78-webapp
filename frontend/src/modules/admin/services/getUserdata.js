/**
 * Función para obtener los datos del usuario.
 * @param {string} userId - El ID del usuario.
 * @returns {Promise<Object|null>} - Devuelve el objeto con los datos del usuario o null si ocurre un error.
 */


import { getAccessToken } from "../../auth/libs/tokenStorage";

const { VITE_BACKEND_URL } = import.meta.env;

const getUserData = async () => {
    try {
        const accessToken = getAccessToken();
        console.log("accessToken: ", accessToken)
        if (!accessToken) throw new Error("No access token available");

        const myHeaders = new Headers();
        myHeaders.append("Authorization", `Bearer ${accessToken}`);

        const response = await fetch(`${VITE_BACKEND_URL}/user/profile`, {
            method: "GET",
            headers: myHeaders,
        });
        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }
        console.log("response: ", response)
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching user data:", error);
        return null;
    }
};

export default getUserData;
