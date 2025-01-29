import { useState, useEffect } from "react";
import { getUserList } from "../services/getUser";
export default function useFetchUser() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        setUser(getUserList());
    }, []);

    return user;
}