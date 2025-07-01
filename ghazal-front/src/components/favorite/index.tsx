import { MdFavorite, MdFavoriteBorder } from "react-icons/md";
import { useEffect, useState } from "react";

import styles from "./favorite.module.css";
import { Furniture, ListFavorite, apiResponse, User } from "@/api/objects";
import api from "@/api/route";
import { verifySession } from "@/services/session";

export default function Favorite({id, size}: {id: number, size: number}){
    const [favoriteIcon, setFavoriteIcon] = useState<boolean>(false);

    useEffect(() => {
        async function getFavorite(){
            const user: User = await verifySession();

            if(user){
                const res: apiResponse = await api.get<apiResponse>(`/favorite/user/${user.id}`);
                const data: ListFavorite = res.entities as ListFavorite;
                const favorite = data[0];

                for(const f of favorite.furnitures){
                    if(f.id === id){
                        setFavoriteIcon(true);
                        return;
                    }
                }
                setFavoriteIcon(false);
            } 
        }
    
        getFavorite();
    }, []);

    async function deleteFavorite(){
        const user: User = await verifySession();

        if(user){
            await api.delete(`/favorite/${user.id}/${id}`);
        }
    }

    async function addFavorite(){
            const user: User = await verifySession();
            if(user){
                await api.post(`/favorite/${user.id}/${id}`);
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