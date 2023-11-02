import { NgModule } from '@angular/core'; // kui ei ole ./ või ../ on tegemist node_modules impordiga
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

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
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
