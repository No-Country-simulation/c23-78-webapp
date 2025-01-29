export default function useLoginLocalStorage() {
    const [token, setToken] = useState("");
    const [refreshToken, setRefreshToken] = useState("");

    useEffect(() => {
        const token = localStorage.getItem("token");
        const refreshToken = localStorage.getItem("refresh_token");

        if (token && refreshToken) {
            setToken(token);
            setRefreshToken(refreshToken);
        }
    }, []);

    return { token, refreshToken };
}