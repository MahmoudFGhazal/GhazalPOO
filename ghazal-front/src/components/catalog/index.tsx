'use client'
import { Furniture } from "@/api/objects";
import styles from "./catalog.module.css";
import { useState } from "react";
import Item from "../item";

export default function Catalog({furnitures}: {furnitures: Furniture[]}){
    const [currentFurnitures, setCurrentFurnitures] = useState<Furniture[]>(furnitures);

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