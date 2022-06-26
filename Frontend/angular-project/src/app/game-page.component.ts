import { Component, Input, OnDestroy, OnInit, ViewChild } from "@angular/core";
import { HttpClient } from '@angular/common/http'
import { GameSettingsService } from "./game-settings";
import { Subscription } from "rxjs";
import { Observable } from "rxjs/";
import { CdTimerComponent } from "angular-cd-timer";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'game-page',
    templateUrl: './game-page.component.html'
})

export class GamePage implements OnInit, OnDestroy {
    private userId: string = '';
    public username: string = '';
    private turnsLimitation: number = 0;
    public timeLimitation: number = 0;
    public turnsLeft: number = 0;
    public turnsPast: number = 0;
    // public timeLimits: number = 10;
    private gameId: string = '';

    public isGameStarted: boolean = false;
    public result: string = '';
    public statistic: any;

    private subs!: Subscription;

    ticks = 0;

    @ViewChild('basicTimer', { static: true })
    basicTimer!: CdTimerComponent;

    constructor(
        private route: Router,
        private http: HttpClient,
        private readonly gameSettingsService: GameSettingsService
    ) { }

    ngOnInit(): void {
        this.basicTimer.stop();
        this.subs = this.gameSettingsService.userId$.subscribe((userId) => this.userId = userId);
        this.subs = this.gameSettingsService.username$.subscribe((username) => {
            this.username = username;
            console.log(username)
        });
        this.subs = this.gameSettingsService.turnsLimitation$.subscribe((turnsLimitation) => {
            this.turnsLimitation = Number(turnsLimitation);
            console.log(turnsLimitation)
        });
        this.subs = this.gameSettingsService.timeLimitation$.subscribe((timeLimitation) => this.timeLimitation = Number(timeLimitation));

    }

    ngOnDestroy(): void {
        this.subs.unsubscribe();
    }

    startGame() {
        if (this.userId === "") {
            this.route.navigateByUrl('/');
        }
        if (!this.isGameStarted) {
            this.turnsPast = 0;
            if (this.timeLimitation > 0) {
                this.basicTimer.start();
            }
            if (this.turnsLimitation > 0) {
                this.turnsLeft = this.turnsLimitation;
            }

            console.log('username: ' + this.username);
            console.log('userid: ' + this.userId);
            this.http.post('http://localhost:8080/game-page/start', this.userId).subscribe((data: any) => {
                this.gameId = data.gameId;
            });
            this.isGameStarted = true;
        }
    }

    pickNumber(userNumber: string) {
        if (this.userId === "") {
            this.route.navigateByUrl('/');
        }
        if (this.isGameStarted) {


            if (userNumber.match(/^\d{4}$/)) {
                this.turnsPast++;
                if (this.turnsLimitation > 0) {
                    this.turnsLeft--;
                }
                const body = { "number": userNumber, "gameId": this.gameId };
                this.http.post('http://localhost:8080/game-page/guess', body).subscribe((data: any) => {
                    this.result = data.result;

                    if (data.isEnd == "true") {
                        this.basicTimer.stop();
                        this.isGameStarted = false;
                        console.log("into if");
                        this.http.post('http://localhost:8080/game-page/statistic', this.userId).subscribe((data) => {
                            this.statistic = data;
                            console.log(data);
                        });
                    }
                });
            }

        }
    }


}