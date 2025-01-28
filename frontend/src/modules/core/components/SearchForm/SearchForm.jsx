import searchForm from "../../../../assets/image/searchForm.png";
import searchImage from "../../../../assets/image/searchImage.png";

const SearchForm = () => {
    return (
        <>
            <div className="flex items-center justify-center min-h-screen w-full bg-muted">
                <div className="flex flex-col md:flex-row space-y-6 md:space-y-0 md:space-x-6">
                    <div className="w-full md:w-auto h-auto flex justify-center items-center">
                        <img src={searchImage} alt="Cat" className="w-full md:w-auto" />
                    </div>
                    <div className=" w-full md:w-auto h-auto flex">
                        <div className="flex flex-col items-center justify-center p-6 w-full">
                            <form className="bg-white p-6 md:p-10 rounded-lg shadow-md w-full max-w-2xl border border-black">
                                <div className="w-full flex justify-center mb-6">
                                    <h2 className="text-2xl md:text-3xl font-bold text-[#F55F1D] mb-6">Busca tu equipo</h2>
                                </div>
                                <div className="flex flex-col md:flex-row items-center space-y-4 md:space-y-0 md:space-x-2 mb-6">
                                    <label for="tracking-number" className="text-sm font-bold text-zinc-700">Ingresa el número de seguimiento</label>
                                    <input type="text" id="tracking-number" className="border border-black rounded-lg p-3 mt-1 w-full md:w-96" placeholder="Ingrese el numero de seguimiento" />
                                    <button type="submit" className="bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto">Buscar</button>
                                </div>
                                <p className="text-sm text-zinc-500">Sin guiones ni puntos. Ej. 36000000000000</p>
                                <img src={searchForm} alt="searchForm" className="w-full" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default SearchForm;