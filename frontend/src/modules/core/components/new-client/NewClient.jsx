import { useForm } from "react-hook-form";

const clienteNumero = 123; 

const NewClient = () => {
    return (
        <div className="min-h-screen flex flex-col items-center justify-center p-6">
            <form className="bg-white p-10 rounded-lg shadow-md w-full max-w-2xl border border-black ">
                <h2 className="text-2xl font-bold  mb-6">Nuevo Cliente</h2>


                <div className="bg-gray-200 p-4 rounded-lg mb-6 w-full  "><span className="text-lg font-extralight ">Cliente #{clienteNumero}</span>
                </div>

                <div className="flex flex-col mb-6">
                    <label htmlFor="nombre" className="text-sm font-bold"></label>
                    <input
                        type="text"
                        id="nombre"
                        className="border border-black rounded-lg p-3 mt-1"
                        placeholder="Nombre"
                    />
                </div>

                <div className="flex flex-col mb-6">
                    <label htmlFor="apellido" className="text-sm font-bold "></label>
                    <input
                        type="text"
                        id="apellido"
                        className="border border-black rounded-lg p-3 mt-1"
                        placeholder="Apellido"
                    />
                </div>

                <div className="flex flex-col md:flex-row gap-4 mb-6">
                    <div className="flex flex-col w-full">
                        <label htmlFor="dni" className="text-sm font-bold "></label>
                        <input
                            type="text"
                            id="dni"
                            className="border border-black rounded-lg p-3 mt-1"
                            placeholder="DNI"
                        />
                    </div>
                    <div className="flex flex-col w-full">
                        <label htmlFor="telefono" className="text-sm font-bold "></label>
                        <input
                            type="tel"
                            id="telefono"
                            className="border border-black rounded-lg p-3 mt-1"
                            placeholder="Telefono"
                        />
                    </div>
                </div>

                <div className="flex flex-col mb-6">
                    <label htmlFor="direccion" className="text-sm font-bold "> </label>
                    <input
                        type="text"
                        id="direccion"
                        className="border border-black rounded-lg p-3 mt-1"
                        placeholder="Dirrecion"
                    />
                </div>

                <div className="flex flex-col mb-6">
                    <label htmlFor="correo" className="text-sm font-bold"> </label>
                    <input
                        type="email"
                        id="correo"
                        className="border border-black rounded-lg p-3 mt-1"
                        placeholder="Email"
                    />
                </div>


                <button
                    type="submit"
                    className="bg-[#F55F1D] text-white py-3 px-4 rounded-lg w-full hover:bg-[#d14e19] transition duration-300"> Añadir nuevo trabajador</button>
                <p className="text-center text-sm mt-4 hover:underline">No añadir cliente</p>
            </form>
        </div>
    )
}

export default NewClient
