import './App.css'
import OurServices from './modules/core/components/OurServices/OurServices'
import AboutUs from './modules/core/components/About/AboutUs'
import ContactForm from './modules/core/components/Form/ContactForm'
import Login from './modules/core/components/Login-Form/Login'


function App() {


  return (
    <>
      <section className='max-w-[1200px] m-auto'>
        <OurServices />
        <AboutUs />
        <ContactForm />
      </section>
    </>
  )
} 

export default App
