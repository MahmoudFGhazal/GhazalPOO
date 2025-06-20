

export default async function handleSign(email: string, name: string, password: string, confPassword: string, cpf: string){
    email.split;
    name.split;
    password.split;
    confPassword.split;
    cpf.split;

    console.log(email, name, password, confPassword, cpf)

    if(!email || !password || !confPassword || !name){
        return "Algum campo obrigatorio vazio\nOs campos com * são obrigatorios";
    }

    if(password != confPassword){
        return "Senhas Divergentes";
    }

    if(password.length > 255 || name.length > 255 || cpf.length > 255 || email.length > 255){
        return "Tamanho do texto na caixa superior ao permitido\nMaximo 255 caracteres";
    }

    return "";

}