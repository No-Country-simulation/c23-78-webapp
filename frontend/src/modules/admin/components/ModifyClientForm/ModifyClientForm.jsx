import React, { useState, useEffect } from "react";
import { useForm, Controller } from "react-hook-form";
import {
  TextField,
  Button,
  Box,
  Typography,
  Paper,
  Alert,
} from "@mui/material";

import getClientById from "../../../core/services/getClientById";
import { modifyClient } from "../../services/modifyClient";

export function ModifyClientForm({ id }) {
  const {
    control,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    defaultValues: {
      name: "",
      lastname: "",
      phone: "",
      addess: "",
      email: "",
    },
  });
  const [clientData, setClientData] = useState({});
  const [message, setMessage] = useState(null);

  useEffect(() => {
    const fetchClientData = async () => {
      if (!id) return;
      try {
        const client = await getClientById(id);
        setClientData(client);
        reset(client);
      } catch (error) {
        console.error("Error fetching client data:", error);
        setMessage({
          type: "error",
          text: "Error al cargar datos del cliente.",
        });
      }
    };
    fetchClientData();
  }, [id, reset]);

  const onSubmit = async (data) => {
    try {
      await modifyClient(id, data);
      setMessage({
        type: "success",
        text: "Datos del cliente modificados con éxito.",
      });
    } catch (error) {
      setMessage({
        type: "error",
        text: "Error al modificar datos del cliente.",
      });
    }
  };

  return (
    <Paper
      elevation={2}
      sx={{ width: "100%", maxWidth: 600, margin: "auto", mt: 4, p: 3 }}
    >
      <Typography variant="h5" gutterBottom>
        Modificar Datos del Cliente
      </Typography>
      {message && <Alert severity={message.type}>{message.text}</Alert>}
      <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ mt: 3 }}>
        <Controller
          name="name"
          control={control}
          rules={{ required: "Nombre es obligatorio" }}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Nombre"
              margin="normal"
              error={!!errors.name}
              helperText={errors.name?.message}
            />
          )}
        />

        <Controller
          name="lastname"
          control={control}
          rules={{ required: "lastname es obligatorio" }}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Lastname"
              type="lastname"
              margin="normal"
              error={!!errors.lastname}
              helperText={errors.lastname?.message}
            />
          )}
        />

        <Controller
          name="phone"
          control={control}
          rules={{ required: "Teléfono es obligatorio" }}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Teléfono"
              type="tel"
              margin="normal"
              error={!!errors.phone}
              helperText={errors.phone?.message}
            />
          )}
        />

        <Controller
          name="email"
          control={control}
          rules={{ required: "Email es obligatorio" }}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Email"
              type="email"
              margin="normal"
              error={!!errors.email}
              helperText={errors.phone?.message}
            />
          )}
        />

        <Controller
          name="address"
          control={control}
          rules={{ required: "Dirección es obligatorio" }}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Address"
              type="text"
              margin="normal"
              error={!!errors.addess}
              helperText={errors.phone?.message}
            />
          )}
        />

        <Button
          type="submit"
          variant="contained"
          fullWidth
          sx={{ mt: 3, bgcolor: "#1976d2", "&:hover": { bgcolor: "#1565c0" } }}
        >
          Guardar Cambios
        </Button>
      </Box>
    </Paper>
  );
}
