import { useState, useEffect } from "react";
import { modifyOrder } from "./modifyOrdersClient";

const useModifyOrdersClient = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    useEffect(() => {
        const updateOrder = async (orderId, orderData) => {
            setLoading(true);
            setError(null);
    
            try {
                const updatedOrder = await modifyOrder(orderId, orderData);
                return updatedOrder;
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };
    
      updateOrder();
    }, [])
    

    return { updateOrder, loading, error };
};

export default useModifyOrdersClient;
