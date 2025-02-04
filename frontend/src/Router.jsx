import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Principal from './pages/Principal';
import LoginPage from './pages/Auth/LoginPage';
import NewWorkerPage from './pages/Admin/NewWorkerPage';
import NewClientPage from './pages/admin/NewClientPage';
import Admin from './pages/admin/admin';
import TrackingPage from './pages/tracking/trackingPage';
import DeviceNotFoundPage from './pages/admin/DeviceNotFoundPage';
import MovementsTrackPage from './pages/admin/MovementsTrackPage';
import { ProtectedRoute } from './modules/auth/components/ProtectedRoute';
import { AuthProvider } from './modules/auth/components/AuthProvider';
import NewOrderFormPage from './pages/admin/NewOrderFormPage';

const browserRoutes = createBrowserRouter([
    { path: '/', element: <Principal />, },
    { path: '/auth/login', element: <LoginPage />, },
    {
        path: '/', element: <ProtectedRoute />, children: [
            { path: '/admin/*', element: <Admin />, },
            { path: '/admin/worker', element: <NewWorkerPage />, },
            { path: '/admin/client', element: <NewClientPage />, },
            { path: '/admin/newOrder', element: <NewOrderFormPage />, },
        ]
    },
    { path: '/tracking/*', element: <TrackingPage />, },
    { path: '/notFound', element: <DeviceNotFoundPage />, },
    { path: '/admin/trackMovements', element: <MovementsTrackPage />, },
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