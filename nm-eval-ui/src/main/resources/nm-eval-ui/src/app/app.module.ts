import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CatsService } from './services/cats.service';
import { MatListModule, MatInputModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { ListFilterPipe } from './pipes/list-filter-pipe';

import { AppComponent } from './app-component/app.component';
import { CatListComponent } from './cat-list/cat-list.component';

@NgModule({
  declarations: [
    AppComponent,
    CatListComponent,
    ListFilterPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatInputModule,
    MatListModule,
    BrowserAnimationsModule,
    FormsModule
  ],
  providers: [CatsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
