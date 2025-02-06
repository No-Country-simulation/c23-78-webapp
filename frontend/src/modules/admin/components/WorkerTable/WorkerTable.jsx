import { useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  IconButton,
  Tooltip,
  TablePagination,
} from "@mui/material";

import { Pencil, Trash, Eye } from "lucide-react";

// Datos ficticios de clientes (aumentados para demostrar la paginación)
const workers = [
  {
    id: "001",
    dni: "22345567840",
    firstName: "Emilio",
    lastName: "Yong",
    phone: "612345678",
    email: "emilio-y89@gmail.com",
    address: "Guayaquil, Mz 291 solar 1"
  }
];

export function WorkerTable() {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(Number.parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleEdit = (id) => {
    console.log(`Editar técnico ${id}`);
    // Implementar lógica de edición
  };

  const handleView = (id) => {
    console.log(`Ver técnico ${id}`);
    // Implementar lógica de visualización
  };

  const handleDelete = (id) => {
    console.log(`Eliminar técnico ${id}`);
    // Implementar lógica de eliminación
  };

  return (
    <Paper
      sx={{
        width: "100%",
        overflow: "hidden",
        margin: "auto",
      }}
    >
      <TableContainer sx={{ maxHeight: 440 }}>
        <Table stickyHeader aria-label="tabla de técnicos">
          <TableHead>
            <TableRow>
              <TableCell sx={{ fontWeight: "bold" }}># TRABAJADOR</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>DNI</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>NOMBRE</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>APELLIDO</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>TELÉFONO</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>EMAIL</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>ACCIONES</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {workers
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((worker) => (
                <TableRow key={worker.id}>
                  <TableCell component="th" scope="row">
                    {worker.id}
                  </TableCell>
                  <TableCell>{worker.dni}</TableCell>
                  <TableCell>{worker.firstName}</TableCell>
                  <TableCell>{worker.lastName}</TableCell>
                  <TableCell>{worker.phone}</TableCell>
                  <TableCell>{worker.email}</TableCell>
                  <TableCell>
                    <Tooltip title="Editar">
                      <IconButton
                        onClick={() => handleEdit(worker.id)}
                        size="small"
                      >
                        <Pencil />
                      </IconButton>
                    </Tooltip>
                    <Tooltip title="Ver">
                      <IconButton
                        onClick={() => handleView(worker.id)}
                        size="small"
                      >
                        <Eye />
                      </IconButton>
                    </Tooltip>
                    <Tooltip title="Eliminar">
                      <IconButton
                        onClick={() => handleDelete(worker.id)}
                        size="small"
                      >
                        <Trash />
                      </IconButton>
                    </Tooltip>
                  </TableCell>
                </TableRow>
              ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        count={workers.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}
