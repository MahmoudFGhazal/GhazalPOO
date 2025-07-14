'use client'
import styles from "./headerLink.module.css";
import Link from "next/link";
import { usePathname } from "next/navigation";

export default function HeaderLink({link, text}: {link: string, text: string}){
    const pathname = usePathname();
    const isCurrentLink = pathname == `/${link}`;

    if(isCurrentLink) return(<></>);

    return(
        <Link className={styles.link} href={`/${link}`}>
            <p className={styles.pointer}>{text}</p>
        </Link>
    );
}