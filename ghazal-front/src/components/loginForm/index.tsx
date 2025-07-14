'use client'
import { FormEvent, useEffect, useState } from "react";
import styles from "./loginForm.module.css";
import handleLogin from "@/services/handleLogin";
import { useRouter } from "next/navigation";
import Button from "../submitButton";

export default function LoginForm(){
    const router = useRouter();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {
        async function getSession(){
            const res = await fetch("/api/session", {
                method: "GETNAME",
            });

            if(res.ok){
                const user = await res.json();
                if(user){
                    router.push('/catalogo');
                }
            }
        }

        getSession();
    }, []);

    function handleSubmit(event: FormEvent<HTMLFormElement>){
        event.preventDefault();

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
    );
}