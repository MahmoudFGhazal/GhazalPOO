'use client'
import Image from "next/image";
import { useEffect, useState } from "react";
import Link from "next/link";

import styles from "./item.module.css";
import { Furniture } from "@/api/objects";
import Favorite from "@/components/favorite";

export default function Item({f}: {f: Furniture}){
    const [hoverFurniture, setHoverFurniture] = useState<number | boolean>(false);
    const [favoriteIcon, setFavoriteIcon] = useState<number | boolean>(false);

    return(
        <Link className={styles.link} href={`/catalogo/${f.id}`}>
            <div
                className={styles.furnitureContent}
                onMouseEnter={() => setHoverFurniture(f.id)}
                onMouseLeave={() => setHoverFurniture(false)}
            >
                <Image 
                    className={styles.furnitureImage}
                    src="https://static.wikia.nocookie.net/animeverso/images/0/04/ShrekRender.png/revision/latest/scale-to-width-down/340?cb=20231106231500&path-prefix=pt-br"
                    alt="Imagem do Movel"
                    width={240}
                    height={240}
                /><br/>
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