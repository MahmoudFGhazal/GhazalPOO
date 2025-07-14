import styles from "./page.module.css";
import api from "@/api/route";
import { Furniture, apiResponse } from "@/api/objects";
import Catalog from "@/components/catalog";

async function getAllFurnitures(): Promise<Furniture[] | null> {
    const res: apiResponse = await api.get<apiResponse>("/furniture");
    
    if(res && res.typeResponse !== "SUCCESS"){
        alert(res.message);
        return null;
    }else{
        const data: Furniture[] = res.entities as Furniture[];

        return data || null;
    }
}

export default async function Catalogo(){
    const furnitures = await getAllFurnitures(); 

    //Arrumar: Criar Pagina
    if(!furnitures) return(<p>Problema no Servidor</p>);

    return(
        <div>
            <Catalog furnitures={furnitures} />
        </div>
    );
}