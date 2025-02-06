const API_URL = "https://tu-api.com/orders"; // Reemplaza con la URL de tu backend

const getWorksOrders = async () => {
  try {
    const response = await fetch(API_URL, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`, // Si usas autenticación
      },
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error al obtener las órdenes de trabajo:", error);
    return { orders: [] }; // Retorna un objeto vacío en caso de error
  }
};

export default getWorksOrders;
