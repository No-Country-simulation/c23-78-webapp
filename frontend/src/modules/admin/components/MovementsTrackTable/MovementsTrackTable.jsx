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
    { id: "modelo", label: "Modelo Dispositivo", minWidth: 150 },
    { id: "idMovimiento", label: "ID Movimiento", minWidth: 150 },
    { id: "numOrden", label: "N° Orden", minWidth: 150 },
    { id: "nombreTecnico", label: "Nombre Técnico", minWidth: 200 },
    { id: "fechaMovimiento", label: "Fecha Movimiento", minWidth: 200 },
    { id: "descripcion", label: "Descripción", minWidth: 300 },
    { id: "estado", label: "Estado", minWidth: 150 },
];

export default function MovementsTrackTable() {
    const [rows, setRows] = useState([]);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("/api/movimientos"); // llamada a la API
                const data = await response.json();
                setRows(data);
            } catch (error) {
                setRows([
                    {
                        modelo: "Desconocido",
                        idMovimiento: "0000",
                        numOrden: "N/A",
                        nombreTecnico: "No asignado",
                        fechaMovimiento: "00/00/0000",
                        descripcion: "No disponible",
                        estado: "Pendiente",
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
        <Paper sx={{ width: "100%", overflow: "hidden", background: "" }}>
            <TableContainer sx={{ maxHeight: 440 }}>
                <Table stickyHeader aria-label="sticky table" >
                    <TableHead >
                        <TableRow >
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
                    <TableBody >
                        {rows
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((row, index) => (
                                <TableRow hover role="checkbox" tabIndex={-1} key={index}>
                                    {columns.map((column) => {
                                        const value = row[column.id];
                                        return (
                                            <TableCell key={column.id} align={column.align}>
                                                {value}
                                            </TableCell>
                                        );
                                    })}
                                </TableRow>
                            ))}
                    </TableBody>
                </Table>
            </TableContainer >
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
