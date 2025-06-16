import { Datas } from "./objects";

type Method = "GET" | "POST" | "PUT" | "DELETE";

const BASE_URL = "http://localhost:8080";

async function request<T>(method: Method, url: string, data?: Datas): Promise<T> {
    //No Header: Colcoar o Authorization
    const options: RequestInit = {
        method,
        headers: {
            "Content-Type": "application/json",
        },
        body: data ? JSON.stringify(data) : undefined,
    };

    const res = await fetch(`${BASE_URL}${url}`, options);

    if(!res.ok){
        const error = await res.text();
        throw new Error(error || "Erro na requisição");
    }

    return res.json();
}

const api = {
    get: <T>(url: string) => request<T>("GET", url),
    post: <T>(url: string) => request<T>("POST", url),
    put: <T>(url: string) => request<T>("PUT", url),
    delete: <T>(url: string) => request<T>("DELETE", url),
}

export default api;