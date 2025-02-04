import React from "react"
import { Box, Typography, Paper } from "@mui/material"
import { styled } from "@mui/material/styles"

// Styled components
const TimelineContainer = styled(Box)({
  position: "relative",
  padding: "20px",
  "&::before": {
    content: '""',
    position: "absolute",
    left: "115px",
    top: "30px",
    bottom: "30px",
    width: "2px",
    background: "#e0e0e0",
  },
})

const TimelineItem = styled(Box)({
  display: "flex",
  alignItems: "flex-start",
  marginBottom: "20px",
  position: "relative",
})

const TimelineDot = styled(Box)({
  width: "16px",
  height: "16px",
  borderRadius: "50%",
  backgroundColor: "#f4511e",
  margin: "0 10px",
  flexShrink: 0,
  position: "relative",
  zIndex: 1,
})

const TimelineContent = styled(Box)({
  flex: 1,
  marginLeft: "20px",
})

const DateTimeBox = styled(Box)({
  width: "85px",
  textAlign: "right",
  flexShrink: 0,
})

export function TrackingTimeLine() {
  const trackingData = [
    {
      date: "27-0-2024",
      time: "06:55 hs.",
      status: "Recibido en el taller",
      description: "Tu producto ha sido recibido. Estamos preparando el diagnóstico inicial.",
    },
    {
      date: "26-09-2024",
      time: "11:25 hs.",
      status: "En diagnóstico",
      description: "Nuestro equipo está evaluando el problema para encontrar la mejor solución.",
    },
    {
      date: "26-09-2024",
      time: "10:51 hs.",
      status: "Esperando piezas",
      description: "Estamos esperando recibir las piezas necesarias para completar la reparación.",
    },
    {
      date: "21-09-2024",
      time: "12:42 hs.",
      status: "En reparación",
      description: "Estamos trabajando en la reparación de tu producto.",
    },
    {
      date: "21-09-2024",
      time: "12:01 hs.",
      status: "En control de calidad",
      description: "Estamos verificando que tu producto funcione correctamente después de la reparación.",
    },
    {
      date: "21-09-2024",
      time: "12:45 hs.",
      status: "Listo para retirar",
      description: "Tu producto está reparado y listo para que lo retires.",
    },
    {
      date: "22-09-2024",
      time: "10:45 hs.",
      status: "Entregado al cliente",
      description: "Ya retiraste tu producto. ¡Gracias por confiar en nosotros!",
    },
  ]

  return (
    <Paper
      elevation={1}
      sx={{
        width: "100%",
        maxWidth: { xs: "100%", sm: 700 },
        margin: "auto",
        mt: { xs: 2, sm: 4},
        p: { xs: 2, sm:3},
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
            <DateTimeBox>
              <Typography variant="body2" color="textSecondary" sx={{ fontSize: "0.875rem" }}>
                {item.date}
              </Typography>
              <Typography variant="body2" color="textSecondary" sx={{ fontSize: "0.875rem" }}>
                {item.time}
              </Typography>
            </DateTimeBox>

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
  )
}

export default TrackingTimeLine