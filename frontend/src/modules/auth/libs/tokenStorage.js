/**
 * Se guardan los tokens en el localStorage.
 * @param {string} accessToken
 * @param {string} refreshToken
 */

const TOKEN_KEY = "access_token";
const REFRESH_TOKEN_KEY = "refresh_token";
const USERNAME = "username";

const { VITE_BACKEND_URL } = import.meta.env;

export const saveTokens = (response) => {
  localStorage.setItem(TOKEN_KEY, response.access_token);
  localStorage.setItem(REFRESH_TOKEN_KEY, response.refresh_token);
//   localStorage.setItem(USERNAME, response.username);
  //localStorage.setItem(USER_ROLE, response.user_role); //revisar y editar
  getUserData();
};

/**
 * El token puede ser un string o null si no se encuentra el token.
 * @returns {string | null}
 */
export const getAccessToken = () => {
  try {
    return localStorage.getItem(TOKEN_KEY);
  } catch (error) {
    console.error("Usuario no logeado:", error);
    return null;
  }
};
export const getRefreshToken = () => {
  return localStorage.getItem(REFRESH_TOKEN_KEY);
};

export const getUserData = async () => {
  try {
    const response = await fetch(`${VITE_BACKEND_URL}/user/profile`, {
      method: "GET",
      headers: { authorization: `bearer ${getAccessToken}` },
    });
    if (response.ok) {
      const data = await response.json();
      sessionStorage.setItem('userData', data); 
    }
  } catch (error) {
    return { error: error.message };
  }
};

export const getRole = () => {
    const roleUser = sessionStorage.getItem('userData')
    return roleUser.role;
}

/**
 * Funcion de logout experimental.
 */
export const logout = () => {
  localStorage.removeItem(TOKEN_KEY);
  localStorage.removeItem(REFRESH_TOKEN_KEY);
};
