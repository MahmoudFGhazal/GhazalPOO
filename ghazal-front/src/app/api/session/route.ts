import { deleteSession, getNameSession, verifySession } from "@/services/session";
import { User } from "@/api/objects";
import { NextResponse } from "next/server";

export async function GET(){
    const user: User = await verifySession();
    if(user === undefined) return new Response(null, {status:401});
    return NextResponse.json(user);
}

export async function GETNAME(){
    const userName = await getNameSession();
    if(userName === undefined) return new Response(null, {status: 401});
    return NextResponse.json(userName);
}

export async function DELETE(){
    deleteSession();
    const user: User = await verifySession();
    if(user !== null) return new Response(null, {status:401})
    return new Response(null, { status: 200 });
}