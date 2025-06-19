'use server'
import { ListUser } from "@/api/objects";
import api from "@/api/route";
import { createSession } from "@/services/session";
import { error } from "console";
import { redirect } from "next/navigation";


export default async function handleLogin(email: string, password: string){
    if(!email || !password){
        return { success: false, message: "Campo Vazio"};
    }

    const data: ListUser = await api.get<ListUser>(`/user/${email}/${password}`);

    if(data && data.length > 0){
        await createSession(data);
        return { success: true };
    }else{
         console.error("Login API error:", error);
        return { success: false, message: "Email ou Senha Invalida"};
    }
}