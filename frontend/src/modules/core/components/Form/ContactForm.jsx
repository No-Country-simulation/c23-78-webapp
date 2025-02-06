import { useForm } from "react-hook-form";


const ContactForm = () => {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center p-6">
      <h2 className="text-3xl font-bold text-[#F55F1D] mb-6">CONTÁCTENOS</h2>
      <form className="bg-white p-10 rounded-lg shadow-md w-full max-w-2xl border border-black ">

        <div className="flex flex-col md:flex-row gap-4 mb-6">
          <div className="flex flex-col w-full">
            <label htmlFor="nombre" className="text-sm font-bold text-gray-700"></label>
            <input
              type="text"
              id="nombre"
              className="border border-black rounded-lg p-3 mt-1"
              placeholder="Nombre"
            />
          </div>
          <div className="flex flex-col w-full">
            <label htmlFor="apellido" className="text-sm font-bold text-gray-700"></label>
            <input
              type="text"
              id="apellido"
              className="border border-black rounded-lg p-3 mt-1"
              placeholder="Apellido"
            />
          </div>
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="correo" className="text-sm font-bold text-gray-700"> </label>
          <input
            type="email"
            id="correo"
            className="border border-black rounded-lg p-3 mt-1"
            placeholder="ejemplo@correo.com"
          />
        </div>

        <div className="flex flex-col mb-6">
          <label htmlFor="telefono" className="text-sm font-bold text-gray-700"></label>
          <input
            type="tel"
            id="telefono"
            className="border border-black rounded-lg p-3 mt-1"
            placeholder="Teléfono"
          />
        </div>

        <div className="flex flex-col mb-8">
          <label htmlFor="mensaje" className="text-sm font-bold text-gray-700"></label>
          <textarea
            id="mensaje"
            rows="6"
            className="border border-black rounded-lg p-3 mt-1"
            placeholder="Escribe tu mensaje aquí..."
          ></textarea>
        </div>

        <button
          type="submit"
          className="bg-[#F55F1D] text-white py-3 px-4 rounded-lg w-full hover:bg-[#d14e19] transition duration-300"> Enviar Mensaje </button>
      </form>
    </div>
  );
};

export default ContactForm;