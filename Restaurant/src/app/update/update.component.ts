import { FormGroup, FormBuilder } from '@angular/forms';
import { Menu } from './../model/Menu';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MenuService } from '../menu.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  searchText: string = '';
  isupdated = false;
 
  @Input() 
  public menus: Menu[];
  @Input()
  public newMenu: Menu;
  @Input()
  public menu: Menu;

  public id: number;

  editForm: FormGroup;
 

  public page = 0;
  public size = 5;
  public last = false;

  constructor(public menuService: MenuService,
              public fb: FormBuilder) {
    this.menus = [];
    this.resetNewMenu();
   }

  ngOnInit(): void {
    //this.getMenus();
    this.loadData();
    this.editForm = this.fb.group({
      id: [0],
      name: [''],
      category: [''],
      price: [0]
    });
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
      .subscribe(_ => this.loadData());
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

  getMenu(id: number) {
    this.menuService
      .getMenu(id)
      .subscribe((menu:Menu) => this.update(menu));
  }

  getMenus() {
    this.menuService.getMenus()
      .subscribe((response: Menu[]) => this.menus = response);
  }

  delete(id: number) {
    this.menuService.deleteMenu(id)
      .subscribe(_ => this.loadData());
  }

  updateMenu(){
    this.menuService.updateMenu(this.menu)
    .subscribe(_ => this.loadData());
  }

  update(menu: Menu) {
    this.editForm.patchValue({
      id: menu.id,
      name: menu.name,
      category: menu.category,
      price: menu.price
    });
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
