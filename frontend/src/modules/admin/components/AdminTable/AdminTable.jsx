import React, { useState, useEffect } from "react";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import getWorksOrders from "../../services/getWorksOrders";

const columns = [
    { id: "orden", label: "Orden N°", minWidth: 150 },
    { id: "cliente", label: "Cliente", minWidth: 200 },
    { id: "estado", label: "Estado Orden", minWidth: 150 },
    { id: "dispositivo", label: "Dispositivo", minWidth: 200 },
    { id: "estadoDispositivo", label: "Estado Dispositivo", minWidth: 150 },
    { id: "comentario", label: "Observaciones", minWidth: 200 },
    {
        id: "precioInicial",
        label: "Precio Inicial",
        minWidth: 120,
        align: "right",
        format: (value) => `$${value.toFixed(2)}`,
    },
    {
        id: "fechaActualizacion",
        label: "Última Actualización",
        minWidth: 200,
        align: "center",
        format: (value) => new Date(value).toLocaleString(),
    },
    { id: "acciones", label: "Acciones", minWidth: 150 },
];

const transformOrdersToRows = (data) => {
    if (!data.orders || !Array.isArray(data.orders)) {
        console.error("Error: 'orders' no es un array válido.");
        return [];
    }

    return data.orders.flatMap((order) =>
        order.devices.map((device) => ({
            orden: order.number, // Número de orden
            cliente: `${order.client.name} ${order.client.lastName}`, // Cliente con nombre y apellido
            estado: order.active ? "Activa" : "Cerrada", // Estado de la orden
            dispositivo: device.model, // Modelo del dispositivo
            estadoDispositivo: device.state, // Estado del dispositivo
            comentario: order.observations, // Observaciones de la orden
            precioInicial: `$${device.initialPrice.toFixed(2)}`, // Precio inicial con formato
            fechaActualizacion: new Date(order.updatedAt).toLocaleString(), // Última actualización
        }))
    );
};


export default function AdminTable() {
    const [rows, setRows] = useState([]); // Almacenará los datos del backend
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);


    useEffect(() => {
        const fetchData = async () => {
            const response = await getWorksOrders();
            console.log(response);
            const transformedRows = transformOrdersToRows(response);
            setRows(transformedRows);
        };
        fetchData();
    }, [setRows]);

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
                                                                onClick={() => console.log("editando" + row.orden)}
                                                                type="submit"
                                                                className="mr-3 bg-[#F55F1D] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
                                                            >
                                                                Editar
                                                            </button>
                                                            <button
                                                                onClick={() => console.log("eliminando: " + row.orden)}
                                                                type="submit"
                                                                className="bg-[red] text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
                                                            >
                                                                Eliminar
                                                            </button>
                                                            <button
                                                                onClick={() => console.log("viendo: " + row.orden)}
                                                                type="submit"
                                                                className="ml-3 bg-blue-600 text-white py-3 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full md:w-auto"
                                                            >
                                                                Ver
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