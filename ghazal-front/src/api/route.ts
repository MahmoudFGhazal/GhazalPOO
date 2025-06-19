import { Datas, Response } from "./objects";

type Method = "GET" | "POST" | "PUT" | "DELETE";

const BASE_URL = "http://localhost:8080";

async function request<Datas>(method: Method, url: string, data?: Datas): Promise<Datas> {
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

        const json = await res.json();

        if(json.message){
            console.log(json.message);
        }

        return await json.entities as Datas;
    } catch(error){
        console.error("Erro na Requisição", error);
        throw error;   
    }
}

const api = {
    get: <T>(url: string) => request<T>("GET", url),
    post: <T>(url: string) => request<T>("POST", url),
    put: <T>(url: string) => request<T>("PUT", url),
    delete: <T>(url: string) => request<T>("DELETE", url),
}

export default api;