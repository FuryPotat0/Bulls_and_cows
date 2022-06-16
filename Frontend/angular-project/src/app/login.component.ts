import { Component, OnInit } from "@angular/core";
import {HttpClient} from '@angular/common/http'
import { ActivatedRoute, Router } from '@angular/router';
import { GameSettingsService } from "./game-settings";

import { Turn } from "./turn";

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./app.component.css']
})

export class AppLogin implements OnInit{
    username = '';

    constructor(
        private route: Router,
        private http: HttpClient,
        private readonly gameSettingsService: GameSettingsService){}

    createUser(username: string){
        const body = {username : username}
        this.http.post('http://localhost:8080', username).subscribe((data: any) =>{
            this.gameSettingsService.setUserId(data.userId);
            this.gameSettingsService.setUsername(username);
            this.gameSettingsService.setTurnsLimitation(data.turnsLimitation);
            this.gameSettingsService.setTimeLimitation(data.timeLimitation);
            console.log('userid: ' + data.userId);
            // console.log(data.timeLimitation);
        })
        this.username = username;
        console.log(this.username);
        this.route.navigateByUrl('/game-page');
    }

    ngOnInit(){
    }
}