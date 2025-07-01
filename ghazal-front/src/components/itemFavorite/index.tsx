import { Furniture } from '@/api/objects';
import styles from './item.module.css';
import Image from 'next/image';
import Favorite from '../favorite';
import Link from 'next/link';

export default function ItemFavorite({f}: {f: Furniture}){
    return(
        <Link className={styles.container} href={`/catalogo/${f.id}`}>
            {f.image ?
                <Image
                    className={styles.image}
                    src={f.image}
                    alt="Imagem do Movel"
                    width={240}
                    height={240}
                />
            :
                <Image
                    className={styles.image}
                    src="/assets/ShrekRender.png"
                    alt="Imagem do Movel"
                    width={240}
                    height={240}
                />
            }<br/>
            <div className={styles.description}>
                <div>
                    <p className={styles.model}>{f.model}</p>
                    <p className={styles.price}>R${f.price}</p>
                </div>
                <Favorite key={f.id} size={30} id={f.id}/>
            </div>
        </Link>
    );
}