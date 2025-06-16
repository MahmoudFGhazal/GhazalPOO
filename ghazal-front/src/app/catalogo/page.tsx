'use client'
import { ListFurniture } from "@/app/api/objects";
import { MdFavoriteBorder } from "react-icons/md";
import Image from "next/image";

import styles from "./page.module.css";
import api from "@/app/api/route";
import { useEffect, useState } from "react";

export default function Catalogo(){
    const [furnitures, setFurnitures] = useState<ListFurniture>([]);

    useEffect(() => {
        async function getAllFurnitures() {
            try{
                const data = await api.get<ListFurniture>("/furniture");
                setFurnitures(data);
            } catch(error) {
                console.error("Erro ao carregar os móveis", error);
            }
        }

        getAllFurnitures();
    }, []);

    return(
        <div className={styles.container}>
            <h1>Catalogo</h1>
            
            <div className={styles.catalogContent}>
                {furnitures.map((f) => (
                    <div key={f.id}>
                        <Image 
                            src="https://static.wikia.nocookie.net/animeverso/images/0/04/ShrekRender.png/revision/latest/scale-to-width-down/340?cb=20231106231500&path-prefix=pt-br"
                            alt="Imagem do Movel"
                            width={240}
                            height={240}
                        /><br/>
                        <p>{f.model}</p>
                        <MdFavoriteBorder />
                        <br/>
                        <p>{f.price}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}