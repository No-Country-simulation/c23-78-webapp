import { saveTokens } from "../libs/tokenStorage";

/**
 * Hook personalizado para manejar el formulario de inicio de sesión.
 * @param {Object} defaultValues - Valores predeterminados para el formulario.
 * @returns {Object} - Métodos y estados del formulario.
 */
const useGetLoginForm = (defaultValues) => {
    const {
    } = useForm({
    });

    /**
     * Función que se ejecuta al enviar el formulario.
     * @param {Object} data - Contiene los valores ingresados en el formulario.
     */
    const onSubmit = (data) => {
        (async () => {
            try {

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
    };
};

