'use server'
import { SignJWT, jwtVerify } from 'jose';
import { ListUser, User } from "@/api/objects";
import { cookies } from "next/headers";

const secretKey = 'key';
const key = new TextEncoder().encode(secretKey);

async function encrypt(payload: any){
    return await new SignJWT(payload)
        .setProtectedHeader({ alg: 'HS256'})
        .setIssuedAt()
        .sign(key);
}

async function decrypt(input: string): Promise<any> {
    const {payload} = await jwtVerify(input, key, {
        algorithms: ['HS256'],
    });
    return payload;
}

export async function createSession(data: ListUser){
    const user: User = data[0];

    const expires = new Date(Date.now() + 60 * 60 * 1000);
    const session = await encrypt({user, expires});

    const cookieStore = await cookies();
    cookieStore.set('session', session, {expires});
}

export async function verifySession(){
    const cookieStore = await cookies();
    const session = cookieStore.get('session')?.value;
    if (!session) return null;

    const decryptedPayload = await decrypt(session);

    if(decryptedPayload && decryptedPayload.user){
        return decryptedPayload.user;
    }
    return null;
}

export async function deleteSession(){
    const cookieStore = await cookies();
    cookieStore.set('session', '',  {expires: new Date(0)});
}