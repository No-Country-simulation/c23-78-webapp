import { useState, useEffect } from "react";
import getClientList from "../services/getClientList";

const useGetClientList = () => {
    const [clients, setClients] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchClients = async () => {
            setLoading(true);
            setError(null);

            try {
                const clientList = await getClientList();
                setClients(clientList);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchClients();
    }, []);

    return { clients, loading, error };
};

export default useGetClientList;

