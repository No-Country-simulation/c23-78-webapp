import { useForm, Controller } from "react-hook-form";
import { TextField, Button, Paper, Typography, Alert, Box } from "@mui/material";
import createUserOrder from "../../services/createUserOrder";

const NewClient = () => {
    const { control, handleSubmit, formState: { errors } } = useForm();
    const onSubmit = async (data) => {
        const payload = {

            dni: data.dni ?? "DNI no proporcionado",
            //observations: data.orderObservation ?? "Sin observaciones",
            name: data.nombre ?? "Nombre no proporcionado",
            lastName: data.apellido ?? "Apellido no proporcionado",
            phone: data.telefono ?? "Teléfono no proporcionado",
            address: data.direccion ?? "Dirección no proporcionada",
            email: data.correo ?? "Correo no proporcionado",
            password: data.password ?? "Contraseña no proporcionada",
            role: "TECHNICIAN",
        };
        const clientData = payload;
        const response = await createUserOrder(clientData);

        if (response) {
            console.log("Cliente creado exitosamente:", response);
        } else {
            console.error("Error al crear el cliente");
        }
    };



    return (
        <Paper elevation={2} sx={{ width: "100%", maxWidth: 700, margin: "auto", mt: 4, p: 3 }}>
            <Typography variant="h5" gutterBottom>Nuevo Cliente</Typography>
            <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ mt: 3 }}>
                {/* Nombre */}
                <Controller
                    name="nombre"
                    control={control}
                    rules={{ required: "El nombre es obligatorio" }}
                    render={({ field }) => (
                        <TextField {...field} fullWidth label="Nombre" margin="normal" error={!!errors.nombre} helperText={errors.nombre?.message} />
                    )}
                />

                {/* Apellido */}
                <Controller
                    name="apellido"
                    control={control}
                    rules={{ required: "El apellido es obligatorio" }}
                    render={({ field }) => (
                        <TextField {...field} fullWidth label="Apellido" margin="normal" error={!!errors.apellido} helperText={errors.apellido?.message} />
                    )}
                />

                {/* DNI */}
                <Controller
                    name="dni"
                    control={control}
                    rules={{ required: "DNI es obligatorio" }}
                    render={({ field }) => (
                        <TextField {...field} fullWidth label="DNI" margin="normal" error={!!errors.dni} helperText={errors.dni?.message} />
                    )}
                />

                {/* Teléfono */}
                <Controller
                    name="telefono"
                    control={control}
                    render={({ field }) => (
                        <TextField {...field} fullWidth label="Teléfono" margin="normal" />
                    )}
                />

                {/* Dirección */}
                <Controller
                    name="direccion"
                    control={control}
                    render={({ field }) => (
                        <TextField {...field} fullWidth label="Dirección" margin="normal" />
                    )}
                />

                {/* Correo */}
                <Controller
                    name="correo"
                    control={control}
                    rules={{ required: "Correo electrónico es obligatorio", pattern: { value: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/, message: "Correo inválido" } }}
                    render={({ field }) => (
                        <TextField {...field} fullWidth label="Correo Electrónico" margin="normal" error={!!errors.correo} helperText={errors.correo?.message} />
                    )}
                />

                {/* Contraseña */}
                <Controller
                    name="password"
                    control={control}
                    rules={{ required: "Contraseña obligatoria" }}
                    render={({ field }) => (
                        <TextField {...field} fullWidth label="Contraseña" type="password" margin="normal" error={!!errors.password} helperText={errors.password?.message} />
                    )}
                />

                {/* Botones */}
                <Button type="submit" variant="contained" fullWidth sx={{ mt: 3, bgcolor: "#F55F1D", "&:hover": { bgcolor: "#d14e19" } }}>
                    Añadir nuevo cliente
                </Button>
                <Button
                    fullWidth sx={{ color: "text.secondary", mt: 1 }}
                    onClick={() => window.history.back()}
                >
                    No añadir cliente
                </Button>
            </Box>
        </Paper>
    );
};

export default NewClient;
