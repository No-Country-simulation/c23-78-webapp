import { NavBar } from "../../modules/core/components"
import BasicTabs from "../../modules/admin/components/AdminTab/AdminTab";
import AdminTable from "../../modules/admin/components/AdminTable/AdminTable";

var email = "roger@trackmyfix.com";

const Admin = () => {
    return (
        <>
            <NavBar />
            <div className="flex flex-row items-center justify-between pt-10 pb-10">
                <div className="ml-20">
                    <h2 className="text-3xl font-bold text-[#F55F1D]">Bienvenido, {email}</h2>
                    <p className="text-sm text-zinc-500">Bienvenido a la sección de administración</p>
                </div>
                <div className="mr-20">
                    <button
                        type="submit"
                        className="bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto">
                        Cerrar sesión
                    </button>
                </div>
            </div>
            <div className="w-full h-[1px] bg-black"></div>
            <form className="flex flex-row items-center justify-center p-6 w-full ">
                <input
                    type="search"
                    id="search"
                    className="border border-black rounded-lg p-3 mt-1  w-full md:w-[1400px] bg-inherit"
                    placeholder="Buscar..."
                />
                <button type="submit" className="mr-4 ml-4 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto">Buscar</button>
            </form>
            <div className="flex flex-col items-start justify-between w-full">
                <a>
                    <button type="submit" className="ml-20 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto">Crear factura</button>
                </a>
            </div>
            <BasicTabs
                firts=
                {<>
                    <AdminTable />
                </>}
                second={<>
                    <AdminTable />
                </>} 
            />
        </>
    )
}

export default Admin;