import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

export function middleware(request: NextRequest){
    const headers = new Headers(request.headers);
    headers.set("current-path", request.nextUrl.pathname);
    
    return NextResponse.next({ headers });
}

export const confg = {
    matcher: [
        "/((?!api|_next/static|_next/image|favicon.ico).*)",
    ],
};