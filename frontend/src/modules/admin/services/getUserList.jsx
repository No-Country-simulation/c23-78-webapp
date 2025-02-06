 export async function getUser() {
    try {
        const response = await fetch("/api/user");
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error al obtener el usuario:", error);
        return null;
    }
}