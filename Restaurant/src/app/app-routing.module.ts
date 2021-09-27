import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { UpdateComponent } from './update/update.component';

const routes: Routes = [
  {path: 'menu/:id', component: MenuComponent},
  {path: 'menu', component: MenuComponent},
  {path: 'update', component: UpdateComponent},
  {path: '', redirectTo: 'menu', pathMatch: 'full'},
  {path: '**', component: MenuComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
