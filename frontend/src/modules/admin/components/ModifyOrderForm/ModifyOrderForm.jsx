import React, { useState, useEffect } from "react";
import { useForm, Controller } from "react-hook-form";
import { TextField, Button, Box, Typography, MenuItem, Paper, FormControl, InputLabel, Select, Alert } from "@mui/material";
import { getAccessToken } from "../../../auth/libs/tokenStorage";
import postWorkOrder from "../../../core/services/postWorkOrder";
import getSearchOrder from "../../../core/services/getSearchOrder";
import { modifyOrder } from "../../services/modifyOrder";

export function ModifyOrderForm({ orderNumber }) {
    const { control, handleSubmit, formState: { errors }, reset } = useForm({
        defaultValues: {
            dni: "",
            initialBudget: "",
            orderObservation: "",
            deviceType: "",
            serialNumber: "",
            deviceBrand: "",
            status: "",
            clientProblem: "",
            finalBudget: "",
        },
    });
    const [deviceTypes, setDeviceTypes] = useState([]);
    const [statuses, setStatuses] = useState([]);
    const [message, setMessage] = useState(null);
    const [orderData, setOrderData] = useState({});
    const [orderID, setOrderID] = useState("");

    useEffect(() => {
        const fetchOrderData = async () => {
            if (!orderNumber) return;

            console.log("Llamando a getSearchOrder con:", orderNumber);
            const order = await getSearchOrder(orderNumber);
            setOrderData(order);
            setOrderID(order.idOrder);
            console.log("order", order);
        };
        fetchOrderData();
    }, [orderNumber]);


    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = getAccessToken();
                const headers = { Authorization: `Bearer ${token}` };
                const [typesRes, statesRes] = await Promise.all([
                    fetch("http://localhost:9091/device/types", { headers }),
                    fetch("http://localhost:9091/device/states", { headers })
                ]);

                const types = await typesRes.json();
                const states = await statesRes.json();
                setDeviceTypes(types);
                setStatuses(states);
            } catch (error) {
                console.error("Error fetching data:", error);

            }
        };
        fetchData();
    }, []);

    useEffect(() => {
        if (orderData) {
            reset(formatOrderData(orderData));
        }
    }, [orderData]);

    function formatOrderData(order) {
        return {
            dni: order?.client?.dni || "DNI no encontrado",
            initialBudget: order?.devices?.[0]?.initialPrice || "Presupuesto inicial no disponible",
            orderObservation: order?.observations || "Sin observaciones",
            deviceType: order?.devices?.[0]?.type || "Tipo de dispositivo no disponible",
            serialNumber: order?.devices?.[0]?.serialNumber || "Número de serie no disponible",
            deviceBrand: order?.devices?.[0]?.model || "Marca no disponible",
            status: order?.devices?.[0]?.state || "Estado no disponible",
            clientProblem: order?.devices?.[0]?.clientDescription || "Descripción del cliente no disponible",
            finalBudget: order?.devices?.[0]?.finalPrice || "Presupuesto final no disponible",
        };
    }

    const formattedData = formatOrderData(orderData);

    const onSubmit = async (data) => {

        const payload = {
            dni: data.dni,
            observations: data.orderObservation || "Sin observaciones",

            devices: [
                {
                    model: data.deviceBrand || "Desconocido",
                    serialNumber: data.serialNumber || "Sin número de serie",
                    accessories: data.accessoryObservation || "No especificado",
                    initialPrice: parseFloat(data.initialBudget) || 0,
                    finalPrice: parseFloat(data.finalBudget) || 0,
                    clientDescription: data.clientProblem || "No especificado",
                    technicalReport: data.technicalDiagnosis || "Pendiente",
                    type: data.deviceType || "SIN_TIPO",
                    state: data.status || "RECIBIDO",
                }
            ]
        };
        
        try {
            const result = await modifyOrder(payload, orderID);
            console.log("result", result);
            setMessage({ type: "success", text: "Orden enviada con éxito." });

        } catch (error) {
            setMessage({ type: "error", text: "Error al enviar la orden. Inténtalo de nuevo." });
        }
    };

    return (
        <Paper elevation={2} sx={{ width: "100%", maxWidth: 700, margin: "auto", mt: 4, p: 3 }}>
            <Typography variant="h5" gutterBottom>Modificar Orden de Trabajo</Typography>
            <Box
                component="form"
                onSubmit={handleSubmit(onSubmit)}
                sx={{ mt: 3 }}
            >

                <Controller name="dni" control={control} rules={{ required: "DNI es obligatorio" }}
                    render={({ field }) => <TextField {...field} fullWidth label="DNI del Cliente" margin="normal" error={!!errors.dni} helperText={errors.dni?.message} />} />

                <Controller name="initialBudget" control={control}
                    render={({ field }) => <TextField {...field} fullWidth label="Presupuesto Inicial" type="number" margin="normal" />} />

                <Controller name="orderObservation" control={control}
                    render={({ field }) => <TextField {...field} fullWidth label="Observación de Orden" multiline rows={3} margin="normal" />} />

                <Controller name="deviceType" control={control}
                    render={({ field }) => (
                        <FormControl fullWidth margin="normal">
                            <InputLabel>Tipo de Dispositivo</InputLabel>
                            <Select {...field} label="Tipo de Dispositivo">
                                {deviceTypes.map((type) => (
                                    <MenuItem key={type} value={type}>
                                        {type.replace(/_/g, " ")}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    )} />

                <Controller name="serialNumber" control={control}
                    render={({ field }) => <TextField {...field} fullWidth label="Número de Serie" margin="normal" />} />

                <Controller name="deviceBrand" control={control}
                    render={({ field }) => <TextField {...field} fullWidth label="Marca del Dispositivo" margin="normal" />} />

                <Controller name="status" control={control}
                    render={({ field }) => (
                        <FormControl fullWidth margin="normal">
                            <InputLabel>Estado</InputLabel>
                            <Select {...field} label="Estado">
                                {statuses.map((status, index) => (
                                    <MenuItem key={status} value={status} index={index}>
                                        {status.replace(/_/g, " ")}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    )} />

                <Controller name="clientProblem" control={control}
                    render={({ field }) => <TextField {...field} fullWidth label="Problema Reportado" multiline rows={3} margin="normal" />} />

                <Controller name="finalBudget" control={control}
                    render={({ field }) => <TextField {...field} fullWidth label="Presupuesto Final" type="number" margin="normal" />} />

                <Button type="submit" variant="contained" fullWidth sx={{ mt: 3, mb: 1, bgcolor: "#f4511e", "&:hover": { bgcolor: "#e64a19" } }}>Guardar Cambios</Button>
            </Box>
        </Paper>
    );
}
