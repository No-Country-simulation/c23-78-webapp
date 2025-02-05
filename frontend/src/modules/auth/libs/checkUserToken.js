import getUserData from "../../admin/services/getUserdata";
import renewToken from "./renewToken";
import { AuthProvider } from "../components/AuthProvider";
export default async function checkUserToken() {
    const { logout } = AuthProvider;
    try {
        const userData = await getUserData();
    } catch (error) {
        console.error("Error en la solicitud, intentando renovar token:");
        renewToken();
    }
    try {
        const refreshToken = await renewToken();
    } catch (error) {
        console.error("Error en la solicitud, cerrando sesion");
        logout();
    }
}