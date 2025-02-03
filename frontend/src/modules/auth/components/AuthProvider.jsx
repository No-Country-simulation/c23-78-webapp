import { useContext, createContext, useState, useEffect } from 'react';
import { getAccessToken } from '../libs/tokenStorage';

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
  //pasar el estado y el login (login probablemente cambie)
  return (
    <AuthContext.Provider value={{ isAuthenticated, login }}>
      <button onClick={login}>Login</button>
      <button onClick={logout}>Logout</button>
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
  isAuthenticated: itsLogged,
})

export function AuthProvider({ children }) {

  const [isAuthenticated, setIsAuthenticated] = useState(false);

  return (
    <AuthContext.Provider value={{ isAuthenticated }}>
      {children}
    </AuthContext.Provider>
  )
}

<<<<<<< HEAD
export const useAuth = () => useContext(AuthContext)
=======
export const useAuth = () => useContext(AuthContext)

*/
>>>>>>> developer-frontend
