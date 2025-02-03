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

export const useAuth = () => useContext(AuthContext)