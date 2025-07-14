'use client'
import { FormEvent, useState } from 'react';
import styles from './signForm.module.css';
import { apiResponse, User } from '@/api/objects';
import api from '@/api/route';
import Button from '../submitButton';
import { useRouter } from "next/navigation";
import handleSign from '@/services/handleSign';

export default function signForm(){
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [confPassword, setConfPassword] = useState('');
    const [cpf, setCpf] = useState('');
    const router = useRouter();

    function formatCpf(value: string): string {
        const digits = value.replace(/\D/g, '').slice(0,11);

        const formatted = digits
            .replace(/(\d{3})(\d)/, '$1.$2')
            .replace(/^(\d{3})\.(\d{3})(\d)/, '$1.$2.$3')
            .replace(/^(\d{3})\.(\d{3})\.(\d{3})(\d)/, '$1.$2.$3-$4');
    
        return formatted;
    }

    function handleSubmit(event: FormEvent<HTMLFormElement>){
            event.preventDefault();
    
            async function SignIn() {
                const result = await handleSign(email, name, password, confPassword, cpf);
                
                if(result && !result.success){
                    alert(result.message);
                }else{
                    window.dispatchEvent(new Event('session-changed'));
                    router.push('/catalogo');
                }
            }
    
            SignIn();
        }


    return(
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
                    onChange={(e) => setCpf(formatCpf(e.target.value))}
                    type="text"
                    placeholder="CPF"
                />
            </div>
            <Button text={"Cadastrar"} />
        </form>
    )
}