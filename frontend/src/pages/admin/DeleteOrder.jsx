import React from 'react'
import { useParams } from 'react-router-dom';

export const DeleteOrder = () => {
    const { orderId } = useParams();
  return (
    <div>DeleteOrder</div>
  )
}
