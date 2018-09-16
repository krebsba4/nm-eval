import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CatsService } from '../services/cats.service';
import { MatListModule, MatInputModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CatListComponent } from './cat-list.component';
import { ListFilterPipe } from '../pipes/list-filter-pipe';
import { FormsModule } from '@angular/forms';
import { DebugElement } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

describe('CatListComponent', () => {
  let component: CatListComponent;
  let fixture: ComponentFixture<CatListComponent>;

  let debugElement: DebugElement;
  let catsSpy;
  let catsServiceMock: CatsService;
  const response = require('../testing/restCatResponseMany.json');

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatListComponent, ListFilterPipe ],
      imports: [MatListModule, MatInputModule, BrowserAnimationsModule, FormsModule, HttpClientTestingModule],
      providers: [CatsService],
    })
    .compileComponents();

    fixture = TestBed.createComponent(CatListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    debugElement = fixture.debugElement;
    catsServiceMock = debugElement.injector.get(CatsService);
    catsSpy = spyOn(catsServiceMock, 'getCats').and.returnValue(of(response));
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create and sort mocked ws data', () => {
    expect(component).toBeTruthy();
    component.ngOnInit();
    expect(catsSpy).toHaveBeenCalled();
    expect(component.cats.length === 25).toBeTruthy();
    expect(component.cats[0].id === '0238f700-64ab-417f-99ed-6f9ed1015625').toBeTruthy();
  });

  it('make sure keying in search populates', () => {
    expect(component).toBeTruthy();
    component.ngOnInit();
    expect(catsSpy).toHaveBeenCalled();

    component.searchInput = '';
    component.onKey('asdfasdf');
    expect(component.searchInput === 'asdfasdf').toBeTruthy();
  });
});
