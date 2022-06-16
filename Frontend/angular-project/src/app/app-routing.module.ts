import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AppLogin } from './login.component'
import { GamePage } from './game-page.component';


const routes: Routes = [
  {path: '', redirectTo: '/login', component: AppComponent},
  {path: 'login', component: AppLogin},
  {path: 'game-page', component: GamePage}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
