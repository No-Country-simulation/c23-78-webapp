import { useForm } from "react-hook-form";
import authLoginUser from "../services/authLoginUser";
import { saveTokens } from "../libs/tokenStorage";

const useGetLoginForm = (defaultValues) => {
    const {
        register,
        handleSubmit,
        formState: { errors },
        reset,
        watch,
    } = useForm({
        defaultValues, // valores predeterminados
    });

    // Manejo del
    const onSubmit = (data) => {
        (async () => {
            try {
                console.log("enviando, data", data);
                const result = await authLoginUser(data.email, data.password);
                saveTokens(result);
            } catch (error) {
                console.error("Login failed:", error);
            }
        })();
    };

    return {
        register, // conectar los inputs
        handleSubmit, // manejo de informacion
        onSubmit, // submit del form
        errors, // catch para errores
        reset, // Caso reinicio
        watch, // Trigger 
    };
};

export default useGetLoginForm;
