
import styles from './submitButton.module.css';

export default function Button({text}: {text: string}){
    return(
        <>
            <button type="submit" className={styles.button}>{text}</button>
        </>
    );
}