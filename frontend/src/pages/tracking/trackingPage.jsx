import { NavBar } from "../../modules/core/components";
import { TrackingTimeLine } from "../../modules/core/components";
import { Footer } from "../../modules/core/components";
import { OrderForm } from "../../modules/core/components/OrderForm/OrderForm";
import { UserLoggin } from "../../modules/test/UserLoggin";
const TrackingPage = () => {
  return (
    <>
      <NavBar />
      <TrackingTimeLine />
      <OrderForm />
    </>
  );
};

export default TrackingPage;