import { useState, useEffect } from "react";
import {modifyWorker} from '../services/modifyWorkers';

const useModifyWorkers = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const updateWorker = async (workerId, workerData) => {
            setLoading(true);
            setError(null);
    
            try {
                const updatedWorker = await modifyWorker(workerId, workerData);
                return updatedWorker;
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };
      updateWorker();
    }, [])
    
    return { updateWorker, loading, error };
};

export default useModifyWorkers;