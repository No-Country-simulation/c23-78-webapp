import { useState, useEffect } from "react";
import {getWorkersList} from '../services/getWorkersList'; 

const useGetTrabajadores = () => {
    const [trabajadores, setTrabajadores] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchTrabajadores = async () => {
            try {
                const response = await getWorkersList(); 
                if (!response.ok) {
                    throw new Error("Error al obtener la lista de trabajadores");
                }
                const data = await response.json();
                setTrabajadores(data);
                
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchTrabajadores();
    }, []);

    return { trabajadores, loading, error };
};

export default useGetTrabajadores;
