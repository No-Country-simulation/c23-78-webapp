import { useForm } from "react-hook-form";
import { useAuth } from "../components/AuthProvider";

const useGetLoginForm = (defaultValues) => {
  const { register, handleSubmit, formState: { errors } } = useForm({ defaultValues });
  const auth = useAuth();
  const { VITE_BACKEND_URL } = import.meta.env;

  const onSubmit = async (data) => {
    try {
      const response = await fetch(`${VITE_BACKEND_URL}/user/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      });

      // Verifica si la respuesta está vacía
      if (!response.ok) {
        throw new Error("Credenciales incorrectas");
      }

      // Verifica si la respuesta tiene contenido antes de convertir a JSON
      const text = await response.text();
      if (!text) {
        throw new Error("Respuesta vacía del servidor");
      }

      const result = JSON.parse(text);
      auth.login(result.token);
    } catch (error) {
      console.error("Error en la autenticación:", error.message);
      alert(error.message);
    }
  };

  return { register, handleSubmit, onSubmit, errors };
};

export default useGetLoginForm;
