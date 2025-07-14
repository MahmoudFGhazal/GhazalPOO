'use client'
import styles from "./logoutButton.module.css";

export default function LogoutButton({ usr_name }: {usr_name: string}){
    async function handleExit(){
        const res = await fetch("/api/session", {
            method: "DELETE",
        });

        if(res.ok){
            window.dispatchEvent(new Event('session-changed'));
            window.location.reload();
            return;
        }
        alert("Erro para sair da conta");
    }

    return(
        <div>
            <p className={styles.pointer} onClick={() => handleExit()}>{usr_name}</p>
        </div>
    );
}