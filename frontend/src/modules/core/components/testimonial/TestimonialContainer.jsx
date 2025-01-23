import React from "react";

import { TestimonialCard } from "./TestimonialCard";

const Testimonials = () => {
  return (
    <section className="text-gray-600 body-font">
      <div className="container px-5 py-24 mx-auto">
        <div className="container mx-auto flex flex-wrap">
          <div className="md:w-full">
            <h1 className="text-3xl font-medium title-font text-amber-600 mb-12 text-left">
              TESTIMONIOS
            </h1>
            <p className="text-2xl font-medium text-black max-w-96">
              Mira Que Dicen Nuestros Clientes Sobre Nuestros Servicios
            </p>
          </div>

          <div className="flex flex-wrap -m-4">
            <TestimonialCard />
          </div>
        </div>
      </div>
    </section>
  );
};

export default Testimonials;