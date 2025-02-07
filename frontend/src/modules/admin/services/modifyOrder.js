/*
    Función para modificar las órdenes de un cliente
    Crear un hook para conectar el servicio con el componente
*/
import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

export const modifyOrder = async (orderData, orderId) => {
  try {
    const itsLogged = !!getAccessToken();
    if (!itsLogged) throw new Error("User not authenticated");

    console.log(`${VITE_BACKEND_URL}/work-order/${orderId}`);

    const response = await fetch(`${VITE_BACKEND_URL}/work-order/${orderId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getAccessToken()}`,
      },
      body: JSON.stringify(orderData),
    });

    if (!response.ok) {
      const errorData = await response.json();
      console.error("Error completo:", errorData);
      throw new Error(`Error al modificar la orden: ${JSON.stringify(errorData)}`);
    }

    console.log("Modificación de orden exitosa");
    return await response.json();
  } catch (error) {
    console.error("Error en modifyClientOrder:", error);
    throw error; 
  }
};
