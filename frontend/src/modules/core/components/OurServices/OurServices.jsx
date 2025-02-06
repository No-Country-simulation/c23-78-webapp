import icons from '../../../../assets/Icons';
import ServiceCard from './ServiceCard';  

const services = [
    {
      icon: icons.ComputerIcon,
      title: "Reparación de computadoras",
      description: "Solucionamos problemas de hardware y software en computadoras de escritorio y laptops."
    },
    {
      icon: icons.PhoneIcon,
      title: "Servicio de celulares",
      description: "Reparamos pantallas dañadas, baterías defectuosas y otros componentes de smartphones y tablets."
    },
    {
      icon: icons.PrinterIcon,
      title: "Mantenimiento de impresoras",
      description: "Ofrecemos limpieza profunda, reparación profesional y configuración completa de todo tipo de impresoras."
    }
  ];

  const OurServices = () => {
    return (
      <section className="p-10 w-auto mb-10">
        <div className="mb-10 flex flex-col justify-center align-center w-[70%] m-auto text-center">
          <h2 className="text-2xl font-bold text-[#F55F1D]">NUESTROS SERVICIOS</h2>
          <p className="font-bold mt-3 text-xl	">
            Confía en nosotros para devolverle la vida a tus dispositivos. ¡Tu satisfacción es nuestra prioridad!
          </p>
        </div>
        
        <div className="flex justify-center gap-10">
          {services.map((service, index) => (
            <ServiceCard
              key={index}
              icon={service.icon}
              title={service.title}
              description={service.description}
            />
          ))}
        </div>
      </section>
    );
  };
  
  export default OurServices;
