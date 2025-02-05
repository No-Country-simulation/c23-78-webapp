import React, { useState } from "react";
import { PackageSearch, ChevronDown } from 'lucide-react';
import logo from "../../../../assets/logoDark.png";

export const NavBar = () => {

  const [isServicesOpen, setIsServicesOpen] = useState(false);

  return (
    <header className="text-white bg-black body-font">
      <div className="container mx-auto flex flex-wrap p-5 flex-col md:flex-row items-center">
        <a
          className="flex title-font font-medium items-center text-white mb-4 md:mb-0 cursor-pointer"
          href="/"
        >
          <img
            src={logo}
            alt="trackmyfix"
            className="h-12 w-auto object-contain"
          />
        </a>
        <nav className="md:ml-auto flex flex-wrap items-center text-base justify-center">
          <div className="relative">

            <button onClick={() => setIsServicesOpen(!isServicesOpen)} className="mr-5 hover:text-amber-600 cursor-pointer inline-flex items-center">
              Services
              <ChevronDown className="ml-1" />
            </button>

            {isServicesOpen && (
              <div className="absolute left-0 mt-2 w-48 bg-white text-gray-800 shadow-lg rounded-md p-[5px]">
                <a href="#services" className="block px-4 py-2 hover:bg-gray-100">
                  Reparación de laptops
                </a>
                <a href="#services" className="block px-4 py-2 hover:bg-gray-100">
                  Reparación de celulares
                </a>
                <a href="#services" className="block px-4 py-2 hover:bg-gray-100">
                  Mantenimiento preventivo
                </a>
              </div>
            )}
          </div>
          <a className="mr-5 hover:text-amber-600 cursor-pointer" href="#about-us">About us</a>
          <a className="mr-5 hover:text-amber-600 cursor-pointer" href="#contact">Contact</a>
        </nav>
        {/* <button className="inline-flex items-center bg-gray-800 border-0 py-1 px-3 focus:outline-none hover:bg-gray-700 rounded text-base mt-4 md:mt-0">
          Login
          <svg
            fill="none"
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            className="w-4 h-4 ml-1"
            viewBox="0 0 24 24"
          >
            <path d="M5 12h14M12 5l7 7-7 7"></path>
          </svg>
        </button> */}
      </div>
    </header>
  );
};

export default NavBar;