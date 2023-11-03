import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-scores',
  templateUrl: './scores.component.html',
  styleUrls: ['./scores.component.scss']
})
export class ScoresComponent {
    //  Game[]
  games: any[] = [];

  constructor(private httpClient: HttpClient) {}

  ngOnInit() {     // Game[]
    this.httpClient.get<any>("http://localhost:8080/all-games-ordered").subscribe(res => {
      this.games = res;
    })
  }

}
