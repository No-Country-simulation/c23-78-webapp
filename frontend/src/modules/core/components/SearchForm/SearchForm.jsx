import { useState } from "react";
import { useNavigate } from "react-router-dom"; // Importar useNavigate para la redirección
import searchForm from "../../../../assets/image/searchForm.png";
import searchImage from "../../../../assets/image/searchImage.png";

const SearchForm = () => {
    const [trackingNumber, setTrackingNumber] = useState(""); // Estado para el código
    const [errorMessage, setErrorMessage] = useState(""); // Estado para los mensajes de error
    const navigate = useNavigate(); // Hook para la navegación

    // Función que se ejecuta cuando se hace submit
    const handleSubmit = async (e) => {
        e.preventDefault(); // Evitar que el formulario se recargue

        // Usamos fetch para hacer la solicitud al backend
        try {
            const response = await fetch(`http://localhost:9091/work-order/number/${trackingNumber}`);

            // Si la respuesta es exitosa (status 200), redirigir a la página de éxito
            if (response.ok) {
                navigate(""); // Cambia esta ruta según sea necesario
            } else {
                // Si la respuesta no es 200 (por ejemplo, código incorrecto), mostrar mensaje de error
                setErrorMessage("Código incorrecto. Intenta nuevamente.");
                navigate(""); // Cambia esta ruta según sea necesario
            }
        } catch (error) {
            // Si ocurre un error durante la solicitud (por ejemplo, problemas de red)
            setErrorMessage("Ocurrió un error al realizar la solicitud.");
            navigate("CAMBIAR"); // Cambia esta ruta según sea necesario
        }
    };

    return (
        <>
            <div className="flex items-center justify-center min-h-screen w-full bg-muted">
                <div className="flex flex-col md:flex-row space-y-6 md:space-y-0 md:space-x-6">
                    <div className="w-full md:w-auto h-auto flex justify-center items-center">
                        <img src={searchImage} alt="Cat" className="w-full md:w-auto" />
                    </div>
                    <div className="w-full md:w-auto h-auto flex">
                        <div className="flex flex-col items-center justify-center p-6 w-full">
                            <form onSubmit={handleSubmit} className="bg-white p-6 md:p-10 rounded-lg shadow-md w-full max-w-2xl border border-black">
                                <div className="w-full flex justify-center mb-6">
                                    <h2 className="text-2xl md:text-3xl font-bold text-[#F55F1D] mb-6">Busca tu equipo</h2>
                                </div>
                                <div className="flex flex-col md:flex-row items-center space-y-4 md:space-y-0 md:space-x-2 mb-6">
                                    <label htmlFor="tracking-number" className="text-sm font-bold text-zinc-700">Ingresa el número de seguimiento</label>
                                    <input 
                                        type="text" 
                                        id="tracking-number" 
                                        className="border border-black rounded-lg p-3 mt-1 w-full md:w-96" 
                                        placeholder="Ingrese el numero de seguimiento"
                                        value={trackingNumber}
                                        onChange={(e) => setTrackingNumber(e.target.value)} 
                                    />
                                    <button type="submit" className="bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto">Buscar</button>
                                </div>
                                <p className="text-sm text-zinc-500">Sin guiones ni puntos. Ej. 12345</p>
                                {errorMessage && <p className="text-red-500 text-sm mt-4">{errorMessage}</p>}
                                <img src={searchForm} alt="searchForm" className="w-full" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default SearchForm;
