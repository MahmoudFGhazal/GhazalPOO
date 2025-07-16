'use client'
import { FormEvent, useEffect, useState } from 'react';
import styles from './review.module.css';
import { Furniture, Review, User, apiResponse } from '@/api/objects';
import { FaRegStar, FaStar } from "react-icons/fa";
import { getNameSession, verifySession } from '@/services/session';
import api from '@/api/route';
import Button from '@/components/submitButton';
import { IoMdClose } from 'react-icons/io';

export default function ReviewPopUp({furniture}: {furniture: Furniture}){
    const [comment, setComment] = useState("");
    const [rating, setRating] = useState(0);
    const [ratingHover, setRatingHover] = useState<null | number>(null);
    const [showReview, setShowReview] = useState<Boolean>(false);
    const [session, setSession] = useState<Boolean>(false);

    useEffect(() => {
        async function getSession(){
            const res = await fetch("/api/session", {
                method: "GETNAME",
            });

            if(res.ok){
                const user = await res.json();
                const hasSession = !!(user);
                setSession(hasSession);
            }
        }

        getSession();
    }, []);


    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        const user: User = await verifySession();

        if(user){
            const review: Review = {
                rating: rating,
                comment: comment,
                user: user,
                furniture: furniture,
            }

            const res: apiResponse = await api.put<apiResponse>("/review", review);

            if(res.message){
                alert(res.message);
            }else{
                alert("Sua avaliação foi enviada com sucesso");
            }
        }
    }

    function handleRating(star: number) {
        if(star == 1 && rating == 1){
            setRating(0);
            return;
        }
        setRating(star);
    }

    if(!session) return(<></>);

    if(!showReview) return(
        <div className={styles.reviewContent} onClick={() => setShowReview(true)}>
            <p>Avaliar</p>
        </div>
    );

    return(
        <div className={styles.overlay}>
        <IoMdClose className={styles.closeButton} size={50} onClick={() => setShowReview(false)}/>
            <div className={styles.container}>
                <h1>Avaliação</h1>
                <form className={styles.form} onSubmit={(e) => handleSubmit(e)}>
                    <div>
                        {[1, 2, 3, 4, 5].map((star) => (
                            <span 
                                className={styles.star} 
                                key={star} 
                                onClick={() => handleRating(star)}
                                onMouseEnter={() => setRatingHover(star)}
                                onMouseLeave={() => setRatingHover(null)}
                            > 
                                {((rating >= star) || (ratingHover && ratingHover >= star)) ? <FaStar size={50} /> : <FaRegStar size={50} />}
                            </span>
                        ))}
                    </div>
                    <textarea 
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                        className={styles.comment} 
                        placeholder='Comentário' 
                        maxLength={200}
                    />
                    <Button text={"Enviar"} />
                </form>
            </div>
        </div>
    );
}