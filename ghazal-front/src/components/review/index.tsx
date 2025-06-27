'use client'
import { useState } from 'react';
import styles from './review.module.css';
import { Furniture, Review, User, Response } from '@/api/objects';
import { FaRegStar, FaStar } from "react-icons/fa";
import { verifySession } from '@/services/session';
import Cookies from 'js-cookie';
import api from '@/api/route';
import Button from '@/components/submitButton';

export default function ReviewPopUp({furniture}: {furniture: Furniture}){
    const [comment, setComment] = useState("");
    const [rating, setRating] = useState(0);

    const handleSubmit = async () => {
        const cookie = Cookies.get('session');

        if(cookie){
            const user: User = await verifySession();
            const review: Review = {
                id: 0,
                rating: rating,
                comment: comment,
                user: user,
                furniture: furniture,
            }

            const res: Response = await api.put<Response>("/review", review);

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

    return(
        <div className={styles.container}>
            <h1>Avaliação</h1>
            <form className={styles.form} onSubmit={() => handleSubmit()}>
                <div>
                    {[1, 2, 3, 4, 5].map((star) => (
                        <span 
                            className={styles.star} 
                            key={star} 
                            onClick={() => handleRating(star)}
                        > 
                            {rating >= star ? <FaStar size={50} /> : <FaRegStar size={50} />}
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
    );
}