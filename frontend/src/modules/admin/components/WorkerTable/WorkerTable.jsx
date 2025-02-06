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
import getTechnicianList from "../../services/getTechnicianList";

export function WorkerTable() {
  const [workers, setWorkers] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  useEffect(() => {
    async function fetchWorkers() {
      try {
        const data = await getTechnicianList();
        setWorkers(data);
      } catch (error) {
        console.error("Error al obtener la lista de técnicos:", error);
      }
    }
    fetchWorkers();
  }, []);

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
                  <TableCell>{worker.name}</TableCell>
                  <TableCell>{worker.lastName}</TableCell>
                  <TableCell>{worker.phone}</TableCell>
                  <TableCell>{worker.email}</TableCell>
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
        count={workers.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}
