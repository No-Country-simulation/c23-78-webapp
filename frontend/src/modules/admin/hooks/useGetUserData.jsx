const useGetUserData = (userId) => {
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

    return {
        userData,
        loading,
        error,
    };
};

export default useGetUserData;