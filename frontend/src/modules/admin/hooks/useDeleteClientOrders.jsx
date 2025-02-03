import { useState } from "react";
import deleteClientOrders from "../services/deleteClientOrders";

export default function useDeleteClientOrders() {

    // Estados de carga y error
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    // Función para eliminar las órdenes de un cliente
    const handleDeleteOrders = async (clientId) => {
        setLoading(true);
        setError(null);

        // Llamada al servicio para eliminar las órdenes
        const result = await deleteClientOrders(clientId);

        // Si la llamada al servicio es exitosa, limpiamos el estado de error
        if (!result.success) {
            setError(result.error);
        }

        setLoading(false);
        return result;
    };

    return { handleDeleteOrders, loading, error };
}


/*
    ejemplo de uso:
    
    import useDeleteClientOrders from "../hooks/useDeleteClientOrders";

    const [clientId, setClientId] = useState("");
    const { handleDeleteOrders, loading, error } = useDeleteClientOrders(); // Hook personalizado

    const handleDelete = async () => {
        if (!clientId) {
            alert("ID no valido");
            return;
        }

        const result = await handleDeleteOrders(clientId);
        
        if (result.success) {
            alert("Órdenes eliminadas exitosamente");
        } else {
            alert("Error al eliminar órdenes: " + result.error);
        }
    };

    return (
        <>
            <button onClick={handleDelete}>Eliminar órdenes</button>
                <input
                type="text"
                placeholder="Ingrese el ID del cliente"
                value={clientId}
                onChange={(e) => setClientId(e.target.value)}
                className="border border-gray-300 p-2 rounded-md w-full mb-3"
            />
        </>
     
    );
*/