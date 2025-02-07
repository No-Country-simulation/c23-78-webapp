import { getAccessToken } from "../../auth/libs/tokenStorage";
const { VITE_BACKEND_URL } = import.meta.env;

const createUserOrder = async (clientData) => {

    const accessToken = getAccessToken();
    const url = `${VITE_BACKEND_URL}/user/register`;
    const headers = new Headers();
    const exampleData = {
        name: "Ragnar",
        lastName: "Lothbrok",
        email: "ragnaar@lothbrok.com",
        dni: 44351651,
        address: "cathegat 101",
        role: "TECHNICIAN",
        phone: "+11385598738",
        password: "ragnarok9"
    };
    console.log(clientData.password)
    const body = JSON.stringify(clientData);
    console.log("datos de ejemplo: ", exampleData);
    headers.append("Content-Type", "application/json");
    headers.append("Authorization", `Bearer ${accessToken}`);

    const requestOptions = {
        method: "POST",
        headers: headers,
        body: body,
        redirect: "follow",
    };

    try {
        const response = await fetch(url, requestOptions);
        if (!response.ok) {
            const errorData = await response.json();
            console.error("Error completo:", errorData);
            throw new Error(`Error al modificar la orden: ${JSON.stringify(errorData)}`);
        }
        const result = await response.json();
        return result;
    } catch (error) {
        console.error("Error al registrar al cliente:", error);
        return null;
    }
};

export default createUserOrder;
