import { useForm } from "react-hook-form";

const ContactForm = () => {
  return (
    <div id="contact" className="min-h-screen flex flex-col items-center justify-center p-4 sm:p-6">
      <h2 className="text-2xl sm:text-3xl font-bold text-[#F55F1D] mb-4 sm:mb-6">CONTÁCTENOS</h2>
      <form className="bg-white p-6 sm:p-10 rounded-lg shadow-md w-full max-w-lg sm:max-w-2xl border border-black">
        <div className="flex flex-col sm:flex-row gap-4 mb-4 sm:mb-6">
          <div className="flex flex-col w-full">
            <input
              type="text"
              id="nombre"
              className="border border-black rounded-lg p-2 sm:p-3 mt-1 text-sm sm:text-base"
              placeholder="Nombre"
            />
          </div>
          <div className="flex flex-col w-full">
            <input
              type="text"
              id="apellido"
              className="border border-black rounded-lg p-2 sm:p-3 mt-1 text-sm sm:text-base"
              placeholder="Apellido"
            />
          </div>
        </div>

        <div className="flex flex-col mb-4 sm:mb-6">
          <input
            type="email"
            id="correo"
            className="border border-black rounded-lg p-2 sm:p-3 mt-1 text-sm sm:text-base"
            placeholder="ejemplo@correo.com"
          />
        </div>

        <div className="flex flex-col mb-4 sm:mb-6">
          <input
            type="tel"
            id="telefono"
            className="border border-black rounded-lg p-2 sm:p-3 mt-1 text-sm sm:text-base"
            placeholder="Teléfono"
          />
        </div>

        <div className="flex flex-col mb-6">
          <textarea
            id="mensaje"
            rows="4"
            className="border border-black rounded-lg p-2 sm:p-3 mt-1 text-sm sm:text-base"
            placeholder="Escribe tu mensaje aquí..."
          ></textarea>
        </div>

        <button
          type="submit"
          className="bg-[#F55F1D] text-white py-2 sm:py-3 px-4 rounded-lg w-full hover:bg-[#d14e19] transition duration-300 text-sm sm:text-base"
        >
          Enviar Mensaje
        </button>
      </form>
    </div>
  );
};

export default ContactForm;