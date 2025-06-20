'use client'
import handleSign from "@/services/handleSign";
import styles from "./page.module.css";
import { FormEvent, useState } from "react";
import api from "@/api/route";
import { User } from "next-auth";

export default function Cadastrar(){
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confPassword, setConfPassword] = useState('');
    const [name, setName] = useState('');
    const [cpf, setCpf] = useState('');

    function handleSubmit(event: FormEvent<HTMLFormElement>){
        event.preventDefault();

        async function SignIn(){
            const result: string = await handleSign(email, name, password, confPassword, cpf);
            if(result != ""){
                alert(result);
                return;
            }

            await api.put<User>(`/user`);

        }

        SignIn();
    }

    return(
        <div className={styles.container}>
            <h1>Cadastrar</h1>
            <form className={styles.form} onSubmit={(e) => handleSubmit(e)}>
                <div className={styles.input}>
                    <p>E-mail*:</p>
                    <input
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        type="email"
                        placeholder="E-mail"
                    />
                </div>
                <div className={styles.input}>
                    <p>Nome*:</p>
                    <input
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        type="text"
                        placeholder="Nome"
                    />
                </div>
                <div className={styles.input}>
                    <p>Senha*:</p>
                    <input
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        type="password"
                        placeholder="Senha"
                    />
                </div>
                <div className={styles.input}>
                    <p>Confirmar Senha*:</p>
                    <input
                        value={confPassword}
                        onChange={(e) => setConfPassword(e.target.value)}
                        type="password"
                        placeholder="Senha"
                    />
                </div>
                <div className={styles.input}>
                    <p>CPF:</p>
                    <input
                        value={cpf}
                        onChange={(e) => setCpf(e.target.value)}
                        type="text"
                        placeholder="CPF"
                    />
                </div>
                <button type="submit" className={styles.button}>Cadastrar</button>
            </form>
        </div>
    );
}