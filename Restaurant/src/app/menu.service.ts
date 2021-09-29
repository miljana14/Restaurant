import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from './model/Menu';
import { MenuPage } from './model/MenuPage';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  menuUrl = 'http://localhost:8080/api/menu/';

  constructor(private http: HttpClient) { }

  getMenus(): Observable<any> {
    return this.http
      .get(this.menuUrl);
  }

  getMenu(id: number): Observable<any> {
    return this.http
      .get(this.menuUrl + id);
  }

  deleteMenu(id: number): Observable<any> {
    return this.http
      .delete(this.menuUrl + id);
  }

  saveMenu(menu: Menu): Observable<any> {
    return this.http
      .post(this.menuUrl, menu);
  }

  updateMenu(menu:Menu): Observable<any> {
    return this.http.put(this.menuUrl + menu.id,menu);
}

  getMenuPaged(page: any, size: any): Observable<MenuPage> {
    const params = new HttpParams() 
      .set('page', page)
      .set('size', size);

    return this.http.get<MenuPage>(this.menuUrl, { params });
  } 
}
