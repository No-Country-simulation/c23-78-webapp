import getUserData from "../../admin/services/getUserdata";

const TOKEN_KEY = "access_token";
const REFRESH_TOKEN_KEY = "refresh_token";
const NAME = "name";
const ROLE = "role";

/**
 * Guarda los tokens y los datos del usuario en el localStorage.
 * @param {Object} response - Respuesta del backend con los tokens.
 */
export const saveTokens = async (response) => {
    try {
        console.log("TOKENS", response);

        localStorage.setItem(TOKEN_KEY, response.access_token);
        localStorage.setItem(REFRESH_TOKEN_KEY, response.refresh_token);

        const userData = await getUserData(); 
        console.log("userData", userData)
        if (userData) {
            localStorage.setItem(NAME, userData.name);
            localStorage.setItem(ROLE, userData.role);
        } else {
            console.error("Error: No se pudieron obtener los datos del usuario.");
        }

        console.log("Access Token:", localStorage.getItem(TOKEN_KEY));
        console.log("Refresh Token:", localStorage.getItem(REFRESH_TOKEN_KEY));
        console.log("Username:", localStorage.getItem(NAME));
        console.log("User Role:", localStorage.getItem(ROLE));

    } catch (error) {
        console.error("Error al guardar los tokens y datos del usuario:", error);
    }
};

/**
 * Obtiene el token de acceso almacenado en localStorage.
 * @returns {string | null}
 */
export const getAccessToken = () => {
    try {
        return localStorage.getItem(TOKEN_KEY);
    } catch (error) {
        console.error("Error al obtener el access token:", error);
        return null;
    }
};

/**
 * Obtiene el refresh token almacenado en localStorage.
 * @returns {string | null}
 */
export const getRefreshToken = () => {
    return localStorage.getItem(REFRESH_TOKEN_KEY);
};

/**
 * Obtiene el nombre del usuario almacenado en localStorage.
 * @returns {string | null}
 */
export const getName = () => {
    return localStorage.getItem(NAME);
};

/**
 * Obtiene el rol del usuario almacenado en localStorage.
 * @returns {string | null}
 */
export const getRole = () => {
    return localStorage.getItem(ROLE);
};

/**
 * Cierra sesión y elimina los tokens del localStorage.
 */
export const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(REFRESH_TOKEN_KEY);
    localStorage.removeItem(NAME);
    localStorage.removeItem(ROLE);
    console.log("Sesión cerrada. Tokens eliminados.");
};
