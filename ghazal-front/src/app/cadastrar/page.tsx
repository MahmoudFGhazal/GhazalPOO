import styles from "./page.module.css";
import SignForm from "@/components/signForm"

export default function Cadastrar(){
    //Arrumar: Ver se está logado

    return(
        <div className={styles.container}>
            <h1>Cadastrar</h1>
            <SignForm />
        </div>
    );
}