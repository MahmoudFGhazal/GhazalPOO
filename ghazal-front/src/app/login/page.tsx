'use client'
import { FormEvent, useState, useEffect } from "react";
import styles from "./page.module.css";

import handleLogin from "@/services/handleLogin";
import { useRouter } from "next/navigation";
import { User } from "@/api/objects";
import Cookies from "js-cookie";
import { verifySession } from "@/services/session";
import Link from "next/link";
import Button from "@/components/submitButton";

export default function Login(){
    const router = useRouter();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {
        async function getSession(){
            const cookie = Cookies.get('session');
            if(cookie){
                const user: User = await verifySession();
                if(user){
                    router.push('/catalogo');
                }
            }
        }

        getSession();
    }, []);

    function handleSubmit(event: FormEvent<HTMLFormElement>){
        event.preventDefault();

        if(!email || !password){
            alert("Algum campo vazio");
            return;
        }

        async function Login() {
            const result = await handleLogin(email, password);
            if(result && !result.success){
                alert(result.message);
            }else{
                window.dispatchEvent(new Event('session-changed'));
                router.push('/catalogo');
            }
        }

        Login();
    }

    return(
        <div className={styles.container}>
            <h1>Login</h1>
            <form className={styles.form} onSubmit={(e) => handleSubmit(e)}>
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
                <Button text={"Enviar"} />
            </form>
            <Link 
                href={"/cadastrar"}
                className={styles.signInLink}
            >
                <button className={`${styles.button} ${styles.signIn}`}>Cadastrar</button>
            </Link>
        </div>
    );
}