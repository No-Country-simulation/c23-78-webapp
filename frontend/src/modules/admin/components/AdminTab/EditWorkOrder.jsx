// Puede servir para editar las ordenes 



import React, { useState, useEffect } from "react";

export default function EditWorkOrder({ order, onClose, refreshOrders }) {
    const [orderData, setOrderData] = useState(order);

    useEffect(() => {
        setOrderData(order);
    }, [order]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setOrderData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleDeviceChange = (index, field, value) => {
        const updatedDevices = [...orderData.devices];
        updatedDevices[index] = { ...updatedDevices[index], [field]: value };
        setOrderData((prev) => ({ ...prev, devices: updatedDevices }));
    };

    const updateWorkOrder = async () => {
        try {
            const response = await fetch(`http://localhost:9091/work-order/${orderData.id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(orderData),
            });

            if (!response.ok) throw new Error("Error al actualizar la orden");

            alert("Orden actualizada correctamente");
            refreshOrders(); // Recarga la tabla
            onClose(); // Cierra el formulario
        } catch (error) {
            console.error("Error al actualizar la orden:", error);
            alert("Hubo un error al actualizar la orden");
        }
    };

    return (
        <div className="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white p-6 rounded-lg shadow-lg w-96">
                <h2 className="text-lg font-bold mb-4">Editar Orden N° {orderData.id}</h2>
                
                <label className="block mb-2">Observaciones:</label>
                <textarea
                    name="observations"
                    value={orderData.observations}
                    onChange={handleChange}
                    className="w-full p-2 border rounded"
                />

                {orderData.devices.map((device, index) => (
                    <div key={index} className="mt-4">
                        <h3 className="font-semibold">Dispositivo {index + 1}</h3>

                        <label className="block">Modelo:</label>
                        <input
                            type="text"
                            value={device.model}
                            onChange={(e) => handleDeviceChange(index, "model", e.target.value)}
                            className="w-full p-2 border rounded"
                        />

                        <label className="block">Estado:</label>
                        <input
                            type="text"
                            value={device.state}
                            onChange={(e) => handleDeviceChange(index, "state", e.target.value)}
                            className="w-full p-2 border rounded"
                        />
                    </div>
                ))}

                <div className="mt-4 flex justify-end">
                    <button onClick={onClose} className="mr-2 bg-gray-500 text-white py-2 px-4 rounded">
                        Cancelar
                    </button>
                    <button onClick={updateWorkOrder} className="bg-blue-600 text-white py-2 px-4 rounded">
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    );
}
