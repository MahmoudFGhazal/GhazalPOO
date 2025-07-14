export interface apiResponse {
    entities: Datas,
    message: string,
    typeResponse: string,
}

export interface Furniture {
    id: number,
    model: string,
    height: number,
    width: number,
    depth: number, 
    weight: number,
    characteristics: string,
    image: string,
    price: number,
    stock: number,
    furnitureType: FurnitureType,
    manufacturer: Manufacturer,
    categories: Category[],
    materials: Material[],
    color: Color,
}

export interface FurnitureType {
    id: number,
    furnitureType: string,
}

export interface Manufacturer{
    id: number,
    manufacturer: string,
}

export interface Category {
    id: number,
    category: string,
}

export interface Material {
    id: number,
    material: string,
}

export interface Color {
    id: number,
    color: string,
}

export interface User {
    id: number,
    email: string,
    password: string,
    name: string,
    cpf: string,
}

export interface Favorite {
    id: number,
    user: User,
    furnitures: Furniture[],
}

export interface Review {
    id: number,
    rating: number,
    comment: string,
    user: User,
    furniture: Furniture,
}

export type Datas = Furniture | User | Favorite | FurnitureType | Manufacturer | Category | Material | Color | Review | 
                    Furniture[] | User[] | Favorite[] | FurnitureType[] | Manufacturer[] | Category[] | Material[] | Color[] | Review[];

