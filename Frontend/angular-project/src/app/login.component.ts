import { Component, OnInit } from "@angular/core";
import {HttpClient} from '@angular/common/http'
import { ActivatedRoute, Router } from '@angular/router';

import { TestUser } from "./testUser";

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./app.component.css']
})

export class AppLogin implements OnInit{
    username = '';

    constructor(
        private route: Router,
        private http: HttpClient){}

    createUser(username: string){

        const body = {username : username}
        this.http.post('http://localhost:8080', body).subscribe((data: any) =>{
            console.log(data.answer);
        })
        this.username = username;
        console.log(this.username);
        this.route.navigateByUrl('/game-page');
    }

    ngOnInit(){
        // this.http.get('http://localhost:8080/test').subscribe((data:any) =>{
        //     console.log(data.str);
        // })
    }
}