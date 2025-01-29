import React, { useState, useEffect } from "react";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";

const columns = [
    { id: "factura", label: "Factura N°", minWidth: 150 },
    { id: "cliente", label: "Clientes", minWidth: 200 },
    { id: "estado", label: "Estado", minWidth: 100 },
    { id: "comentario", label: "Comentario", minWidth: 300 },
    {
        id: "precio",
        label: "Precio",
        minWidth: 100,
        align: "right",
        format: (value) => `$${value.toFixed(2)}`,
    },
    { id: "acciones", label: "Acciones", minWidth: 150 },
];

export default function AdminTable() {
    const [rows, setRows] = useState([]); // Almacenará los datos del backend
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    // Llamada al backend
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("/api/facturas"); // Cambia la URL por la correcta
                const data = await response.json();
                setRows(data); // Supone que el backend devuelve un arreglo con las facturas
            } catch (error) {
                setRows([
                    {
                        factura: "001",
                        cliente: "No Country",
                        estado: "En reparación",
                        comentario: "Lavo la pc con agua y shampoo",
                        precio: "100000$",
                        acciones: "asdasd",
                    },

                ]);
                console.error("Error al obtener los datos:", error);
            }
        };

        fetchData();
    }, []);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    return (
        <Paper sx={{ width: "100%", overflow: "hidden", background: "transparent" }}>
            <TableContainer sx={{ maxHeight: 440 }}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    align={column.align}
                                    style={{ minWidth: column.minWidth }}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((row) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.factura}>
                                        {columns.map((column) => {
                                            const value = row[column.id];
                                            return (
                                                <TableCell key={column.id} align={column.align}>
                                                    {column.id === "acciones" ? (
                                                        <div>
                                                            <button
                                                                onClick={() => console.log(row.factura)}
                                                                type="submit"
                                                                className="mr-3 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
                                                            >
                                                                Editar
                                                            </button>
                                                            <button
                                                                onClick={() => console.log("eliminando: " + row.factura)}
                                                                type="submit"
                                                                className="bg-[red] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
                                                            >
                                                                Eliminar
                                                            </button>
                                                        </div>
                                                    ) : column.format && typeof value === "number" ? (
                                                        column.format(value)
                                                    ) : (
                                                        value
                                                    )}
                                                </TableCell>
                                            );
                                        })}
                                    </TableRow>
                                );
                            })}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={rows.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </Paper>
    );
}
