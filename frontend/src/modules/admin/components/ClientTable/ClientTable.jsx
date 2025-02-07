import { useState, useEffect } from "react";
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
import getClientList from "../../services/getClientList";

export function ClientTable() {
  const [clients, setClients] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  useEffect(() => {
    async function fetchClients() {
      try {
        const data = await getClientList();
        setClients(data);
      } catch (error) {
        console.error("Error fetching clients:", error);
      }
    }
    fetchClients();
  }, []);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(Number.parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleEdit = (id) => {
    console.log(`Editar cliente ${id}`);
    navigate(`/admin/modifyClient/${id}`);
      refreshToken(); 
  };

  const handleView = (id) => {
    console.log(`Ver cliente ${id}`);
  };

  const handleDelete = (id) => {
    console.log(`Eliminar cliente ${id}`);
  };

  return (
    <Paper sx={{ width: "100%", overflow: "hidden", margin: "auto" }}>
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
                  <TableCell component="th" scope="row">{client.id}</TableCell>
                  <TableCell>{client.dni}</TableCell>
                  <TableCell>{client.name}</TableCell>
                  <TableCell>{client.lastName}</TableCell>
                  <TableCell>{client.phone}</TableCell>
                  <TableCell>
                    <Tooltip title="Editar">
                      <IconButton
                        onClick={() => handleEdit(worker.id)}
                        size="small" sx={{
                          transition: "color 0.3s",
                          "&:hover": { color: "orange" }
                        }}
                      >
                        <Pencil />
                      </IconButton>
                    </Tooltip>
                    <Tooltip title="Ver">
                      <IconButton
                        onClick={() => handleView(worker.id)}
                        size="small" sx={{
                          transition: "color 0.3s",
                          "&:hover": { color: "green" }
                        }}
                      >
                        <Eye />
                      </IconButton>
                    </Tooltip>
                    <Tooltip title="Eliminar">
                      <IconButton
                        onClick={() => handleDelete(worker.id)}
                        size="small" sx={{
                          transition: "color 0.3s",
                          "&:hover": { color: "red" }
                        }}
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
