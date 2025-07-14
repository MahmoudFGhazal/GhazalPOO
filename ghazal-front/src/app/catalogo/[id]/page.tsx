import styles from "./page.module.css";
import { Furniture, apiResponse, User, Review } from "@/api/objects";
import api from "@/api/route";
import FavoriteIcon from "@/components/favoriteIconWrapper";
import ReviewPopUp from "@/components/reviewPopUp";

import Image from "next/image";
import { redirect } from "next/navigation";

async function getFurniture(id: string): Promise<Furniture | null> {
    const res: apiResponse = await api.get<apiResponse>(`/furniture/${id}`);
    
    if(res && res.typeResponse !== "SUCCESS"){
        alert(res.message);  
        return null;  
    }else{
        const data: Furniture[] = res.entities as Furniture[]; 
        
        return data[0] || null;
    }
}

async function getReviews(id: string): Promise<Review[] | null>{
    const res: apiResponse = await api.get<apiResponse>(`/review/furniture/${id}`)

    if(res && res.typeResponse !== "SUCCESS"){
        alert(res.message);
        return null
    }else{
        const data: Review[] = res.entities as Review[];

        return data || null;
    }
}

export default async function Movel({params}: {params: {id: string}}){
    const { id } = await params;

    const furniture = await getFurniture(id);

    if (!furniture) {
        //Arrumar: Fazer uma Pagina
        redirect("/catalogo");
    }

    const reviews = await getReviews(id) ?? [];
    const totalReviewRating = reviews.reduce((sum, review) => sum + review.rating, 0);
    const reviewAverage = reviews.length > 0 ? totalReviewRating / reviews.length : 0;

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
                            src="/assets/NãoEncontrada.jpg"
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
                                    <FavoriteIcon size={24} fur_id={furniture.id}/>
                                </div>
                            </div>
                            <p className={styles.price}>R${furniture.price}</p>
                        </div>
                        <p>{reviewAverage}</p>
                        <ReviewPopUp furniture={furniture} />
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