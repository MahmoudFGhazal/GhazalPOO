import { MdFavorite, MdFavoriteBorder } from "react-icons/md";
import { useEffect, useState } from "react";

import styles from "./favorite.module.css";
import { Furniture, ListFavorite, Response, User } from "@/api/objects";
import api from "@/api/route";
import Cookies from "js-cookie";
import { verifySession } from "@/services/session";

export default function Favorite({id, size}: {id: number, size: number}){
    const [favoriteIcon, setFavoriteIcon] = useState<boolean>(false);

    useEffect(() => {
        async function getFavorite(){
            const cookie = Cookies.get('session');
            if(cookie){
                const user: User = await verifySession();
                const res: Response = await api.get<Response>(`/favorite/user/${user.id}`);
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
        const cookie = Cookies.get('session');
        if(cookie){
            const user: User = await verifySession();
            await api.delete(`/favorite/${user.id}/${id}`);
        }
    }

    async function addFavorite(){
        const cookie = Cookies.get('session');
        if(cookie){
            const user: User = await verifySession();
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