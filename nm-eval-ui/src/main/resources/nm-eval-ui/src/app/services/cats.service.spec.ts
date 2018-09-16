import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CatsService } from './cats.service';
describe('CatsService', () => {

  // Loads in some 'mock' values that i pulled from the service and put into a json file.
  const response = require('../testing/restCatResponse.json');

  let httpMock: HttpTestingController;
  let catsService: CatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CatsService],
      imports: [HttpClientTestingModule]
    });

  catsService = TestBed.get(CatsService);
  httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    const service: CatsService = TestBed.get(CatsService);
    expect(service).toBeTruthy();
  });

  it('cats service returns valid cat objects', () => {
        expect(catsService).toBeTruthy();
        catsService.getCats().subscribe(catsData => {
          expect(catsData[0].id).toBeTruthy();
          expect(catsData[0].file).toBeTruthy();
        });
        const req = httpMock.expectOne(reqs => reqs.method === 'GET' && reqs.params.has('numberOfPictures'));
        req.flush(response);

        httpMock.verify();
  });
});
