import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import getSearchOrder from "../../modules/core/services/getSearchOrder";
import { Footer, NavBar } from "../../modules/core/components";
import TrackTimeLine from "../../modules/tracking/componentes/TrackTimeLine/TrackTimeLine";
import { CheckCircle, AlertCircle, XCircle } from "lucide-react";
import renewToken from "../../modules/auth/libs/renewToken";

export default function TrackOrderPage() {
    const { orderNumber } = useParams();

    renewToken();

    const [orderData, setOrderData] = useState({
        number: "",
        devices: [{
            state: "CANCELADO",

        }],
        client: {
            name: "",

        },
    });
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
            <div className="mb-20">
                <NavBar />
                <div className="flex flex-row items-center justify-between pt-10 pb-10">
                    <div className="ml-20">
                        <h2 className="text-3xl font-bold text-[#F55F1D]">Bienvenido {userData.name}</h2>
                        <p className="text-sm text-zinc-500">
                            Página de seguimiento de su orden de reparación
                        </p>
                    </div>

                    <div className="mr-20">
                        <button
                            type="button"
                            className="bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
                            onClick={() => window.history.back()}
                        >
                            Volver
                        </button>
                    </div>
                </div>

                <div className="w-full h-[1px] bg-black"></div>

                <div className="flex flex-col items-center justify-center w-full mt-10">
                    <div className="bg-white shadow-lg rounded-xl p-6 border border-gray-300 max-w-2xl">
                        <h3 className="text-2xl font-bold text-[#F55F1D] text-center mb-4">Detalles del Pedido</h3>

                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">

                            <div>
                                <p className="text-gray-600 font-semibold">Número de Orden:</p>
                                <p className="text-gray-800">{orderData?.number || "Cargando..."}</p>
                            </div>

                            <div className="flex items-center gap-2">
                                <p className="text-gray-600 font-semibold">Estado:</p>
                                {orderData?.devices?.[0]?.state ? (
                                    <>
                                        <StatusIcon status={orderData.devices[0].state} />
                                        <p className="text-gray-800">{orderData.devices[0].state}</p>
                                    </>
                                ) : (
                                    <p className="text-gray-800">Cargando...</p>
                                )}
                            </div>

                            <div className="md:col-span-2">
                                <p className="text-gray-600 font-semibold">Observaciones:</p>
                                <p className="text-gray-800">{orderData?.observations || "Sin observaciones"}</p>
                            </div>

                            <div className="md:col-span-2">
                                <p className="text-gray-600 font-semibold">Cliente:</p>
                                <p className="text-gray-800">{orderData?.client?.name || "Desconocido"}</p>
                            </div>

                            <div className="md:col-span-2">
                                <p className="text-gray-600 font-semibold">Datos del encargo:</p>
                                <p className="text-gray-800">{orderData?.devices?.[0]?.model || "No disponible"}</p>
                                <p className="text-gray-800">{orderData?.devices?.[0]?.serialNumber || "No disponible"}</p>
                                <p className="text-gray-800">{orderData?.devices?.[0]?.accessories || "No disponible"}</p>
                                <p className="text-gray-800">{orderData?.devices?.[0]?.technicalReport || "No disponible"}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <TrackTimeLine orderData={orderData} />
            </div>
            <Footer />

        </div>
    );

}
function StatusIcon({ status }) {
    switch (status) {
        case "LISTO_PARA_RETIRO":
        case "ENTREGADO":
            return <CheckCircle className="text-green-500 w-5 h-5" />;
        case "NO_REPARABLE":
        case "CANCELADO":
            return <XCircle className="text-red-500 w-5 h-5" />;
        default:
            return <AlertCircle className="text-yellow-500 w-5 h-5" />;
    }
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