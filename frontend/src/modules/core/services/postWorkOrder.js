import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

export default async function postWorkOrder(workOrderData) {
    const accessToken = getAccessToken();
    const url = `${VITE_BACKEND_URL}/work-order`;
    const headers = new Headers();
    headers.append("Content-Type", "application/json");
    headers.append("Authorization", `Bearer ${accessToken}`);

    const requestOptions = {
        method: "POST",
        headers: headers,
        body: JSON.stringify(workOrderData),
        redirect: "follow",
    };

    try {
        console.log("trying to post work order");
        const response = await fetch(url, requestOptions);
        if (!response.ok) {
            const errorData = await response.json();
            console.error("Error completo:", errorData);
            throw new Error(`Error al modificar la orden: ${JSON.stringify(errorData)}`);
          }
        const result = await response.json();
        return result;
    } catch (error) {
        console.error("Error al enviar la orden de trabajo:", error);
        return null;
    }
}
