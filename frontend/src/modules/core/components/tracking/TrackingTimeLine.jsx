import React from "react";
import { Box, Typography, Paper } from "@mui/material";
import { styled } from "@mui/material/styles";

// Styled components
const TimelineContainer = styled(Box)({
  position: "relative",
  padding: "20px",
  display: "flex",
  flexDirection: "column",
  alignItems: "flex-start",
});

const TimelineItem = styled(Box)({
  display: "flex",
  alignItems: "flex-start",
  marginBottom: "20px",
  position: "relative",
});

const TimelineDot = styled(Box)({
  width: "20px", 
  height: "20px", 
  borderRadius: "50%",
  backgroundColor: "#f4511e",
  margin: "15px", 
  position: "relative",
});

const TimelineContent = styled(Box)({
  flex: 1,
});

const trackingData = [
  {
    status: "Recibido en el taller",
    description: "Tu producto ha sido recibido. Estamos preparando el diagnóstico inicial.",
  },
  {
    status: "En diagnóstico",
    description: "Nuestro equipo está evaluando el problema para encontrar la mejor solución.",
  },
  {
    status: "Esperando piezas",
    description: "Estamos esperando recibir las piezas necesarias para completar la reparación.",
  },
  {
    status: "En reparación",
    description: "Estamos trabajando en la reparación de tu producto.",
  },
  {
    status: "En control de calidad",
    description: "Estamos verificando que tu producto funcione correctamente después de la reparación.",
  },
  {
    status: "Listo para retirar",
    description: "Tu producto está reparado y listo para que lo retires.",
  },
  {
    status: "Entregado al cliente",
    description: "Ya retiraste tu producto. ¡Gracias por confiar en nosotros!",
  },
];

export function TrackingTimeLine() {
  return (
    <Paper
      elevation={1}
      sx={{
        width: "100%",
        maxWidth: { xs: "100%", sm: 700 },
        margin: "auto",
        mt: { xs: 2, sm: 4 },
        p: { xs: 2, sm: 3 },
        backgroundColor: "#fff",
        borderRadius: "8px",
      }}
    >
      <Typography variant="h6" gutterBottom sx={{ mb: 3 }}>
        Paso a paso del seguimiento del producto
      </Typography>

      <TimelineContainer>
        {trackingData.map((item, index) => (
          <TimelineItem key={index}>
            <TimelineDot />

            <TimelineContent>
              <Typography
                variant="subtitle1"
                sx={{
                  fontWeight: 500,
                  mb: 0.5,
                }}
              >
                {item.status}
              </Typography>
              <Typography
                variant="body2"
                color="textSecondary"
                sx={{
                  fontSize: "0.875rem",
                  lineHeight: 1.4,
                }}
              >
                {item.description}
              </Typography>
            </TimelineContent>
          </TimelineItem>
        ))}
      </TimelineContainer>
    </Paper>
  );
}

export default TrackingTimeLine;