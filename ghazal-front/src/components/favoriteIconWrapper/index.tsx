'use client'
import { useEffect, useState } from 'react';
import FavoriteIcon from "./favoriteIcon";
import { User } from '@/api/objects';

export default function FavoriteIconWrapper({fur_id, size}: {fur_id: number, size: number}){
    const [user, setUser] = useState<User | null>(null);

    useEffect(() => {
        async function fetchUser(){
            const res = await fetch("/api/session", {
                method: "GET",
            });

            if(res.ok){
                const user = await res.json();
                setUser(user);
            }
        }

        fetchUser();
    }, []);

    if(!user) return(<></>);

    return(
        <FavoriteIcon size={size} fur_id={fur_id} usr_id={user.id} />
    );
}