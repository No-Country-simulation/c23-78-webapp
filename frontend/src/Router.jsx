import { createBrowserRouter } from 'react-router-dom';
import { RouterProvider } from 'react-router-dom';
import Principal from './pages/Principal';
import LoginPage from './pages/Auth/LoginPage';
import RegisterPage from './pages/Auth/RegisterPage';
import Admin from './pages/admin/admin';
import NewWorkerPage from './pages/Admin/NewWorkerPage';
import NewClientPage from './pages/admin/NewClientPage';
import DeviceNotFoundPage from './pages/admin/DeviceNotFoundPage';



const browserRoutes = createBrowserRouter([
    { path: '/', element: <Principal />, },
    { path: '/auth/login', element: <LoginPage />, },
    { path: '/auth/register', element: <RegisterPage />, },
    { path: `/admin/*`, element: <Admin />, },
    { path: '/admin/worker', element: <NewWorkerPage />, },
    { path: '/admin/client', element: <NewClientPage />, },
    { path: '/admin/notFound', element: <DeviceNotFoundPage />, },


]);


const Router = () => {
    return (
        <div>
            <RouterProvider router={browserRoutes} />
        </div>
    )
}

export default Router;