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
  score = 0;

  constructor(private httpClient: HttpClient) {} // dependency injection

  start() {
    this.player = "Mihkel";
    this.httpClient.get(
      "http://localhost:8080/start?playerName=" + this.player, 
      { responseType: 'text' }
    ).subscribe(res => this.card = res);
    
  }

  startNewGuess() {
    this.response = "Guess the new card!";
  }

  guess(userGuess: string) {
    this.httpClient.get<any>("http://localhost:8080/" + userGuess).subscribe(res => {
      this.card = res.card;
      this.response = "You guessed " + res.message;
      this.score = res.score;
    });
    // this.response = "You guessed " + userGuess;
  }
}
