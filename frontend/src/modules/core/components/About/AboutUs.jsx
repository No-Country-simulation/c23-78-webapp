import DiscussingPhoto from '../../../../assets/Image/DiscussingPhoto.png'

const AboutUs = () => {
    return (
        <div id="about-us" className="flex flex-col justify-center items-center text-center p-6 ">
            <div>
                <h2 className="text-3xl font-bold text-[#F55F1D]">SOBRE NOSOTROS</h2>
                <img className="mt-7" src={DiscussingPhoto} alt="Imagen de TrackMyFix" />
            </div>
            <div className='w-[80%]'>
                <p className="mt-4 text-lg">Nuestro objetivo es simplificar el proceso de reparación y mantenerte informado en cada paso.</p>
                <p className="mt-4 text-lg">En <span className="text-[#F55F1D]">TrackMyFix</span>, nos especializamos en ofrecer un servicio rápido, confiable y transparente para la reparación de tus dispositivos electrónicos.</p>
            </div>
        </div>
    );
};

export default AboutUs;
