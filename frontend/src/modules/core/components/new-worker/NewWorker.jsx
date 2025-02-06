import { Button } from "@mui/material";
import { useForm } from "react-hook-form";
import { useState } from "react";

const { VITE_BACKEND_URL } = import.meta.env

const NewWorker = () => {
  const { register, handleSubmit, formState: { errors } } = useForm();
  const [message, setMessage] = useState(null);

  const onSubmit = async (data) => {
    try {
      const response = await fetch(`${VITE_BACKEND_URL}/user/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: data.name,
          lastName: data.lastName,
          email: data.email,
          dni: parseInt(data.dni, 10),
          address: data.address,
          role: "ADMIN",
          phone: data.phone,
          password: data.password,
        }),
      });

      if (!response.ok) {
        throw new Error("Error al registrar el trabajador");
      }

      setMessage("¡Trabajador registrado con éxito!");
    } catch (error) {
      console.error(error);
      setMessage("Hubo un error al registrar el trabajador");
    }
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center p-6">
      <form 
        className="bg-white p-10 rounded-lg shadow-md w-full max-w-2xl border border-black"
        onSubmit={handleSubmit(onSubmit)}
      >
        <h2 className="text-2xl font-bold mb-6">Nuevo trabajador</h2>

        <div className="flex flex-col mb-6">
          <input 
            type="text" 
            {...register("name", { required: true })} 
            className="border border-black rounded-lg p-3 mt-1" 
            placeholder="Nombre" 
          />
          {errors.name && <span className="text-red-500 text-sm">Nombre requerido</span>}
        </div>

        <div className="flex flex-col mb-6">
          <input 
            type="text" 
            {...register("lastName", { required: true })} 
            className="border border-black rounded-lg p-3 mt-1" 
            placeholder="Apellido" 
          />
          {errors.lastName && <span className="text-red-500 text-sm">Apellido requerido</span>}
        </div>

        <div className="flex flex-col md:flex-row gap-4 mb-6">
          <div className="flex flex-col w-full">
            <input 
              type="text" 
              {...register("dni", { required: true })} 
              className="border border-black rounded-lg p-3 mt-1" 
              placeholder="DNI" 
            />
            {errors.dni && <span className="text-red-500 text-sm">DNI requerido</span>}
          </div>
          <div className="flex flex-col w-full">
            <input 
              type="tel" 
              {...register("phone", { required: true })} 
              className="border border-black rounded-lg p-3 mt-1" 
              placeholder="Teléfono" 
            />
            {errors.phone && <span className="text-red-500 text-sm">Teléfono requerido</span>}
          </div>
        </div>

        <div className="flex flex-col mb-6">
          <input 
            type="text" 
            {...register("address", { required: true })} 
            className="border border-black rounded-lg p-3 mt-1" 
            placeholder="Dirección" 
          />
          {errors.address && <span className="text-red-500 text-sm">Dirección requerida</span>}
        </div>

        <div className="flex flex-col mb-6">
          <input 
            type="email" 
            {...register("email", { required: true })} 
            className="border border-black rounded-lg p-3 mt-1" 
            placeholder="Email" 
          />
          {errors.email && <span className="text-red-500 text-sm">Email requerido</span>}
        </div>

        <div className="flex flex-col mb-6">
          <input 
            type="password" 
            {...register("password", { required: true })} 
            className="border border-black rounded-lg p-3 mt-1" 
            placeholder="Contraseña" 
          />
          {errors.password && <span className="text-red-500 text-sm">Contraseña requerida</span>}
        </div>

        <Button 
          type="submit" 
          variant="contained" 
          fullWidth 
          sx={{ mt: 3, mb: 1, bgcolor: "#f4511e", "&:hover": { bgcolor: "#e64a19" } }}
        >
          Añadir nuevo trabajador
        </Button>

        <Button 
          fullWidth 
          onClick={() => window.history.back()} 
          sx={{ color: "text.secondary", mt: 1 }}
        >
          No añadir trabajador
        </Button>

        {message && <p className="text-center text-sm mt-4">{message}</p>}
      </form>
    </div>
  );
};

export default NewWorker;
