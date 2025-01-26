import { createBrowserRouter  } from 'react-router-dom';
import { RouterProvider } from 'react-router-dom';
import Principal from './pages/Principal';
import LoginPage from './pages/Auth/LoginPage';
import RegisterPage from './pages/Auth/RegisterPage';
import NewWorkerPage from './pages/Admin/NewWorkerPage';


const browserRoutes = createBrowserRouter ([
    { path: '/', element: <Principal />, },
    { path: '/auth/login', element: <LoginPage/>, },
    { path: '/auth/register', element: <RegisterPage />, },
    { path: '/admin/worker', element: <NewWorkerPage />, },

]);


const Router = () => {
    return (
        <div>
            <RouterProvider router={browserRoutes} />
        </div>
    )
}

export default Router;