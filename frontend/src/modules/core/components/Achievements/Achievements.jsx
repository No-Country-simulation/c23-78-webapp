const Achievements = () => {
    return (
        <>

            <div className=" w-full h-96">
                <div className="w-full h-24 bg-gray-100 flex justify-center items-center">
                    <h2 className="text-3xl font-bold text-[#F55F1D]">TESTIMONIOS DE NUESTROS CLIENTES</h2>
                </div>
                <div className='w-full h-44 bg-gray-100 flex flex-col items-center justify-center'>

                    <section className="bg-background p-8 h-[48px] w-full flex justify-center items-center">
                        <div className="grid grid-cols-2 md:grid-cols-4 gap-8 text-center">
                            <div className="flex flex-col items-center">
                                <img aria-hidden="true" alt="document-icon" src="https://openui.fly.dev/openui/48x48.svg?text=📝" />
                                <h3 className="text-muted-foreground">Trabajos realizados</h3>
                                <p className="text-3xl font-bold text-accent">300 +</p>
                            </div>
                            <div className="flex flex-col items-center">
                                <img aria-hidden="true" alt="star-icon" src="https://openui.fly.dev/openui/48x48.svg?text=⭐" />
                                <h3 className="text-muted-foreground">Satisfacción de clientes</h3>
                                <p className="text-3xl font-bold text-accent">80 %</p>
                            </div>
                            <div className="flex flex-col items-center">
                                <img aria-hidden="true" alt="money-icon" src="https://openui.fly.dev/openui/48x48.svg?text=💰" />
                                <h3 className="text-muted-foreground">Facturación</h3>
                                <p className="text-3xl font-bold text-accent">$30 K</p>
                            </div>
                            <div className="flex flex-col items-center">
                                <img aria-hidden="true" alt="calendar-icon" src="https://openui.fly.dev/openui/48x48.svg?text=📅" />
                                <h3 className="text-muted-foreground">Experiencia</h3>
                                <p className="text-3xl font-bold text-accent">2 Años</p>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </>
    )
}

export default Achievements;