/*
    Función para modificar las órdenes de un cliente
    Crear un hook para conectar el servicio con el componente
*/
import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

export const modifyOrder = async (orderId, orderData) => {
  try {
    const itsLogged = !!getAccessToken();
    if (!itsLogged) throw new Error("User not authenticated");

    const response = await fetch(`${VITE_BACKEND_URL}/work-order/${orderId}`, {
      method: "PUT",
      headers: {"authorization": `bearer ${getAccessToken}`},
      body: JSON.stringify(orderData),
    });

    if (!response.ok) {
      throw new Error("Error al modificar la orden");
    }

    return await response.json();
  } catch (error) {
    console.error("Error en modifyClientOrder:", error);
    throw error;
  }
};
