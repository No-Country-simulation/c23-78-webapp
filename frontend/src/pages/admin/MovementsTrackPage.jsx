import MovementsTrackTable from "../../modules/admin/components/MovementsTrackTable/MovementsTrackTable";
import { NavBar } from "../../modules/core/components";
import MovementsTrackSearch from "../../modules/admin/components/MovementsTrackSearch/MovementsTrackSearch";
import PageHeader from "../../modules/admin/components/PageHeader/PageHeader";

const MovementsTrackPage = () => {
    const handleLogout = () => {
        console.log("Cerrando sesión...");
    };

    return (
        <>
            <NavBar />
            <PageHeader
                title="Movimientos"
                description="Bienvenido a la sección de administración"
                buttonText="Cerrar sesión"
                onButtonClick={handleLogout}
            />
            <MovementsTrackSearch />
            <MovementsTrackTable />
        </>
    )
}

export default MovementsTrackPage;