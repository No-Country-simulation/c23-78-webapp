const Achievements = () => {
    return (
      <div className="w-full h-auto">
        <div className="w-full bg-gray-100 flex flex-col items-center justify-center py-10">
          <section className="bg-background p-8 w-full flex justify-center items-center">
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8 text-center">
              <div className="flex flex-col items-center">
                <img aria-hidden="true" alt="document-icon" src="https://openui.fly.dev/openui/48x48.svg?text=📝" />
                <h3 className="text-gray-600">Trabajos realizados</h3>
                <p className="text-2xl md:text-3xl font-bold text-[#F55F1D]">300 +</p>
              </div>
              <div className="flex flex-col items-center">
                <img aria-hidden="true" alt="star-icon" src="https://openui.fly.dev/openui/48x48.svg?text=⭐" />
                <h3 className="text-gray-600">Satisfacción de clientes</h3>
                <p className="text-2xl md:text-3xl font-bold text-[#F55F1D]">80 %</p>
              </div>
              <div className="flex flex-col items-center">
                <img aria-hidden="true" alt="money-icon" src="https://openui.fly.dev/openui/48x48.svg?text=💰" />
                <h3 className="text-gray-600">Facturación</h3>
                <p className="text-2xl md:text-3xl font-bold text-[#F55F1D]">$30 K</p>
              </div>
              <div className="flex flex-col items-center">
                <img aria-hidden="true" alt="calendar-icon" src="https://openui.fly.dev/openui/48x48.svg?text=📅" />
                <h3 className="text-gray-600">Experiencia</h3>
                <p className="text-2xl md:text-3xl font-bold text-[#F55F1D]">2 Años</p>
              </div>
            </div>
          </section>
        </div>
      </div>
    );
  };
  
  export default Achievements;
  