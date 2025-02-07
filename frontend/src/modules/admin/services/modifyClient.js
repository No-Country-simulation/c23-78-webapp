import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

export const modifyClient = async (clientId) => {
  try {
    const itsLogged = !!getAccessToken();
    if (!itsLogged) throw new Error("User not authenticated");

    console.log(`${VITE_BACKEND_URL}/user/${clientId}`);

    const response = await fetch(`${VITE_BACKEND_URL}/user/${clientId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getAccessToken()}`,
      },
    });

    if (!response.ok) {
      const errorData = await response.json();
      console.error("Error completo:", errorData);
      throw new Error(`Error al obtener el cliente: ${JSON.stringify(errorData)}`);
    }

    console.log("Obtención de cliente exitosa");
    return await response.json();
  } catch (error) {
    console.error("Error en getClient:", error);
    throw error; 
  }
};