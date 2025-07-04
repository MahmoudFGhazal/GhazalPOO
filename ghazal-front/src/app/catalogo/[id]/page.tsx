'use client'
import { Furniture, ListFurniture, apiResponse, User } from "@/api/objects";
import { IoMdClose } from "react-icons/io";
import styles from "./page.module.css";
import { useEffect, useState } from "react";
import api from "@/api/route";
import { useParams } from "next/navigation";
import Image from "next/image";
import Favorite from "@/components/favorite";
import Review from "@/components/review";
import { verifySession } from "@/services/session";

export default function Movel(){
    const [furniture, setFurniture] = useState<Furniture>();
    const [showReview, setShowReview] = useState<Boolean>(false);
    const [session, setSession] = useState<Boolean>(false);

    const params = useParams();
    const id = params.id?.toString();

    useEffect(() => {
        async function getSession(){
            const user: User = await verifySession();

            if(user){
                setSession(true);
            }else{
                setSession(false)
            }
        }

        getSession();

        async function getFurniture() {
            const res: apiResponse = await api.get<apiResponse>(`/furniture/${id}`);
            
            if(res && res.typeResponse !== "SUCCESS"){
                alert(res.message);    
            }else{
                const data: ListFurniture = res.entities as ListFurniture; 
                
                setFurniture(data[0]);
            }
        }

        getFurniture();
    }, []);

    if (!furniture) return <p>Carregando móvel... </p>;
    console.log(furniture.image);
    return(
        <div className={styles.container}>
            <div className={styles.mainContainer}>
                <div className={styles.objectsContainer}>
                    {furniture.image ?
                        <Image
                            className={styles.image}
                            src={furniture.image}
                            alt="Imagem do Movel"
                            width={450}
                            height={300}
                        />
                    :
                        <Image
                            className={styles.image}
                            src="/assets/ShrekRender.png"
                            alt="Imagem do Movel"
                            width={450}
                            height={300}
                        />
                    }
                    <div className={styles.mainDescription}>
                        <div>
                            <div className={styles.upMainDescription}>
                                <h1>{furniture?.model}</h1>
                                <div className={styles.favorite} >
                                    <Favorite size={24} id={furniture.id}/>
                                </div>
                            </div>
                            <p className={styles.price}>R${furniture.price}</p>
                        </div>
                        {session && (
                            <div className={styles.reviewContent} onClick={() => setShowReview(true)}>
                                <p>Avaliar</p>
                            </div>
                        )}
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

            {showReview && (
                <div className={styles.overlay}>
                    <IoMdClose className={styles.closeButton} size={50} onClick={() => setShowReview(false)}/>
                    <Review furniture={furniture} />
                </div>    
            )}

        </div>
    );
}