// components/TimelineItem.js
import React from 'react';
import { Box, Typography } from '@mui/material';
import { styled } from '@mui/material/styles';

const TimelineItemContainer = styled(Box)({
    display: 'flex',
    alignItems: 'flex-start',
    marginBottom: '20px',
    position: 'relative',
});

const TimelineDot = styled(Box)({
    width: '20px',
    height: '20px',
    borderRadius: '50%',
    backgroundColor: '#f4511e',
    margin: '15px',
    position: 'relative',
});

const TimelineContent = styled(Box)({
    flex: 1,
});

export function TimelineItem({ status, description }) {
    return (
        <TimelineItemContainer>
            <TimelineDot />
            <TimelineContent>
                <Typography variant="subtitle1" sx={{ fontWeight: 500, mb: 0.5 }}>
                    {status}
                </Typography>
                <Typography variant="body2" color="textSecondary" sx={{ fontSize: '0.875rem', lineHeight: 1.4 }}>
                    {description}
                </Typography>
            </TimelineContent>
        </TimelineItemContainer>
    );
}
