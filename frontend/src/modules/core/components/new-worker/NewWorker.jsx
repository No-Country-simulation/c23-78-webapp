import { Button } from "@mui/material";
import { useForm } from "react-hook-form";

const NewWorker = () => {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center p-6">
      <form className="bg-white p-10 rounded-lg shadow-md w-full max-w-2xl border border-black ">
        <h2 className="text-2xl font-bold  mb-6">Nuevo trabajador</h2>

        <div className="flex flex-col mb-6">
          <label htmlFor="nombre" className="text-sm font-bold"></label>
          <input
            type="text"
            id="nombre"
            className="border border-black rounded-lg p-3 mt-1"
            placeholder="Nombre"
          />
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="apellido" className="text-sm font-bold "></label>
          <input
            type="text"
            id="apellido"
            className="border border-black rounded-lg p-3 mt-1"
            placeholder="Apellido"
          />
        </div>

        <div className="flex flex-col md:flex-row gap-4 mb-6">
          <div className="flex flex-col w-full">
            <label htmlFor="dni" className="text-sm font-bold "></label>
            <input
              type="text"
              id="dni"
              className="border border-black rounded-lg p-3 mt-1"
              placeholder="DNI"
            />
          </div>
          <div className="flex flex-col w-full">
            <label htmlFor="telefono" className="text-sm font-bold "></label>
            <input
              type="tel"
              id="telefono"
              className="border border-black rounded-lg p-3 mt-1"
              placeholder="Telefono"
            />
          </div>
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="direccion" className="text-sm font-bold ">
            {" "}
          </label>
          <input
            type="text"
            id="direccion"
            className="border border-black rounded-lg p-3 mt-1"
            placeholder="Dirrecion"
          />
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="correo" className="text-sm font-bold ">
            {" "}
          </label>
          <input
            type="email"
            id="correo"
            className="border border-black rounded-lg p-3 mt-1"
            placeholder="Email"
          />
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="password" className="text-sm font-bold"></label>
          <input
            type="password"
            id="password"
            className="border border-black rounded-lg p-3 mt-1"
            placeholder="Contraseña"
          />
        </div>

        <Button
          type="submit"
          variant="contained"
          fullWidth
          sx={{
            mt: 3,
            mb: 1,
            bgcolor: "#f4511e",
            "&:hover": { bgcolor: "#e64a19" },
          }}
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
      </form>
    </div>
  );
};

export default NewWorker;
