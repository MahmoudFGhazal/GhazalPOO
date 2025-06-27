'use client'
import Image from "next/image";
import Link from "next/link";

import styles from "./header.module.css";
import Logo from "@/../public/assets/Logo-Simples.jpg";
import { useEffect, useState } from "react";
import { deleteSession, verifySession } from "@/services/session";
import { User } from "@/api/objects";
import Cookies from 'js-cookie';
import { usePathname } from "next/navigation";

export default function Header(){
    const [session, setSession] = useState<string | null>(null);
    const pathname = usePathname();
    const isCatalogo = pathname === '/catalogo';
    const isFavorite = pathname === '/favoritos';

    useEffect(() => {
        async function getSession(){
            const cookie = Cookies.get('session');

            if(cookie){
                const user: User = await verifySession();
                if(user && user.name){
                    const formattedName = user.name.charAt(0).toUpperCase() + user.name.slice(1).toLowerCase();
                    setSession(formattedName);
                }else{
                    setSession(null);
                }
            }else{
                setSession(null);
            }
        }

        getSession();

        const listener = () => {
            getSession();
        };

        window.addEventListener('session-changed', listener);
        return () => window.removeEventListener('session-changed', listener);
    }, []);

    async function handleExit(){
        deleteSession();
        window.dispatchEvent(new Event('session-changed'));
        window.location.reload();
    }

    return(
        <header className={styles.header}>
            <div className={styles.startContainer}>
                {!isCatalogo &&
                    <Link className={styles.link} href={'/catalogo'}>
                        <p className={styles.pointer}>Catálogo</p>
                    </Link>
                }
                {(session !== null && !isFavorite) &&
                    <Link className={styles.link} href={'/favoritos'}>
                        <p className={styles.pointer}>Favoritos</p>
                    </Link>
                }
            </div>
            <div className={styles.block}>
                <Image
                    className={styles.Logo}
                    alt="Logo"
                    src={Logo}
                    priority={true}
                />
                {session === null ?
                    <Link className={styles.link} href={'/login'}>
                        <p className={styles.pointer}>Login</p>
                    </Link>
                :
                    <p className={styles.pointer} onClick={() => handleExit()}>{session}</p>
                }
            </div>

        </header>
    );
}