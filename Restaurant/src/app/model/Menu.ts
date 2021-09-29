interface MenuInterface {
    id: number;
    name: string;
    category: string;
    price: number;
}

export class Menu implements MenuInterface {
    id: number;
    name: string;
    category: string;
    price: number;

    constructor(object: MenuInterface) {
        this.id = object.id;
        this.name = object.name;
        this.category = object.category;
        this.price = object.price;
    }
}