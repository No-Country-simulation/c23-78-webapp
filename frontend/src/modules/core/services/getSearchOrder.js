/**
 * @param {string} orderNumber - Número de orden a buscar
 * @returns {Promise<Object>} - Objeto con la información de la orden
 * @throws {Error} - Error de la solicitud
 */

const { VITE_BACKEND_URL } = import.meta.env;

export default async function getSearchOrder(orderNumber) {
    try {
        console.log(`${VITE_BACKEND_URL}/work-order/number/` + orderNumber)
        const response = await fetch(`${VITE_BACKEND_URL}/work-order/number/${orderNumber}`);
        const data = await response.json();

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${data.message || "No encontrado"}`);
        }
        return data;
    } catch (error) {
        console.error("Error en la solicitud:", error);
        return null;
    }
};