import getUserData from "../../admin/services/getUserdata";
import renewToken from "./renewToken";

export default async function checkUserToken(logout) {
    try {
        await getUserData();
        return true; // El token es válido
    } catch (error) {
        console.warn("⚠ Token inválido, intentando renovarlo...");

        try {
            const newToken = await renewToken();
            if (newToken) {
                console.log("✅ Token renovado exitosamente");
                return true;
            }
        } catch (renewError) {
            console.error("❌ No se pudo renovar el token, cerrando sesión...");
            logout(); // Llamamos a logout desde el componente React
            return false;
        }
    }
}