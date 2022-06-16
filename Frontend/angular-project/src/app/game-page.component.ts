import { Component, OnInit } from "@angular/core";
import {HttpClient} from '@angular/common/http'

@Component({
    selector: 'game-page',
    templateUrl: './game-page.component.html'
})

export class GamePage{
    // username = '';

    // constructor(private http: HttpClient){}

    // createUser(username: string){

    //     const body = {username : username}
    //     this.http.post('http://localhost:8080', body).subscribe((data: any) =>{
    //         console.log(data.answer);
    //     })
    //     this.username = username;
    //     console.log(this.username);
    // }
}