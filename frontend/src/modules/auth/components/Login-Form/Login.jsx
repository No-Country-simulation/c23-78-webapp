import Refresh_Token from "../../../test/refresh_token";
import useGetLoginForm from "../../hooks/useGetLoginForm";

const Login = () => {
  const { register, handleSubmit, onSubmit, errors } = useGetLoginForm({
    email: "",
    password: "",
  });

  return (

    <div className="min-h-screen flex flex-col items-center justify-center">
      <Refresh_Token />
      <form
        className="bg-white p-10 rounded-lg shadow-md w-[580px] h-[425px] border border-black"
        onSubmit={handleSubmit(onSubmit)}
      >
        <div className="mb-3">
          <h2 className="mb-1 text-3xl">Iniciar Sesión</h2>
          <p>Ingrese sus datos para acceder a la cuenta</p>
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="correo" className="text-sm font-bold text-gray-700">
            Correo electrónico
          </label>
          <input
            type="email"
            id="correo"
            className="border border-black rounded-lg p-1 mt-3"
            placeholder="Ingresa tu correo"
            {...register("email", { required: "Es necesario ingresar un correo electrónico válido" })}
          />
          {errors.email && (
            <span className="text-red-500 text-sm">{errors.email.message}</span>
          )}
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="password" className="text-sm font-bold text-gray-700">
            Contraseña
          </label>
          <input
            type="password"
            id="password"
            className="border border-black rounded-lg p-1 mt-3"
            placeholder="Ingresa tu contraseña"
            {...register("password", { required: "Es necesario ingresar una contraseña" })}
          />
          {errors.password && (
            <span className="text-red-500 text-sm">{errors.password.message}</span>
          )}
        </div>

        <button
          type="submit"
          className="bg-[#020617] text-white p-2 rounded-lg w-full hover:bg-[#0f172a] transition duration-300"
        >
          Iniciar Sesión
        </button>
        <a className="hover:underline" href="/">
          <p className="flex justify-center mt-5">Volver a la página principal</p>
        </a>
      </form>
    </div>
  );
};

export default Login;

