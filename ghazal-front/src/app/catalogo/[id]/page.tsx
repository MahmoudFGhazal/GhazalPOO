'use client'
import { Furniture, ListFurniture } from "@/api/objects";
import { MdFavorite, MdFavoriteBorder } from "react-icons/md";
import styles from "./page.module.css";
import { useEffect, useState } from "react";
import api from "@/api/route";
import { useParams } from "next/navigation";
import Image from "next/image";
import Favorite from "@/components/favorite";

export default function Movel(){
    const [furniture, setFurniture] = useState<Furniture>();

    const params = useParams();
    const id = params.id?.toString();

    useEffect(() => {
        async function getFurniture() {
            const data = await api.get<ListFurniture>(`/furniture/${id}`);
            setFurniture(data[0]);
        }

        getFurniture();
    }, []);

    useEffect(() => {
        console.log(furniture);
    }, [furniture]);

    if (!furniture) return <p>Carregando móvel... </p>;

    return(
        <div className={styles.container}>
            <div className={styles.mainContainer}>
                <div className={styles.objectsContainer}>
                    <Image
                        className={styles.image}
                        src="https://static.wikia.nocookie.net/animeverso/images/0/04/ShrekRender.png/revision/latest/scale-to-width-down/340?cb=20231106231500&path-prefix=pt-br"
                        alt="Imagem do Movel"
                        width={450}
                        height={300}
                    />
                    <div className={styles.mainDescription}>
                        <div className={styles.upMainDescription}>
                            <h1>{furniture?.model}</h1>
                            <div className={styles.favorite} >
                                <Favorite size={24} id={furniture.id}/>
                            </div>
                        </div>
                        <p className={styles.price}>R${furniture.price}</p>
                    </div>
                </div>
            </div>
            <div className={styles.descriptionContainer}>
                <h1>Descrição</h1>
                <div className={styles.informationContainer}>
                    <p>Cor: {furniture.color.color}</p>
                    <p>Altura: {furniture.height}m</p>
                    <p>Largura: {furniture.width}m</p>
                    <p>Profundidade: {furniture?.depth}m</p>
                    <br/>
                    <h2>Caracteristicas:</h2>
                    <p>{furniture.characteristics}</p>
                </div>
                <div className={styles.categoryContainer}>
                    <p>Categoria: {furniture.categories.map((c) => (
                        <span key={c.id}>{c.category}</span>
                    ))}</p>
                </div>
            </div>
        </div>
    );
}