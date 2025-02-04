/**
 * Función para actualizar la ruta de la página.
 * @param {string} searchText - Texto a buscar en la barra de búsqueda.
 * @param {string} pathName - Nombre del parámetro de la ruta.
 * @returns {void}
 */

import { useEffect } from "react";

export default function useUpdatePath(searchText, pathName = "search") {
    useEffect(() => {
        if (!searchText) {
            const newPath = window.location.pathname;
            window.history.pushState({}, "", newPath);
        } else {
            const newPath = `?${pathName}=${encodeURIComponent(searchText)}`;
            window.history.pushState({}, "", newPath);
        }
    }, [searchText]);
}
