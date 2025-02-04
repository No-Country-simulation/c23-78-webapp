import { useState } from "react";
import deleteWorkers from "../services/deleteWorkers";

export default function useDeleteWorkers() {

    // Estados de carga y error
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    // Función para eliminar las órdenes de un cliente
    const handleDeleteWorker = async (workerId) => {
        setLoading(true);
        setError(null);

        // Llamada al servicio para eliminar las órdenes
        const result = await deleteWorkers(workerId);

        // Si la llamada al servicio es exitosa, limpiamos el estado de error
        if (!result.success) {
            setError(result.error);
        }

        setLoading(false);
        return result;
    };

    return { handleDeleteWorker, loading, error };
}
