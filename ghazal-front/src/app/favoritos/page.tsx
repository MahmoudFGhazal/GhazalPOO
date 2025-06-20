'use client'
import { useEffect, useState } from 'react';
import styles from './page.module.css';
import api from '@/api/route';
import { Favorite, ListFavorite, ListFurniture, User } from '@/api/objects';
import Cookies from 'js-cookie';
import { verifySession } from '@/services/session';
import ItemFavorite from '@/components/itemFavorite';

export default function Favoritos(){
    const [furnitures, setFurnitures] = useState<ListFurniture>([]);

    useEffect(() => {
        async function getFavorites(){           
            const cookie = Cookies.get('session');
            if(cookie){
                const user: User = await verifySession();

                if(user){
                    const data: ListFavorite = await api.get<ListFavorite>(`/favorite/user/${user.id}`);
                    const favorite: Favorite = data[0];
                    setFurnitures(favorite.furnitures);
                }
            }
            
        }

        getFavorites();
    }, []);

        

    return(
        <div className={styles.container}>
            {furnitures.map((f) => (
                <ItemFavorite key={f.id} f={f} />
            ))}
        </div>
    );
}