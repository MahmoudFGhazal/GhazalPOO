'use client'
import styles from "./page.module.css";
import api from "@/api/route";
import { useEffect, useState } from "react";

import { ListFurniture, Response } from "@/api/objects";
import Item from "@/components/item";

export default function Catalogo(){
    const [furnitures, setFurnitures] = useState<ListFurniture>([]);
    const [currentFurnitures, setCurrentFurnitures] = useState<ListFurniture>([]);

    useEffect(() => {
        async function getAllFurnitures() {
            const res: Response = await api.get<Response>("/furniture");
            
            const data: ListFurniture = res.entities as ListFurniture;

            if(data){
                setFurnitures(data);
                setCurrentFurnitures(data);
            }
        }

        getAllFurnitures();
    }, []);


    function searchHandler(e: HTMLInputElement){
        const filtered = furnitures.filter((f) =>
            f.model.toLowerCase().includes(e.value.toLowerCase())
        );
        setCurrentFurnitures(filtered);
    }

    return(
        <div className={styles.container}>
            <div className={styles.filterContent}>
                <div className={styles.filterHeader}>
                    <h1>Filtro</h1>
                </div>

                <input
                    placeholder="Pesquise um móvel"
                    onChange={(e) => searchHandler(e.target)}
                />
            </div>
            
            <div className={styles.catalogContent}>
                {currentFurnitures.map((f) => (
                    <Item key={f.id} f={f}/>
                ))}
            </div>
        </div>
    );
}