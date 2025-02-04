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


/*

{
    "orders": [
        {
            "idOrder": 1,
            "number": "ORD-00001",
            "observations": "Observation1",
            "orderTotal": 33000,
            "client": {
                "role": "CLIENT",
                "name": "Client1",
                "lastName": "ClientLastName1",
                "dni": "94808001",
                "address": "calle client 1",
                "createdAt": "2025-02-04T15:59:39.628+00:00",
                "updatedAt": "2025-02-04T15:59:39.628+00:00"
            },
            "active": true,
            "createdAt": "2025-02-04T15:59:39.689+00:00",
            "updatedAt": "2025-02-04T15:59:39.765+00:00",
            "devices": [
                {
                    "idDevice": 1,
                    "model": "Dell OptiPlex 3080",
                    "serialNumber": "SN-00001",
                    "accessories": "Teclado y Mouse",
                    "initialPrice": 3000,
                    "finalPrice": 30000,
                    "clientDescription": "No enciende",
                    "technicalReport": "Fuente dañada",
                    "type": "COMPUTADORA_DE_ESCRITORIO",
                    "state": "RECIBIDO",
                    "createdAt": "2025-02-04T15:59:39.737+00:00",
                    "updatedAt": "2025-02-04T15:59:39.737+00:00"
                }
            ]
        },
        {
            "idOrder": 2,
            "number": "ORD-00002",
            "observations": "Observation2",
            "orderTotal": 22000,
            "client": {
                "role": "CLIENT",
                "name": "Client2",
                "lastName": "ClientLastName2",
                "dni": "94808002",
                "address": "calle client 2",
                "createdAt": "2025-02-04T15:59:39.630+00:00",
                "updatedAt": "2025-02-04T15:59:39.630+00:00"
            },
            "active": true,
            "createdAt": "2025-02-04T15:59:39.698+00:00",
            "updatedAt": "2025-02-04T15:59:39.766+00:00",
            "devices": [
                {
                    "idDevice": 2,
                    "model": "MacBook Pro 2020",
                    "serialNumber": "SN-00002",
                    "accessories": "Cargador original",
                    "initialPrice": 2000,
                    "finalPrice": 20000,
                    "clientDescription": "Pantalla con rayas",
                    "technicalReport": "Falla en GPU",
                    "type": "NOTEBOOK",
                    "state": "RECIBIDO",
                    "createdAt": "2025-02-04T15:59:39.740+00:00",
                    "updatedAt": "2025-02-04T15:59:39.740+00:00"
                }
            ]
        },
        {
            "idOrder": 3,
            "number": "ORD-00003",
            "observations": "Observation3",
            "orderTotal": 88000,
            "client": {
                "role": "CLIENT",
                "name": "Client3",
                "lastName": "ClientLastName3",
                "dni": "94808003",
                "address": "calle client 3",
                "createdAt": "2025-02-04T15:59:39.631+00:00",
                "updatedAt": "2025-02-04T15:59:39.631+00:00"
            },
            "active": true,
            "createdAt": "2025-02-04T15:59:39.699+00:00",
            "updatedAt": "2025-02-04T15:59:39.766+00:00",
            "devices": [
                {
                    "idDevice": 3,
                    "model": "HP Pavilion 15",
                    "serialNumber": "SN-00003",
                    "accessories": "Cargador genérico",
                    "initialPrice": 8000,
                    "finalPrice": 80000,
                    "clientDescription": "No carga",
                    "technicalReport": "Batería agotada",
                    "type": "NOTEBOOK",
                    "state": "EN_DIAGNOSTICO",
                    "createdAt": "2025-02-04T15:59:39.742+00:00",
                    "updatedAt": "2025-02-04T15:59:39.742+00:00"
                }
            ]
        },
        {
            "idOrder": 4,
            "number": "ORD-00004",
            "observations": "Observation4",
            "orderTotal": 99000,
            "client": {
                "role": "CLIENT",
                "name": "Client4",
                "lastName": "ClientLastName4",
                "dni": "94808004",
                "address": "calle client 4",
                "createdAt": "2025-02-04T15:59:39.632+00:00",
                "updatedAt": "2025-02-04T15:59:39.632+00:00"
            },
            "active": true,
            "createdAt": "2025-02-04T15:59:39.700+00:00",
            "updatedAt": "2025-02-04T15:59:39.766+00:00",
            "devices": [
                {
                    "idDevice": 4,
                    "model": "Samsung Galaxy S21",
                    "serialNumber": "SN-00004",
                    "accessories": "Sin accesorios",
                    "initialPrice": 9000,
                    "finalPrice": 90000,
                    "clientDescription": "No enciende",
                    "technicalReport": "Falla en la placa",
                    "type": "SMARTPHONE",
                    "state": "EN_DIAGNOSTICO",
                    "createdAt": "2025-02-04T15:59:39.743+00:00",
                    "updatedAt": "2025-02-04T15:59:39.743+00:00"
                }
            ]
        },
        {
            "idOrder": 5,
            "number": "ORD-00005",
            "observations": "Observation5",
            "orderTotal": 110000,
            "client": {
                "role": "CLIENT",
                "name": "Client5",
                "lastName": "ClientLastName5",
                "dni": "94808005",
                "address": "calle client 5",
                "createdAt": "2025-02-04T15:59:39.633+00:00",
                "updatedAt": "2025-02-04T15:59:39.633+00:00"
            },
            "active": true,
            "createdAt": "2025-02-04T15:59:39.701+00:00",
            "updatedAt": "2025-02-04T15:59:39.766+00:00",
            "devices": [
                {
                    "idDevice": 5,
                    "model": "Dell Inspiron 5000",
                    "serialNumber": "SN-00005",
                    "accessories": "Teclado y Mouse",
                    "initialPrice": 10000,
                    "finalPrice": 100000,
                    "clientDescription": "No enciende",
                    "technicalReport": "Fuente dañada",
                    "type": "COMPUTADORA_DE_ESCRITORIO",
                    "state": "EN_REPARACION",
                    "createdAt": "2025-02-04T15:59:39.745+00:00",
                    "updatedAt": "2025-02-04T15:59:39.745+00:00"
                }
            ]
        },
        {
            "idOrder": 6,
            "number": "ORD-00006",
            "observations": "Observation6",
            "orderTotal": 165000,
            "client": {
                "role": "CLIENT",
                "name": "Client6",
                "lastName": "ClientLastName6",
                "dni": "94808006",
                "address": "calle client 6",
                "createdAt": "2025-02-04T15:59:39.634+00:00",
                "updatedAt": "2025-02-04T15:59:39.634+00:00"
            },
            "active": true,
            "createdAt": "2025-02-04T15:59:39.703+00:00",
            "updatedAt": "2025-02-04T15:59:39.766+00:00",
            "devices": [
                {
                    "idDevice": 6,
                    "model": "iPhone 13",
                    "serialNumber": "SN-00006",
                    "accessories": "Cargador",
                    "initialPrice": 15000,
                    "finalPrice": 150000,
                    "clientDescription": "Pantalla dañada",
                    "technicalReport": "Falla en la cámara",
                    "type": "SMARTPHONE",
                    "state": "EN_REPARACION",
                    "createdAt": "2025-02-04T15:59:39.746+00:00",
                    "updatedAt": "2025-02-04T15:59:39.746+00:00"
                }
            ]
        },
        {
            "idOrder": 7,
            "number": "ORD-00007",
            "observations": "Observation7",
            "orderTotal": 165000,
            "client": {
                "role": "CLIENT",
                "name": "Client7",
                "lastName": "ClientLastName7",
                "dni": "94808007",
                "address": "calle client 7",
                "createdAt": "2025-02-04T15:59:39.635+00:00",
                "updatedAt": "2025-02-04T15:59:39.635+00:00"
            },
            "active": true,
            "createdAt": "2025-02-04T15:59:39.704+00:00",
            "updatedAt": "2025-02-04T15:59:39.766+00:00",
            "devices": [
                {
                    "idDevice": 7,
                    "model": "Samsung Galaxy Tab S7",
                    "serialNumber": "SN-00007",
                    "accessories": "Cargador",
                    "initialPrice": 15000,
                    "finalPrice": 150000,
                    "clientDescription": "Pantalla rota",
                    "technicalReport": "Batería dañada",
                    "type": "TABLET",
                    "state": "LISTO_PARA_RETIRO",
                    "createdAt": "2025-02-04T15:59:39.747+00:00",
                    "updatedAt": "2025-02-04T15:59:39.747+00:00"
                }
            ]
        },
        {
            "idOrder": 8,
            "number": "ORD-00008",
            "observations": "Observation8",
            "orderTotal": 550000,
            "client": {
                "role": "CLIENT",
                "name": "Client8",
                "lastName": "ClientLastName8",
                "dni": "94808008",
                "address": "calle client 8",
                "createdAt": "2025-02-04T15:59:39.635+00:00",
                "updatedAt": "2025-02-04T15:59:39.635+00:00"
            },
            "active": true,
            "createdAt": "2025-02-04T15:59:39.705+00:00",
            "updatedAt": "2025-02-04T15:59:39.767+00:00",
            "devices": [
                {
                    "idDevice": 8,
                    "model": "Lenovo ThinkPad X1 Carbon",
                    "serialNumber": "SN-00008",
                    "accessories": "Mouse inalámbrico",
                    "initialPrice": 50000,
                    "finalPrice": 500000,
                    "clientDescription": "No carga",
                    "technicalReport": "Placa base dañada",
                    "type": "NOTEBOOK",
                    "state": "ENTREGADO",
                    "createdAt": "2025-02-04T15:59:39.748+00:00",
                    "updatedAt": "2025-02-04T15:59:39.748+00:00"
                }
            ]
        },
        {
            "idOrder": 9,
            "number": "ORD-00009",
            "observations": "Observation9",
            "orderTotal": 1100000,
            "client": {
                "role": "CLIENT",
                "name": "Client1",
                "lastName": "ClientLastName1",
                "dni": "94808001",
                "address": "calle client 1",
                "createdAt": "2025-02-04T15:59:39.628+00:00",
                "updatedAt": "2025-02-04T15:59:39.628+00:00"
            },
            "active": false,
            "createdAt": "2025-02-04T15:59:39.706+00:00",
            "updatedAt": "2025-02-04T15:59:39.767+00:00",
            "devices": [
                {
                    "idDevice": 9,
                    "model": "MacBook Air 2020",
                    "serialNumber": "SN-00009",
                    "accessories": "Cargador original",
                    "initialPrice": 100000,
                    "finalPrice": 1000000,
                    "clientDescription": "Pantalla parpadeante",
                    "technicalReport": "Problema en la GPU",
                    "type": "NOTEBOOK",
                    "state": "RECIBIDO",
                    "createdAt": "2025-02-04T15:59:39.749+00:00",
                    "updatedAt": "2025-02-04T15:59:39.749+00:00"
                }
            ]
        },
        {
            "idOrder": 10,
            "number": "ORD-00010",
            "observations": "Observation10",
            "orderTotal": 77000,
            "client": {
                "role": "CLIENT",
                "name": "Client2",
                "lastName": "ClientLastName2",
                "dni": "94808002",
                "address": "calle client 2",
                "createdAt": "2025-02-04T15:59:39.630+00:00",
                "updatedAt": "2025-02-04T15:59:39.630+00:00"
            },
            "active": false,
            "createdAt": "2025-02-04T15:59:39.708+00:00",
            "updatedAt": "2025-02-04T15:59:39.767+00:00",
            "devices": [
                {
                    "idDevice": 10,
                    "model": "Sony Xperia 5",
                    "serialNumber": "SN-00010",
                    "accessories": "Cargador",
                    "initialPrice": 7000,
                    "finalPrice": 70000,
                    "clientDescription": "No enciende",
                    "technicalReport": "Falla en la placa base",
                    "type": "SMARTPHONE",
                    "state": "CANCELADO",
                    "createdAt": "2025-02-04T15:59:39.751+00:00",
                    "updatedAt": "2025-02-04T15:59:39.751+00:00"
                }
            ]
        }
    ],
    "orderSize": 10
}
*/

