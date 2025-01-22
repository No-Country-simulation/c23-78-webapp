import Login from "../modules/core/components/Login-Form/Login"; 
import OurServices from "../modules/core/components/OurServices/OurServices";
import AboutUs from "../modules/core/components/About/AboutUs";
import ContactForm from "../modules/core/components/Form/ContactForm";
import { Link } from "react-router-dom";

const Principal = () => {
  return (
    <div>
      <Link to="/auth/login">Login</Link>
      <section className='max-w-[1200px] m-auto'>
        <Login />
        <OurServices />
        <AboutUs />
        <ContactForm />
      </section>
    </div>
  )
}

export default Principal;