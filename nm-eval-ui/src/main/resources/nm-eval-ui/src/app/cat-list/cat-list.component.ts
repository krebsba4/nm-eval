import { Component, OnInit } from '@angular/core';
import { CatsService } from '../services/cats.service';

@Component({
  selector: 'app-cat-list',
  templateUrl: './cat-list.component.html',
  styleUrls: ['./cat-list.component.css']
})

export class CatListComponent implements OnInit {
  cats: Array<any>;
  searchInput: string;

  constructor(private catsService: CatsService) { }

  ngOnInit() {
    this.catsService.getCats().subscribe(catsData => {
      // Sort cats service return by the uuid
      this.cats = catsData.sort(function(a, b) {
        return a.id > b.id ? 1 : a.id < b.id ? -1 : 0;
      });
    });
  }

  onKey(value: string) {
    this.searchInput += value;
  }

}
