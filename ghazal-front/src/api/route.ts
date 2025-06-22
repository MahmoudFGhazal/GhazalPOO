import { Datas, Response } from "./objects";

type Method = "GET" | "POST" | "PUT" | "DELETE";

const BASE_URL = "http://localhost:8080";

async function request<Response>(method: Method, url: string, data?: Datas): Promise<Response> {
    //No Header: Colcoar o Authorization
    const options: RequestInit = {
        method,
        headers: {
            "Content-Type": "application/json",
        },
        body: data ? JSON.stringify(data) : undefined,
    };

    try{
        const res = await fetch(`${BASE_URL}${url}`, options);
        if(!res.ok){
            const error = await res.text();
            throw new Error(error || "Erro na requisição");
        }

        const json: Response = await res.json();

        return await json;
    } catch(error){
        console.error("Erro na Requisição", error);
        throw error;   
    }
}

const api = {
    get: <Response>(url: string, data?: Datas) => request<Response>("GET", url, data),
    post: <Response>(url: string, data?: Datas) => request<Response>("POST", url, data),
    put: <Response>(url: string, data?: Datas) => request<Response>("PUT", url, data),
    delete: <Response>(url: string, data?: Datas) => request<Response>("DELETE", url, data),
}

export default api;