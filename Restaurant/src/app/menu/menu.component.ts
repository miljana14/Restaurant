import { MenuService } from './../menu.service';
import { Menu } from './../model/Menu';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  searchText: string = '';
 
  @Input() 
  public menus: Menu[];
  @Input()
  public newMenu: Menu;


  public page = 0;
  public size = 5;
  public last = false;

  constructor(public menuService: MenuService) {
    this.menus = [];
    this.resetNewMenu();
   }

  ngOnInit(): void {
    this.getMenus();
    this.loadData();
  }

  loadData() {
    this.menuService.getMenuPaged(this.page, this.size).subscribe(
      res => {
        this.menus = res.content;
        this.last = res.last;
      }
    );
  }

  previousPage() {
    this.page--;
    this.loadData();
  }

  nextPage() {
    this.page++;
    this.loadData();
  }

  save() {
    this.menuService.saveMenu(this.newMenu)
      .subscribe(_ => this.getMenus());
    this.resetNewMenu();
  }

  resetNewMenu() {
    this.newMenu = new Menu({
      id: 0,
      name: '',
      category: '',
      price: 0
    });
  }

  getMenus() {
    this.menuService.getMenus()
      .subscribe((response: Menu[]) => this.menus = response);
  }

  delete(id: number) {
    this.menuService.deleteMenu(id)
      .subscribe(_ => this.getMenus());
  }

  getFilteredMenu(){
    console.log("searchText", this.searchText);
    return this.menus.filter(menu => this.searchText == undefined || this.searchText == '' || menu.name.includes(this.searchText) || menu.category.includes(this.searchText))
  }

  checkLength(){
    if(this.menus.length <= this.size){
    this.page = 0;
    }
}

}
