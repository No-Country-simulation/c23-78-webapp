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
import useGetClientList from "../../hooks/useGetUserList";

// Datos ficticios de clientes (aumentados para demostrar la paginación)
 const clients = [
   {
     id: "001",
     dni: "12345678A",
     firstName: "Juan",
     lastName: "Pérez",
     phone: "612345678",
   }
 ];

export function ClientTable() {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  const clientsAll = useGetClientList();
  console.log(JSON.stringify(clientsAll));

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(Number.parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleEdit = (id) => {
    console.log(`Editar cliente ${id}`);
    // Implementar lógica de edición
  };

  const handleView = (id) => {
    console.log(`Ver cliente ${id}`);
    // Implementar lógica de visualización
  };

  const handleDelete = (id) => {
    console.log(`Eliminar cliente ${id}`);
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
        <Table stickyHeader aria-label="tabla de clientes">
          <TableHead>
            <TableRow>
              <TableCell sx={{ fontWeight: "bold" }}># DE CLIENTE</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>DNI</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>NOMBRE</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>APELLIDO</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>TELÉFONO</TableCell>
              <TableCell sx={{ fontWeight: "bold" }}>ACCIONES</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {clients
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((client) => (
                <TableRow key={client.id}>
                  <TableCell component="th" scope="row">
                    {client.id}
                  </TableCell>
                  <TableCell>{client.dni}</TableCell>
                  <TableCell>{client.firstName}</TableCell>
                  <TableCell>{client.lastName}</TableCell>
                  <TableCell>{client.phone}</TableCell>
                  <TableCell>
                    <Tooltip title="Editar">
                      <IconButton
                        onClick={() => handleEdit(client.id)}
                        size="small"
                      >
                        <Pencil />
                      </IconButton>
                    </Tooltip>
                    <Tooltip title="Ver">
                      <IconButton
                        onClick={() => handleView(client.id)}
                        size="small"
                      >
                        <Eye />
                      </IconButton>
                    </Tooltip>
                    <Tooltip title="Eliminar">
                      <IconButton
                        onClick={() => handleDelete(client.id)}
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
        count={clients.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}
