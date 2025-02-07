import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

export const deleteClientOrder = async (orderId) => {
  try {
    const itsLogged = !!getAccessToken();
    if (!itsLogged) throw new Error("User not authenticated");

    console.log(`Eliminando orden: ${VITE_BACKEND_URL}/work-order/${orderId}/set-active`);

    const response = await fetch(`${VITE_BACKEND_URL}/work-order/${orderId}/set-active`, {
      method: "PATCH",
      headers: {
        "Authorization": `Bearer ${getAccessToken()}`,
      },
    });

    if (!response.ok) {
      const errorData = await response.json();
      console.error("Error completo:", errorData);
      throw new Error(`Error al eliminar la orden: ${JSON.stringify(errorData)}`);
    }

    console.log("Orden eliminada exitosamente");
    return { success: true, message: "Orden eliminada exitosamente" };
  } catch (error) {
    console.error("Error en deleteClientOrder:", error);
    throw error;
  }
};
