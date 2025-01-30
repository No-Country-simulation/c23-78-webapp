/**
 * Se guardan los tokens en el localStorage.
 * @param {string} accessToken
 * @param {string} refreshToken
 */
export const saveTokens = (response) => {
    const TOKEN_KEY = "access_token";
    const REFRESH_TOKEN_KEY = "refresh_token";

    localStorage.setItem(TOKEN_KEY, response.access_token);
    localStorage.setItem(REFRESH_TOKEN_KEY, response.refresh_token);
};

/**
 * El token puede ser un string o null si no se encuentra el token.
 * @returns {string | null}
 */
export const getAccessToken = () => {
    return localStorage.getItem(TOKEN_KEY);
};
export const getRefreshToken = () => {
    return localStorage.getItem(REFRESH_TOKEN_KEY);
};

/**
 * Funcion de logout experimental.
 */
export const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(REFRESH_TOKEN_KEY);
};
