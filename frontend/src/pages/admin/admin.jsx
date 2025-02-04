import { NavBar } from "../../modules/core/components";
import BasicTabs from "../../modules/admin/components/AdminTab/AdminTab";
import AdminTable from "../../modules/admin/components/AdminTable/AdminTable";
import PageHeader from "../../modules/admin/components/PageHeader/PageHeader";
import { useState } from "react";
import useUpdatePath from "../../modules/admin/hooks/useUpdatePath";

const Admin = () => {
    const [email, setEmail] = useState("roger@trackmyfix.com");
    const [searchText, setSearchText] = useState("");

    useUpdatePath(searchText, "search");

    const handleLogout = () => {
        console.log("Cerrando sesión...");
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Buscando:", searchText);
    };

    return (
        <>
            <NavBar />
            <PageHeader
                title={`Bienvenido, ${email}`}
                description="Bienvenido a la sección de administración"
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
                    type="submit"
                    className="mr-4 ml-4 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
                >
                    Buscar
                </button>
            </form>
            <div className="flex flex-col items-start justify-between w-full">
                <a>
                    <button type="submit" className="ml-20 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto">
                        Crear factura
                    </button>
                </a>
            </div>
            <BasicTabs
                firts={<AdminTable />}
                second={<AdminTable />}
            />
        </>
    );
};

export default Admin;
