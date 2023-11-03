import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  response = "";
  player = "";
  card = "";
  cardValue = 0;
  score = 0;

  constructor(private httpClient: HttpClient) {} // dependency injection

  // start tagastab ainult Stringi
  // väärtust ei pane
  start() {
    // this.player = "Mihkel";
    this.httpClient.get(
      "http://localhost:8080/start?playerName=" + this.player, 
      { responseType: 'text' }
    ).subscribe(res => {
      this.card = res.split(":")[0];
      this.cardValue = Number(res.split(":")[1]); 
      this.startNewGuess();
    });
  }

  startNewGuess() {
    this.response = "Guess the new card!";
  }

  guess(userGuess: string) {
    this.httpClient.get<any>("http://localhost:8080/" + userGuess).subscribe(res => {
      if (res.message == "Game_Over") {
        this.response = "Game is over. Your points: " + res.score;
        this.card = "";
      } else if (res.message == "TIMED_OUT") {
        this.response = "You timed out!";
      } else {
        this.card = res.card;
        this.response = "You guessed " + res.message;
        this.score = res.score;
        this.cardValue = res.cardValue;
        // console.log(this.cardValue);
        // console.log(res.cardValue);
      }
      
    });
    // this.response = "You guessed " + userGuess;
  }
}
