import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import PopupState, { bindTrigger, bindMenu } from 'material-ui-popup-state';
import { getRole } from '../../../auth/libs/tokenStorage';
import { useNavigate } from 'react-router-dom';

export default function AdminMenu() {

    const navigate = useNavigate();

    const handleOrder = () => {
        navigate("/admin/newOrder");
    };

    const handleClient = () => {
        navigate("/admin/newClient");
    };

    const handleWorker = () => {
        navigate("/admin/newWorker");
    };

    const itsAdmin = getRole() === "ADMIN";

    return (
        <PopupState variant="popover" popupId="demo-popup-menu" style={{ backgroundColor: '1' }}>
            {(popupState) => (
                <>
                    <Button variant="contained" {...bindTrigger(popupState)}>
                        Añadir +
                    </Button>
                    <Menu {...bindMenu(popupState)}>
                        <MenuItem onClick={handleOrder}>Orden</MenuItem>
                        <MenuItem onClick={handleClient}>Cliente</MenuItem>
                        <MenuItem onClick={handleWorker} disabled={!itsAdmin}>Trabajador</MenuItem>
                    </Menu>
                </>
            )}
        </PopupState>
    );
}