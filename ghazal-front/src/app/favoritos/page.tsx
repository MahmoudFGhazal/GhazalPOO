import styles from './page.module.css';
import api from '@/api/route';
import { Favorite, apiResponse, User, Furniture } from '@/api/objects';
import { verifySession } from '@/services/session';
import ItemFavorite from '@/components/itemFavorite';

import { redirect } from "next/navigation";

async function getFavorites(): Promise<Furniture[] | null>{           
    const user: User = await verifySession();

    if(user){
        const res: apiResponse = await api.get<apiResponse>(`/favorite/user/${user.id}`);
        
        if(res && res.typeResponse !== "SUCCESS"){
            alert(res.message);
        }else{
            const data: Favorite[] = res.entities as Favorite[];
            const favorite: Favorite = data[0];
            if(favorite){
                return favorite.furnitures;
            } else{
                return null;
            }
        }
    }
    
    //Arrumar: Mostrar Alert
    redirect("/catalogo");
}

export default async function Favoritos(){
    const furnitures = await getFavorites();   
    
    //Arrumar: Criar Pagina
    if(!furnitures) return(<p>Lista Vazia</p>)

    return(
        <div className={styles.container}>
            {furnitures.map((f) => (
                <ItemFavorite key={f.id} f={f} />
            ))}
        </div>
    );
}