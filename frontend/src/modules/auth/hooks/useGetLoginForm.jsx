import { useForm } from "react-hook-form"; // Importamos el hook useForm de react-hook-form
import authLoginUser from "../services/authLoginUser"; // Importamos la función que maneja el login
import { saveTokens } from "../libs/tokenStorage";

/**
 * Hook personalizado para manejar el formulario de inicio de sesión.
 * @param {Object} defaultValues - Valores predeterminados para el formulario.
 * @returns {Object} - Métodos y estados del formulario.
 */
const useGetLoginForm = (defaultValues) => {
    const {
        register, // Se usa para registrar los inputs en react-hook-form
        handleSubmit, // Maneja el envío del formulario
        formState: { errors }, // Contiene los errores de validación
        reset, // Función para reiniciar el formulario
        watch, // Permite observar cambios en los inputs
    } = useForm({
        defaultValues, // Se establecen los valores iniciales del formulario
    });

    /**
     * Función que se ejecuta al enviar el formulario.
     * @param {Object} data - Contiene los valores ingresados en el formulario.
     */
    const onSubmit = (data) => {
        (async () => {
            try {
                console.log("Enviando datos:", data);

                // Llamamos a la función de autenticación con los datos ingresados
                const result = await authLoginUser(data.email, data.password);
                saveTokens(result);
                // Aquí podríamos manejar la respuesta del login, como guardar tokens o redirigir
            } catch (error) {
                console.error("Error en el inicio de sesión:", error);
            }
        })();
    };

    return {
        register, // Conectar los inputs con react-hook-form
        handleSubmit, // Función para manejar el envío del formulario
        onSubmit, // Función que se ejecuta cuando el usuario envía el formulario
        errors, // Objeto que contiene errores de validación
        reset, // Función para reiniciar el formulario
        watch, // Permite observar cambios en los inputs en tiempo real
    };
};

export default useGetLoginForm; // Exportamos el hook para usarlo en otros componentes
