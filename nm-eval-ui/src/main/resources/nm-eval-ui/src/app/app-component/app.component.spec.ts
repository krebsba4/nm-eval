import { TestBed, async } from '@angular/core/testing';
import { CatsService } from '../services/cats.service';
import { MatListModule, MatInputModule } from '@angular/material';
import { CatListComponent } from '../cat-list/cat-list.component';
import { AppComponent } from './app.component';
import { ListFilterPipe } from '../pipes/list-filter-pipe';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppComponent, CatListComponent, ListFilterPipe ],
      imports: [MatListModule, MatInputModule, FormsModule, HttpClientTestingModule],
      providers: [CatsService],
    }).compileComponents();
  }));

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
