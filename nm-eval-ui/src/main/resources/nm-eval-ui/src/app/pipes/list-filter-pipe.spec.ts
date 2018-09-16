import { ListFilterPipe } from '../pipes/list-filter-pipe';

describe('ListFilterPipe', () => {
    const catsJsonList = require('../testing/restCatResponse.json');
    let pipe: ListFilterPipe;

    beforeEach(() => {
        pipe = new ListFilterPipe();
    });

    it('should create an instance', () => {
        expect(pipe).toBeTruthy();
    });

    it('should filter 3 objects', () => {
        const catList = catsJsonList;
        expect(pipe.transform(catList, 'f3a33bff-7f24-497e-a60d-7b7fe693d62e')).toString().includes('f3a33bff-7f24-497e-a60d-7b7fe693d62e');
        expect(pipe.transform(catList, '9fbf0ce8-f939-4b65-ba3f-b9da61c8b58a')).toString().includes('9fbf0ce8-f939-4b65-ba3f-b9da61c8b58a');
        expect(pipe.transform(catList, '93')).toString().includes('9fbf0ce8-f939-4b65-ba3f-b9da61c8b58a' && 'f3a33bff-7f24-497e-a60d-7b7fe693d62e');
    });
});


