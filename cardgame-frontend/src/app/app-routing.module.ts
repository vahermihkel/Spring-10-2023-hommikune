import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ScoresComponent } from './scores/scores.component';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "edetabel", component: ScoresComponent }
];

// file -> preferences -> Theme -> color theme -> "Dark Modern"
// lillakad: käsklused
// tumesinised: sissekirjutatud sõnad
// tavaline sinine: const muutujad
// helesinised: omadused/võtmed
// oranžid: stringid
// rohelised: (imporditud) klassid
// {{{{{{{{}}}}}}}}
// Visual Studio Code sisseehitatud abi, mis näitab kus sulgub mis sulg


// localhost:4200
// localhost:4200/edetabel

// mihkel.com
// mihkel.com/edetabel

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
