// components/Timeline.js
import React from 'react';
import { Box, Typography } from '@mui/material';
import { TimelineItem } from '../TimeLineItem/TimeLineItem';


export function Timeline({ timelineItems }) {
    return (
        <Box sx={{ position: 'relative', padding: '20px' }}>
            {timelineItems.map((item, index) => (
                <TimelineItem key={index} status={item.status} description={item.description} />
            ))}
        </Box>
    );
};
