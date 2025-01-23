const Login = () => {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center">
      <form className="bg-white p-10 rounded-lg shadow-md w-[580px] h-[425px] border border-black">
        <div className="mb-3">
          <h2 className="mb-1 text-3xl ">Iniciar Sesión</h2>
          <p>Ingrese sus datos para acceder a la cuenta</p>
        </div>
        {/* estoy probando algo */}
        <div className="flex flex-col mb-6">
          <label htmlFor="telefono" className="text-sm font-bold text-gray-700">Correo electrónico</label>
          <input
            type="tel"
            id="correo"
            className="border border-black rounded-lg p-1 mt-3"
            placeholder=""
          />
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="correo" className="text-sm font-bold text-gray-700">Contraseña</label>
          <input
            type="password"
            id="password"
            className="border border-black rounded-lg p-1 mt-3"
            placeholder=""
          />
        </div>

        <button
          type="submit"
          className="bg-[#020617] text-white p-2  rounded-lg w-full hover:bg-[#0f172a] transition duration-300">Iniciar Sesión</button>
        <a className="hover:underline" href="/">
          <p className="flex justify-center mt-5">Volver a la pagina principal</p>
        </a>
      </form>
    </div>
  )
}

export default Login
