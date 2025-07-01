'use client'
import { useEffect, useState } from 'react';
import styles from './page.module.css';
import api from '@/api/route';
import { Favorite, ListFavorite, ListFurniture, apiResponse, User } from '@/api/objects';
import { verifySession } from '@/services/session';
import ItemFavorite from '@/components/itemFavorite';

export default function Favoritos(){
    const [furnitures, setFurnitures] = useState<ListFurniture>([]);

    useEffect(() => {
        async function getFavorites(){           
            const user: User = await verifySession();

            if(user){
                const res: apiResponse = await api.get<apiResponse>(`/favorite/user/${user.id}`);
                const data: ListFavorite = res.entities as ListFavorite;
                const favorite: Favorite = data[0];
                setFurnitures(favorite.furnitures);
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