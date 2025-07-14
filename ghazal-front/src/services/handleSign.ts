import { apiResponse, User } from "@/api/objects";
import api from "@/api/route";

export default async function handleSign(email: string, name: string, password: string, confPassword: string, cpf: string){
    if(!email || !password || !confPassword || !name){
        return { success: false, message:  "Algum campo obrigatorio vazio\nOs campos com * são obrigatorios"};
    }

    if(password != confPassword){
        return { success: false, message: "Senhas Divergentes"};
    }

    if(password.length > 255 || name.length > 255 || cpf.length > 255 || email.length > 255){
        return { success: false, message: "Tamanho do texto na caixa superior ao permitido\nMaximo 255 caracteres"};
    }

    if(cpf != "" && cpf.length != 14){
        return { success: false, message: "CPF Invalido"};
    }

    const user: User = {
        id: 0,
        email: email,
        name: name,
        password: password,
        cpf: cpf,
    }

    const res: apiResponse = await api.post<apiResponse>(`/user`, user);

    if(res.typeResponse === "SUCCESS"){
        return { success: true };
    }else{
        return { success: false, message: res.message};
    }
}