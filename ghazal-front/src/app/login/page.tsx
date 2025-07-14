import styles from "./page.module.css";
import LoginForm from "@/components/loginForm";

import Link from "next/link";

export default function Login(){
    //Arrumar: Ver se está logado  
    return(
        <div className={styles.container}>
            <h1>Login</h1>
            <LoginForm />
            <Link 
                href={"/cadastrar"}
                className={styles.signInLink}
            >
                <button className={`${styles.button} ${styles.signIn}`}>Cadastrar</button>
            </Link>
        </div>
    );
}