import { useState, useEffect } from "react";

const UserData = ({ userId }) => {
  const [userData, setUserData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchData = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch(
        `http://trackmyfix-backend.eqgrhtbfgsa4ggdk.brazilsouth.azurecontainer.io:9091/user/${userId}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer TU_TOKEN_AQUÍ`,
          },
        }
      );

      if (!response.ok) {
        throw new Error("Error al obtener los datos del usuario");
      }

      const data = await response.json();
      setUserData(data);
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, [userId]);

  return (
    <div className="max-w-md mx-auto bg-white shadow-lg rounded-lg p-6">
      {loading ? (
        <p className="text-gray-500">Cargando...</p>
      ) : error ? (
        <p className="text-red-500">{error}</p>
      ) : (
        <div>
          <h2 className="text-xl font-bold text-gray-800">{userData.name}</h2>
          <p className="text-gray-600">Email: {userData.email}</p>
          <p className="text-gray-600">ID: {userData.id}</p>
        </div>
      )}

      <button
        onClick={fetchData}
        className="mt-4 px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition"
        disabled={loading}
      >
        {loading ? "Cargando..." : "Recargar datos"}
      </button>
    </div>
  );
};

export default UserData;
