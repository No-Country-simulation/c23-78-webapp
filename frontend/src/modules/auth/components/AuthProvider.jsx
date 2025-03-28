import { useContext, createContext, useState, useEffect } from 'react';
import { getAccessToken } from '../libs/tokenStorage';
import checkUserToken from '../libs/checkUserToken';

const AuthContext = createContext({
  isAuthenticated: false,
  login: () => { },
});

export function AuthProvider({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = getAccessToken();
    setIsAuthenticated(!!token); // convertir el token a booleano
  }, []);

  // actualizar estado
  const login = () => {
    setIsAuthenticated(true);
  };

  const logout = () => {
    setIsAuthenticated(false);
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout }}>

      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
/*

import { useContext, createContext, useState, useEffect } from 'react'
import { getAccessToken } from '../libs/tokenStorage';
import authLoginUser from '../services/authLoginUser';

const accessToken = getAccessToken();
const itsLogged = accessToken ? true : false;
// const authLoginUserValidated = authLoginUser();

const AuthContext = createContext({
    isAuthenticated: false,
})

export function AuthProvider({children}) {
  const [isAuthenticated, setIsAuthenticated] = useState(true);

  return(
    <AuthContext.Provider value={{ isAuthenticated }}>
        {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext)

*/
