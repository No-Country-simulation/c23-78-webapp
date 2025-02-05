import React from 'react';
import { Paper, Typography } from '@mui/material';
import { Timeline } from '../TimeLine/TimeLine';
import { useTrackingTimeline } from '../../hooks/useTrackingTimeline';

export function TrackTimeLine({ orderData }) {
    const timelineItems = useTrackingTimeline(orderData);

    return (
        <Paper
            elevation={1}
            sx={{
                width: '100%',
                maxWidth: { xs: '100%', sm: 700 },
                margin: 'auto',
                mt: { xs: 2, sm: 4 },
                p: { xs: 2, sm: 3 },
                backgroundColor: '#fff',
                borderRadius: '8px',
            }}
        >
            <Typography variant="h6" gutterBottom sx={{ mb: 3 }}>
                Paso a paso del seguimiento del producto
            </Typography>
            <Timeline timelineItems={timelineItems} />
        </Paper>
    );
}

export default TrackTimeLine;