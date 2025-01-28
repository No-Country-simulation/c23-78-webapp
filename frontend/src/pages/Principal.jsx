import Login from "../modules/core/components/Login-Form/Login"; 
import OurServices from "../modules/core/components/OurServices/OurServices";
import AboutUs from "../modules/core/components/About/AboutUs";
import ContactForm from "../modules/core/components/Form/ContactForm";
import { Link } from "react-router-dom";
import { Footer, NavBar, TestimonialContainer, DeviceNotFound, TrackingTimeLine } from "../modules/core/components";
import { OrderForm } from "../modules/core/components/OrderForm/OrderForm";


const Principal = () => {
  return (
    <div>
      <NavBar />
      <Link to="/auth/login">Login</Link>
      <section className='max-w-[1200px] m-auto'>
        <OurServices />
        <AboutUs />
        <TestimonialContainer />
        <ContactForm />
        <DeviceNotFound />
        <OrderForm />
        <TrackingTimeLine />
      </section>
      <Footer />
    </div>
  )
}

export default Principal;