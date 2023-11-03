import { NgModule } from '@angular/core'; // kui ei ole ./ või ../ on tegemist node_modules impordiga
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module'; // need on kuskilt meie failidest
import { AppComponent } from './app.component'; // sest ees on ./
import { HomeComponent } from './home/home.component';
import { ScoresComponent } from './scores/scores.component';

@NgModule({
  declarations: [ // kõik lehed
    AppComponent,
    HomeComponent,
    ScoresComponent
  ],
  imports: [ // kõik võimekused, mida Angular teha saab
    BrowserModule, // *ngIf ....   <ng-cointainer>
    AppRoutingModule, // lisab kõik navigeerimisega seotud loogika
    HttpClientModule, // lisab kõik API päringutega seotud loogika
    FormsModule // kõik vormidega seotud loogika (input)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
