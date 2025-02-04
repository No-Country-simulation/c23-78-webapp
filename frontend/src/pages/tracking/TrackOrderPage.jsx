import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import getSearchOrder from "../../modules/core/services/getSearchOrder";
import { NavBar, TrackingTimeLine } from "../../modules/core/components";
import PageHeader from "../../modules/admin/components/PageHeader/PageHeader";

export default function TrackOrderPage() {
    const { orderNumber } = useParams();
    const [orderData, setOrderData] = useState(null);
    const [userData, setUserData] = useState({
        name: "",
        role: "client",
    });

    useEffect(() => {
        const fetchOrderData = async () => {
            const order = await getSearchOrder(orderNumber);
            setOrderData(order);
            setUserData({
                ...userData,
                name: order.client.name,
                role: order.client.role,
            });
        };
        fetchOrderData();
    }, [orderNumber]);



    return (
        <div>
            <NavBar />

            <PageHeader title={`Bienvenido ${userData.name}`} description="Seguimiento de su orden de reparación" />
            
            <TrackingTimeLine orderData={orderData} />
        </div>
    );
}

/**
 {
    "idOrder": 4,
    "number": "ORD-00004",
    "observations": "Observation4",
    "orderTotal": 99000,
    "client": {
        "role": "CLIENT",
        "name": "Client4",
        "lastName": "ClientLastName4",
        "dni": "94808004",
        "address": "calle client 4",
        "createdAt": "2025-02-04T20:45:47.544+00:00",
        "updatedAt": "2025-02-04T20:45:47.544+00:00"
    },
    "active": true,
    "createdAt": "2025-02-04T20:45:48.432+00:00",
    "updatedAt": "2025-02-04T20:45:48.988+00:00",
    "devices": [
        {
            "idDevice": 4,
            "model": "Samsung Galaxy S21",
            "serialNumber": "SN-00004",
            "accessories": "Sin accesorios",
            "initialPrice": 9000,
            "finalPrice": 90000,
            "clientDescription": "No enciende",
            "technicalReport": "Falla en la placa",
            "type": "SMARTPHONE",
            "state": "EN_DIAGNOSTICO",
            "createdAt": "2025-02-04T20:45:48.968+00:00",
            "updatedAt": "2025-02-04T20:45:48.968+00:00"
        }
    ]
}
 */