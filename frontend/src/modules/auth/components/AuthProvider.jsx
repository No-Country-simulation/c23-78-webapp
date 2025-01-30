import { useContext, createContext, useState, useEffect } from 'react'
import { getAccessToken } from '../libs/tokenStorage';

const accessToken = getAccessToken();
const itsLogged = accessToken ? true : false;

const AuthContext = createContext({
    isAuthenticated: itsLogged,
})

export function AuthProvider({children}) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  return(
    <AuthContext.Provider value={{ isAuthenticated }}>
        {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext)
