import OurServices from "../modules/core/components/OurServices/OurServices";
import AboutUs from "../modules/core/components/About/AboutUs";
import ContactForm from "../modules/core/components/Form/ContactForm";
import { Link } from "react-router-dom";
import { Footer, NavBar, TestimonialContainer, DeviceNotFound, TrackingTimeLine } from "../modules/core/components";
import { OrderForm } from "../modules/core/components/OrderForm/OrderForm";
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
      <TestimonialContainer />
      <ContactForm />
      <Footer />
      
    </div>
  )
}

export default Principal;