'use client'
import Image from "next/image";
import { useEffect, useState } from "react";
import Link from "next/link";

import styles from "./item.module.css";
import { Furniture } from "@/api/objects";
import Favorite from "@/components/favorite";

export default function Item({f}: {f: Furniture}){
    const [hoverFurniture, setHoverFurniture] = useState<number | boolean>(false);
    return(
        <Link className={styles.link} href={`/catalogo/${f.id}`}>
            <div
                className={styles.furnitureContent}
                onMouseEnter={() => setHoverFurniture(f.id)}
                onMouseLeave={() => setHoverFurniture(false)}
            >
                {f.image ?
                <Image
                    className={styles.image}
                    src={f.image}
                    alt="Imagem do Movel"
                    width={240}
                    height={240}
                />
            :
                <Image
                    className={styles.image}
                    src="/assets/ShrekRender.png"
                    alt="Imagem do Movel"
                    width={240}
                    height={240}
                />
            }<br/>
                <div className={styles.upDescription}>
                    <div className={styles.nameContent}>
                        <p>{f.model}</p>
                    </div>
                    <Favorite key={f.id} size={24} id={f.id}/>
                </div>
                <div className={styles.priceContent}>
                    <p className={`${styles.price} ${hoverFurniture === f.id && styles.hidden}`}>
                        R${f.price}
                    </p>
                        
                    <p className={`${styles.showMore} ${styles.price} ${hoverFurniture === f.id ? styles.visible : styles.hidden}`}>
                        Saiba Mais
                    </p>
                </div>
            </div>
        </Link>
    );
}