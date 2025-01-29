import React from 'react'
import { ChevronLeft, PackageX } from 'lucide-react';


export const DeviceNotFound = () => {
  return (
    <section className='text-gray-800 body-font'>
        <div className='container px-5 py-24 mx-auto max-w-[700px]'>
            <button className='inline-flex text-gray-800 border-0  mb-12  focus:outline-none hover:text-gray-500 rounded'>
                <ChevronLeft /> Volver
            </button>
            <div className='flex flex-col items-center'>
                <PackageX className='w-20 h-20 mb-7' />
                <h3 className='mb-3 font-semibold'>No se encontraron resultados para el equipo <span className='text-red-600'>xxxxxxxxx</span></h3>
                <p className='text-center'>Es posible que el número no corresponda a un equipo. Por favor, ingresa el número que te enviamos por e-mail para poder realizar el seguimiento de tu envío.</p>
            </div>
        </div>
    </section>
  )
}
