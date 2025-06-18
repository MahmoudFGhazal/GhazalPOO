import { IoReorderThree } from "react-icons/io5";
import { FaSearch } from "react-icons/fa";
import Image from "next/image";
import Link from "next/link";

import styles from "./header.module.css";
import Logo from "@/../public/assets/Logo-Simples.jpg";

export default function Header(){
    return(
        <header className={styles.header}>
            <div className={styles.startContainer}>
                <Link className={styles.link} href={'/catalogo'}>
                    <p>Catálogo</p>
                </Link>
            </div>
            <div className={styles.block}>
                <Image
                    className={styles.Logo}
                    alt="Logo"
                    src={Logo}
                    priority={true}
                />
                <Link className={styles.link} href={'/login'}>
                    <p>Login</p>
                </Link>
            </div>

        </header>
    );
}