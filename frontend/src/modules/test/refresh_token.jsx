import { useState } from "react";

export default function Refresh_Token() {
    const [tokenInput, setTokenInput] = useState("");
    const [refreshToken, setRefreshToken] = useState("");

    function handleSubmit(event) {
        event.preventDefault();

        const newTokens = {
            access_token: tokenInput,
            refresh_token: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaW5kcmdAZ21haWwuY29tIiwiaWF0IjoxNzM4MTkyMDk5LCJleHAiOjE3MzgxOTI5OTksImlzcyI6InRyYWNrbXlmaXgifQ.fEFnffEDh1gSKTn4uqX1nf-drHpRXRHbHkv73EYayXc", // Simular un refresh_token
        };
        localStorage.setItem("token", newTokens.access_token);
        localStorage.setItem("refresh_token", newTokens.refresh_token);

        setRefreshToken(newTokens.refresh_token);

        setTokenInput("");
    }

    function showToken() {
        const token = localStorage.getItem("token");
        console.log("Token:", token);
    }

    function showRefreshToken() {
        const refreshToken = localStorage.getItem("refresh_token");
        console.log("Refresh Token:", refreshToken);
    }

    return (
        <div>
            <form onSubmit={handleSubmit} className="mb-4">
                <input
                    type="text"
                    value={tokenInput}
                    onChange={(e) => setTokenInput(e.target.value)}
                    placeholder="Ingresa el token"
                    className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:border-[#F55F1D]"
                    required
                />
                <button
                    type="submit"
                    className="bg-[#F55F1D] text-white py-2 px-4 rounded-lg hover:bg-[#d14e19] transition duration-300 w-full mt-2"
                >
                    Guardar token
                </button>
            </form>
            <button
                onClick={showToken}
                className="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition duration-300 w-full mb-2"
            >
                Mostrar token
            </button>

            <button
                onClick={showRefreshToken}
                className="bg-green-500 text-white py-2 px-4 rounded-lg hover:bg-green-700 transition duration-300 w-full"
            >
                Mostrar refresh_token
            </button>
        </div>
    );
}