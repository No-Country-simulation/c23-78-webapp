import React from "react"
import { useForm, Controller } from "react-hook-form"
import { TextField, Button, Box, Typography, MenuItem, Paper, FormControl, InputLabel, Select } from "@mui/material"

const options = [
  "RECIBIDO",
  "EN_DIAGNOSTICO",
  "ESPERANDO_APROBACION",
  "EN_REPARACION",
  "ESPERANDO_REPUESTOS",
  "REPARADO",
  "EN_PRUEBAS",
  "LISTO_PARA_RETIRO",
  "ENTREGADO",
  "NO_REPARABLE",
  "CANCELADO",
]

const deviceTypes = ["Laptop", "Desktop", "Tablet", "Smartphone", "Printer", "Monitor", "Other"]

export function OrderForm() {
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: {
      deviceType: "",
      status: "",
    },
  })

  const onSubmit = (data) => {
    console.log(data)
    // Handle form submission
  }

  return (
    <Paper elevation={2} sx={{
      width: "100%",
      maxWidth: { xs: "100%", sm: 700 },
      margin: "auto",
      mt: { xs: 2, sm: 4},
      p: { xs: 2, sm:3},
      backgroundColor: "#fff",
      borderRadius: "8px",
    }}>
      <Typography variant="h5" component="h2" gutterBottom>
        Nueva Orden
      </Typography>

      <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ mt: 3 }}>
        <Controller
          name="dni"
          control={control}
          rules={{
            required: "DNI es obligatorio",
            pattern: {
              value: /^[0-9]+$/,
              message: "El DNI debe ser numérico",
            },
          }}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="DNI"
              margin="normal"
              error={!!errors.dni}
              helperText={errors.dni?.message}
              inputProps={{ inputMode: "numeric", pattern: "[0-9]*" }}
              
            />
          )}
        />

        <Controller
          name="initialBudget"
          control={control}
          render={({ field }) => (
            <TextField {...field} fullWidth label="Presupuesto Inicial" type="number" margin="normal" />
          )}
        />

        <Controller
          name="orderObservation"
          control={control}
          render={({ field }) => (
            <TextField {...field} fullWidth label="Observación de orden" multiline rows={3} margin="normal" />
          )}
        />

        <Controller
          name="deviceType"
          control={control}
          render={({ field }) => (
            <FormControl fullWidth margin="normal">
              <InputLabel>Tipo de dispositivo</InputLabel>
              <Select {...field} label="Tipo de dispositivo">
                {deviceTypes.map((type) => (
                  <MenuItem key={type} value={type.toLowerCase()}>
                    {type}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          )}
        />

        <Controller
          name="accessoryObservation"
          control={control}
          render={({ field }) => (
            <TextField {...field} fullWidth label="Observación (accesorios)" multiline rows={3} margin="normal" />
          )}
        />

        <Controller
          name="serialNumber"
          control={control}
          render={({ field }) => (
            <TextField {...field} fullWidth label="Número de serie del dispositivo" margin="normal" />
          )}
        />

        <Controller
          name="deviceBrand"
          control={control}
          render={({ field }) => <TextField {...field} fullWidth label="Marca del dispositivo" margin="normal" />}
        />

        <Controller
          name="status"
          control={control}
          render={({ field }) => (
            <FormControl fullWidth margin="normal">
              <InputLabel>Estado</InputLabel>
              <Select {...field} label="Estado">
                {options.map((status) => (
                  <MenuItem key={status} value={status}>
                    {status.replace(/_/g, " ")}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          )}
        />

        <Controller
          name="clientProblem"
          control={control}
          render={({ field }) => (
            <TextField
              {...field}
              fullWidth
              label="Problema reportado por el cliente"
              multiline
              rows={3}
              margin="normal"
            />
          )}
        />

        <Controller
          name="technicalDiagnosis"
          control={control}
          render={({ field }) => (
            <TextField {...field} fullWidth label="Diagnóstico del técnico" multiline rows={3} margin="normal" />
          )}
        />

        <Controller
          name="finalBudget"
          control={control}
          render={({ field }) => (
            <TextField {...field} fullWidth label="Presupuesto final" type="number" margin="normal" />
          )}
        />

        <Button
          type="submit"
          variant="contained"
          fullWidth
          sx={{
            mt: 3,
            mb: 1,
            bgcolor: "#f4511e",
            "&:hover": {
              bgcolor: "#e64a19",
            },
          }}
        >
          Añadir nueva orden
        </Button>

        <Button
          fullWidth
          onClick={() => window.history.back()}
          sx={{
            color: "text.secondary",
            textTransform: "none",
          }}
        >
          No añadir orden
        </Button>
      </Box>
    </Paper>
  )
}

