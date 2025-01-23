import React from "react";
import { testimonials } from "./testimonials";

export const TestimonialCard = () => {
  return (
    <>
      {testimonials.map((testimonial) => (
        <div key={testimonial.id} className="p-4 md:w-1/3 w-full mt-6">
          <div className="h-full bg-gray-100 p-10 rounded shadow-lg">
            <p className="leading-relaxed mb-6">
              { testimonial.text }
            </p>
            <a className="inline-flex items-center">
              <img
                alt="testimonial"
                src={ testimonial.profile }
                className="w-12 h-12 rounded-full flex-shrink-0 object-cover object-center"
              ></img>
              <span className="flex-grow flex flex-col pl-4">
                <span className="title-font font-medium text-gray-900">
                  { testimonial.name }
                </span>
                <span className="text-gray-500 text-sm">{ testimonial.role }</span>
              </span>
            </a>
          </div>
        </div>
      ))}
    </>
  );
};

//export default TestimonialCard;