'use client'
import { FormEvent, useState } from "react";
import styles from "./page.module.css";
import { LoginRequest } from "../api/objects";

export default function Login(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function handleSubmit(event: FormEvent<HTMLFormElement>){
        event.preventDefault();
        const user: LoginRequest = {email, password};

        console.log(user);
        
    }

    return(
        <div className={styles.container}>
            <h1>Login</h1>
            <form className={styles.form}>
                <div className={styles.input}>
                    <p>E-mail:</p>
                    <input
                        type="email"
                        placeholder="E-mail"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className={styles.input}>
                    <p>Senha:</p>
                    <input
                        type="password"
                        placeholder="Senha"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button className={styles.button}>Enviar</button>
            </form>
        </div>
    );
}