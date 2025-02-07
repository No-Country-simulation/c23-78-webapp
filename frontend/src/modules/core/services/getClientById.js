const { VITE_BACKEND_URL } = import.meta.env;

export default async function getClientById(id) {
    try {
        console.log(`${VITE_BACKEND_URL}/user/update/` + id);
        const response = await fetch(`${VITE_BACKEND_URL}/user/update/${id}`);
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
