const ServiceCard = ({ icon, title, description }) => {
    return (
      <div className="h-[320px] w-[320px] rounded-3xl bg-white p-6 shadow-sm border">
        <img src={icon} alt={`${title} Icon`} className="bg-[#F55F1D] p-6 rounded-lg" />
        <div className="mt-6">
          <h2 className="font-bold text-lg">{title}</h2>
          <p className="text-[#424242] text-base font-light mt-3">{description}</p>
        </div>
      </div>
    );
  };
  
  export default ServiceCard;
  