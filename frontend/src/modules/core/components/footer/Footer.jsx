import React from "react";
import { PackageSearch, Facebook, Twitter, Instagram, Linkedin } from 'lucide-react';

export const Footer = () => {
  return (
    <footer className="text-white body-font bg-black">
      <div className="container px-5 py-24 mx-auto flex md:items-center lg:items-start md:flex-row md:flex-nowrap flex-wrap flex-col">
        <div className="w-64 flex-shrink-0 md:mx-0 mx-auto text-center md:text-left">
          <a className="flex title-font font-medium items-center md:justify-start justify-center text-orange-600">
            <PackageSearch />
            <span className="ml-3 text-xl text-orange-600">TrackMyFix</span>
          </a>
          <p className="mt-2 text-sm text-white">
            Servicio técnico de calidad y oportuno, confía en nuestros servicios.
          </p>
          <span className="inline-flex sm:ml-auto sm:mt-6 justify-center sm:justify-start">
            <a className="text-gray-500 hover:text-orange-600 cursor-pointer">
              <Facebook />
            </a>
            <a className="ml-3 text-gray-500 hover:text-orange-600 cursor-pointer">
              <Twitter />
            </a>
            <a className="ml-3 text-gray-500 hover:text-orange-600 cursor-pointer">
              <Instagram />
            </a>
            <a className="ml-3 text-gray-500 hover:text-orange-600 cursor-pointer">
              <Linkedin />
            </a>
          </span>
        </div>
        <div className="flex-grow flex flex-wrap md:pl-20 -mb-10 md:mt-0 mt-10 md:text-left text-center">
          <div className="lg:w-1/2 md:w-1/2 w-full px-4">
            <h2 className="title-font font-medium text-white tracking-widest text-sm mb-3">
              Accesos rápidos
            </h2>
            <nav className="list-none mb-10">
              <li>
                <a className="text-gray-600 hover:text-gray-200 cursor-pointer">Buscar equipo</a>
              </li>
              <li>
                <a className="text-gray-600 hover:text-gray-200 cursor-pointer">Servicios</a>
              </li>
              <li>
                <a className="text-gray-600 hover:text-gray-200 cursor-pointer">Sobre nosotros</a>
              </li>
              <li>
                <a className="text-gray-600 hover:text-gray-200 cursor-pointer">Contacto</a>
              </li>
            </nav>
          </div>
          
          <div className="lg:w-1/2 md:w-1/2 w-full px-4">
            <h2 className="title-font font-medium text-white tracking-widest text-sm mb-3">
              Dirección
            </h2>
            <h3 className="text-gray-600">
              Local #202 Centro Comercial San Marino.
            </h3>
            <h3 className="text-gray-600">
              Medellin - Colombia.
            </h3>
          </div>
        </div>
      </div>
      <div className="bg-black text-white">
        <div className="container mx-auto py-4 px-5 flex flex-wrap flex-col sm:flex-row">
          <p className="text-gray-500 text-sm text-center sm:text-left">
            Copyrigth © 2025 — TrackMyFix
          </p>
          
        </div>
      </div>
    </footer>
  );
};


//export default Footer;