/*
    Función para obtener la lista de usuarios almacenadas del backend
    Crear un hook para conectar el servicio con el componente
*/

const {VITE_BACKEND_URL} = import.meta.env

export const getUserList = async () => {
    try {
        const response = await fetch(VITE_BACKEND_URL, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            throw new Error("Error al obtener la lista de usuarios");
        }

        return await response.json();
    } catch (error) {
        console.error("Error en getUserList:", error);
        throw error;
    }
};
