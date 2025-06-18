import { MdFavorite, MdFavoriteBorder } from "react-icons/md";
import { useState } from "react";

import styles from "./favorite.module.css";
import { Furniture } from "@/app/api/objects";

export default function Favorite({id, size}: {id: number, size: number}){
    const [favoriteIcon, setFavoriteIcon] = useState<boolean>(false);

    return(
        <div className={styles.favoriteIconContent}
            onMouseEnter={() => setFavoriteIcon(true)}
            onMouseLeave={() => setFavoriteIcon(false)}
        >
            <MdFavorite 
                className={`${styles.favoriteIcon} ${favoriteIcon ? styles.favoriteVisible : styles.favoriteHidden}`}
                size={size}
            />
            <MdFavoriteBorder
                className={styles.favoriteIcon}
                size={size}
            />
        </div>
    );
}