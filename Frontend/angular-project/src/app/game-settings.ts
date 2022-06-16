import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class GameSettingsService {
    public userId$ = new Subject<string>();
    public username$ = new Subject<string>();
    public turnsLimitation$ = new Subject<string>();
    public timeLimitation$ = new Subject<string>();

    public setUserId(userId: string){
        this.userId$.next(userId);
    }
    public setUsername(username: string){
        this.username$.next(username);
    }
    public setTurnsLimitation(turnsLimitation: string){
        this.turnsLimitation$.next(turnsLimitation);
    }
    public setTimeLimitation(timeLimitation: string){
        this.timeLimitation$.next(timeLimitation);
    }
}