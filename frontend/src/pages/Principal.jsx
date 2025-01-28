import OurServices from "../modules/core/components/OurServices/OurServices";
import AboutUs from "../modules/core/components/About/AboutUs";
import ContactForm from "../modules/core/components/Form/ContactForm";
import { Link } from "react-router-dom";
import { Footer, NavBar, TestimonialContainer, DeviceNotFound, TrackingTimeLine } from "../modules/core/components";
import { OrderForm } from "../modules/core/components/OrderForm/OrderForm";

import { NavBar } from "../modules/core/components/navbar/NavBar";
import { Footer, TestimonialCard } from "../modules/core/components";
import Achievements from "../modules/core/components/Achievements/Achievements";
import SearchForm from "../modules/core/components/SearchForm/SearchForm";


const Principal = () => {
  return (
    <div>
      <NavBar />
      <SearchForm />
      <OurServices />
      <AboutUs />
      <Achievements />
      <div className="flex items-center justify-center flex-col  h-[1000px] w-full mt-10 mb-10">
        <h2 className="text-3xl font-bold text-[#F55F1D]">TESTIMONIOS DE NUESTROS CLIENTES</h2>
        <TestimonialCard />
      </div>
      <ContactForm />
      <DeviceNotFound />
      <OrderForm />
      <TrackingTimeLine />
      <Footer />
      
    </div>
  )
}

export default Principal;