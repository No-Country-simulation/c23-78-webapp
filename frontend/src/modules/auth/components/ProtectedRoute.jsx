import React from 'react'
import { Outlet, Navigate } from 'react-router-dom'
import { useAuth } from './AuthProvider'


export const ProtectedRoute = () => {
    const auth = useAuth()

  return auth.isAuthenticated ? <Outlet /> : <Navigate to='/auth/login' /> 
}
