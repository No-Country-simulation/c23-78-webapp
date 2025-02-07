import React from 'react'
import { ModifyClientForm } from '../../modules/admin/components/ModifyClientForm/ModifyClientForm'
import { useParams } from 'react-router-dom'

export const ModifyClient = () => {
    const { id } = useParams();

  return (
    <div>
        <ModifyClientForm id={id} />
    </div>
  )
}
