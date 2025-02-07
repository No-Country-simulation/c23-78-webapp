import { useParams } from "react-router-dom"
import { ModifyOrderForm } from "../../modules/admin/components/ModifyOrderForm/ModifyOrderForm";
import { Footer, NavBar } from "../../modules/core/components";

export default function ModifyOrder() {
    const { orderNumber } = useParams();
    return (
        <div>
            <NavBar />
            <ModifyOrderForm orderNumber={orderNumber} />
            <Footer />
        </div>
    )
}