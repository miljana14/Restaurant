import { Menu } from "./Menu";

export class MenuPage{
    public content: Menu[];
    public last: boolean;

    constructor(content: Menu[], last: boolean){
        this.content = content;
        this.last = last;
    }
}