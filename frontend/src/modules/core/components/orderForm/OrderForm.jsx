import React, { useState, useEffect } from "react";
import { useForm, Controller } from "react-hook-form";
import { TextField, Button, Box, Typography, MenuItem, Paper, FormControl, InputLabel, Select, Alert } from "@mui/material";
import { getAccessToken } from "../../../auth/libs/tokenStorage";
import { Logs } from "lucide-react";

export function OrderForm() {
  const { control, handleSubmit, formState: { errors } } = useForm({
    defaultValues: { deviceType: "", status: "" },
  });

  const [deviceTypes, setDeviceTypes] = useState([]);
  const [statuses, setStatuses] = useState([]);
  const [message, setMessage] = useState(null);

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

  const onSubmit = async (data) => {
    const payload = {
      observations: data.orderObservation,
      client: {
          dni: data.dni,
      },

      devices: [{
        dipositivo: data.device,
        model: data.deviceBrand,
        serialNumber: data.serialNumber,
        accessories: data.accessoryObservation,
        initialPrice: parseFloat(data.initialBudget) || 0,
        finalPrice: parseFloat(data.finalBudget) || 0,
        clientDescription: data.clientProblem,
        technicalReport: data.technicalDiagnosis,
        type: data.deviceType,
        state: data.status,
        
      }],
    };

    try {
      const token = getAccessToken();
      const response = await fetch('http://localhost:9091/work-order', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` },
        body: JSON.stringify(payload)
      });

     if (!response.ok) {
        throw new Error("Hubo un problema al enviar la orden.");
      }

      setMessage({ type: "success", text: "Orden enviada con éxito." });
    } catch (error) {
      setMessage({ type: "error", text: "Error al enviar la orden. Inténtalo de nuevo." });
    }
  };

  return (
    <Paper elevation={2} sx={{ width: "100%", maxWidth: 700, margin: "auto", mt: 4, p: 3 }}>
      <Typography variant="h5" gutterBottom>Nueva Orden de Trabajo</Typography>
      {message && <Alert severity={message.type}>{message.text}</Alert>}
      <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ mt: 3 }}>
        {/* DNI */}
        <Controller
          name="dni"
          control={control}
          rules={{ required: "DNI es obligatorio" }}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="DNI del Cliente"
              margin="normal"
              error={!!errors.dni}
              helperText={errors.dni?.message}
            />
          )}
        />

        {/* Presupuesto Inicial */}
        <Controller
          name="initialBudget"
          control={control}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Presupuesto Inicial"
              type="number"
              margin="normal"
            />
          )}
        />

        {/* Observación de Orden */}
        <Controller
          name="orderObservation"
          control={control}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Observación de Orden"
              multiline
              rows={3}
              margin="normal"
            />
          )}
        />

        {/* Tipo de Dispositivo */}
        <Controller
          name="deviceType"
          control={control}
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
          )}
        />

        {/* Número de Serie */}
        <Controller
          name="serialNumber"
          control={control}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Número de Serie"
              margin="normal"
            />
          )}
        />

        {/* Marca de Dispositivo */}
        <Controller
          name="deviceBrand"
          control={control}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Marca del Dispositivo"
              margin="normal"
            />
          )}
        />

        {/* Estado */}
        <Controller
          name="status"
          control={control}
          render={({ field }) => (
            <FormControl fullWidth margin="normal">
              <InputLabel>Estado</InputLabel>
              <Select {...field} label="Estado">
                {statuses.map((status) => (
                  <MenuItem key={status} value={status}>
                    {status.replace(/_/g, " ")}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          )}
        />

        {/* Problema Reportado */}
        <Controller
          name="clientProblem"
          control={control}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Problema Reportado"
              multiline
              rows={3}
              margin="normal"
            />
          )}
        />

        {/* Presupuesto Final */}
        <Controller
          name="finalBudget"
          control={control}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Presupuesto Final "
              type="number"
              margin="normal"
            />
          )}
        />

        {/* Botones */}
        <Button
          type="submit"
          variant="contained"
          fullWidth
          sx={{ mt: 3, mb: 1, bgcolor: "#f4511e", "&:hover": { bgcolor: "#e64a19" } }}
        >
          Crear Orden
        </Button>
        <Button
          fullWidth
          onClick={() => window.history.back()}
          sx={{ color: "text.secondary", mt: 1 }}
        >
          Cancelar
        </Button>
      </Box>
    </Paper>
  );
}
