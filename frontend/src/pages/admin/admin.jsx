import { NavBar } from "../../modules/core/components";
import BasicTabs from "../../modules/admin/components/AdminTab/AdminTab";
import AdminTable from "../../modules/admin/components/AdminTable/AdminTable";
import PageHeader from "../../modules/admin/components/PageHeader/PageHeader";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useUpdatePath from "../../modules/admin/hooks/useUpdatePath";
import { useAuth } from "../../modules/auth/components/AuthProvider";
import { getName, getRole } from "../../modules/auth/libs/tokenStorage";
import refreshToken from "../../modules/auth/services/refreshToken";
import { ClientTable } from "../../modules/admin/components/ClientTable/ClientTable";
import { WorkerTable } from "../../modules/admin/components/WorkerTable/WorkerTable";

const Admin = () => {
  const [name, setName] = useState("roger@trackmyfix.com");
  const [role, setRole] = useState("trabajador");
  const [searchText, setSearchText] = useState("");
  const { logout } = useAuth();
  const navigate = useNavigate();

  const [value, setValue] = useState(0);

  useUpdatePath(searchText, "search");
  useEffect(() => {
    const name = getName();
    setName(name);
    const role = getRole();
    setRole(role);
  }, []);

  const handleLogout = () => {
    logout();
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Buscando:", searchText);
  };

  const handleCreateOrder = () => {
    navigate("/admin/newOrder");
    refreshToken;
  };

  const handleCreateClient = () => {
    navigate("/admin/client");
    refreshToken;
  };

  const handleCreateWorker = () => {
    navigate("/admin/worker");
    refreshToken;
  };

  return (
    <>
      <NavBar />
      <PageHeader
        title={`Bienvenido, ${name}`}
        description={`Bienvenido a la sección de ${role}`}
        buttonText="Cerrar sesión"
        onButtonClick={handleLogout}
      />
      <form
        onSubmit={handleSubmit}
        className="flex flex-row items-center justify-center p-6 w-full"
      >
        <input
          type="search"
          id="search"
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
          className="border border-black rounded-lg p-3 mt-1 w-full md:w-[1400px] bg-inherit"
          placeholder="Buscar..."
        />
        <button
          disabled
          type="submit"
          className="mr-4 ml-4 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
        >
          Buscar
        </button>
      </form>

      <div className="flex flex-col md:flex-row items-start gap-y-2 md:gap-x-4 p-2">
        
        <button
          onClick={handleCreateOrder}
          className="mr-5 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
        >
          Crear orden
        </button>
        <button
          onClick={handleCreateClient}
          className="mr-5 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
        >
          Crear cliente
        </button>
        <button
          onClick={handleCreateWorker}
          className="mr-5 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
        >
          Crear trabajador
        </button>
      </div>

      <BasicTabs
        firts={<AdminTable />}
        second={<ClientTable />}
        third={<WorkerTable />}
      />

      
    </>
  );
};

export default Admin;