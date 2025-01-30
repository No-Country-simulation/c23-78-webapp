import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Principal from './pages/Principal';
import LoginPage from './pages/Auth/LoginPage';
import Admin from './pages/admin/Admin';
import NewWorkerPage from './pages/Admin/NewWorkerPage';
import NewClientPage from './pages/admin/NewClientPage';
import TrackingPage from './pages/tracking/trackingPage';
import DeviceNotFoundPage from './pages/admin/DeviceNotFoundPage';
import { ProtectedRoute } from './modules/auth/components/ProtectedRoute';
import { AuthProvider } from './modules/auth/components/AuthProvider';



const browserRoutes = createBrowserRouter([
    { path: '/', element: <Principal />, },
    { path: '/auth/login', element: <LoginPage />, },
    { path: '/', element: <ProtectedRoute />, children: [
        { path: '/admin/*', element: <Admin />, },
        { path: '/admin/worker', element: <NewWorkerPage />, },
        { path: '/admin/client', element: <NewClientPage />, },
    ] },
    { path: '/tracking/*', element: <TrackingPage />, },
    { path: '/admin/notFound', element: <DeviceNotFoundPage />, },
]);


const Router = () => {
    return (
        <div>
            <AuthProvider>

                <RouterProvider router={browserRoutes} />

            </AuthProvider>
        </div>
    )
}

export default Router;