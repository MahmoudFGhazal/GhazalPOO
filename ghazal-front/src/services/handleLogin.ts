'use server'
import { ListUser, Response } from "@/api/objects";
import api from "@/api/route";
import { createSession } from "@/services/session";
import { error } from "console";


export default async function handleLogin(email: string, password: string){
    if(!email || !password){
        return { success: false, message: "Campo Vazio"};
    }

    const res: Response = await api.get<Response>(`/user/${email}/${password}`);
    const data: ListUser = res.entities as ListUser;

    if(data && data.length > 0){
        await createSession(data);
        return { success: true };
    }else{
         console.error("Login API error:", error);
        return { success: false, message: "Email ou Senha Invalida"};
    }
}