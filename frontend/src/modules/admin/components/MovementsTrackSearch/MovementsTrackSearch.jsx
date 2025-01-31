import { useFilterMovements } from "../../hooks/useFilterMovements";

export default function MovementsTrackSearch() {
    const { filters, handleChange, handleSubmit } = useFilterMovements();
    return (
        <form onSubmit={handleSubmit} className="flex flex-col md:flex-row items-center justify-center p-6 w-full gap-4">
            <input
                type="text"
                name="orderNumber"
                value={filters.orderNumber}
                onChange={handleChange}
                className="border border-black rounded-lg p-3 w-full md:w-[300px] bg-inherit"
                placeholder="Filtrar N° Orden"
            />
            <input
                type="text"
                name="technicianName"
                value={filters.technicianName}
                onChange={handleChange}
                className="border border-black rounded-lg p-3 w-full md:w-[300px] bg-inherit"
                placeholder="Filtrar por Nombre Técnico"
            />
            <input
                type="date"
                name="date"
                value={filters.date}
                onChange={handleChange}
                className="border border-black rounded-lg p-3 w-full md:w-[300px] bg-inherit"
            />
            <select
                name="action"
                value={filters.action}
                onChange={handleChange}
                className="border border-black rounded-lg p-3 w-full md:w-[300px] bg-inherit"
            >
                <option value="">Seleccionar Acción</option>
                <option value="Reparación">Reparación</option>
                <option value="Mantenimiento">Mantenimiento</option>
                <option value="Entrega">Entrega</option>
            </select>
            <button
                type="submit"
                className="bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
            >
                Buscar
            </button>
        </form>
        
    )
}