'use server'
import { User, apiResponse } from "@/api/objects";
import api from "@/api/route";
import { createSession } from "@/services/session";


export default async function handleLogin(email: string, password: string){
    if(!email || !password){
        return { success: false, message: "Campo Vazio"};
    }

    const res: apiResponse = await api.get<apiResponse>(`/user/${email}/${password}`);

    if(res.typeResponse === "SUCCESS"){
        const data: User[] = res.entities as User[];
        if(data){
            await createSession(data);
            return { success: true };
        }else{
            return { success: false, message: "Erro no Servidor"};
        }
    }else{
        return { success: false, message: res.message};
    }
}