'use client'
import styles from "./favorite.module.css";

import { apiResponse, Favorite } from "@/api/objects";
import api from "@/api/route";
import { useEffect, useState } from "react";
import { MdFavorite, MdFavoriteBorder } from "react-icons/md";

export default function FavoriteIconWrapper({usr_id, fur_id, size}: {usr_id: number, fur_id: number, size: number}){
    const [favoriteIcon, setFavoriteIcon] = useState<boolean>(false);

    useEffect(() => {
        async function getFavorite(){
            const res: apiResponse = await api.get<apiResponse>(`/favorite/user/${usr_id}`);
            
            if(res.typeResponse !== "SUCCESS"){
                alert(res.message);
                window.location.reload();
            }
            
            const data: Favorite[] = res.entities as Favorite[];
            const favorite = data[0];

            if(favorite){
                for(const f of favorite.furnitures){
                    if(f.id === fur_id){
                        setFavoriteIcon(true);
                        return;
                    }
                }
            }
            
            setFavoriteIcon(false);       
        }
    
        getFavorite();
    }, []);

    async function deleteFavorite(){
        const res: apiResponse = await api.delete<apiResponse>(`/favorite/${usr_id}/${fur_id}`);
    
        if(res.typeResponse !== "SUCCESS"){
            alert(res.message);
            window.location.reload();
        }
    }

    async function addFavorite(){
        const res: apiResponse = await api.post(`/favorite/${usr_id}/${fur_id}`);

        if(res.typeResponse !== "SUCCESS"){
            alert(res.message);
            window.location.reload();
        }
    }

    async function handleClick() {
        if(favoriteIcon){
            addFavorite();
        }else{
            deleteFavorite();
        }
        setFavoriteIcon(!favoriteIcon);
    }
    
    return(
        <div className={styles.favoriteIconContent}
            onMouseEnter={() => setFavoriteIcon(!favoriteIcon)}
            onMouseLeave={() => setFavoriteIcon(!favoriteIcon)}
            onClick={(e) => {
                e.preventDefault();
                e.stopPropagation();
            }}
        >
            <MdFavorite 
                className={`${styles.favoriteIcon} ${favoriteIcon ? styles.favoriteVisible : styles.favoriteHidden}`}
                size={size}
            />
            <MdFavoriteBorder
                className={styles.favoriteIcon}
                onClick={handleClick}
                size={size}
            />
        </div>
    );
}