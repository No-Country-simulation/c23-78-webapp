import { useState } from "react";

export const useFilterMovements = () => {
    const [filters, setFilters] = useState({
        orderNumber: "",
        technicianName: "",
        date: "",
        action: ""
    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFilters((prevFilters) => ({
            ...prevFilters,
            [name]: value
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("Filtros aplicados:", filters);
    };

    return { filters, handleChange, handleSubmit };
};

