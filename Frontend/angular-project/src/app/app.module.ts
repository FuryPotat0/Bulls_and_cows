import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppLogin } from './login.component';
import { GamePage } from './game-page.component';

import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  {path: '', component: AppLogin},
  // {path: '', redirectTo: '/login'},
  {path: 'login', component: AppLogin},
  {path: 'game-page', component: GamePage}
];

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    // AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    AppLogin,
    GamePage
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
