import Image from "next/image";

import styles from "./header.module.css";
import Logo from "@/../public/assets/Logo-Simples.jpg";
import { verifySession } from "@/services/session";
import { User } from "@/api/objects";
import HeaderLink from "./headerLink";
import LogoutButton from "./logoutButton";

export default async function Header(){
    const user: User = await verifySession();

    return(
        <header className={styles.header}>
            <div className={styles.startContainer}>
                <HeaderLink link="catalogo" text="Catálogo" />
                {(user !== null) &&
                    <HeaderLink link="favoritos" text="Favorito" />
                }
            </div>
            <div className={styles.block}>
                <Image
                    className={styles.Logo}
                    alt="Logo"
                    src={Logo}
                    priority={true}
                />   
                {(user as User) ?
                    <LogoutButton usr_name={user.name} />
                :
                    <HeaderLink link="login" text="Login" />
                }
            </div>

        </header>
    );
}