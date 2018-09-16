import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'listFilter'
})
export class ListFilterPipe implements PipeTransform {
    transform(items: any[], filter: string): any[] {
        if ( !items || !filter ) {
            return items;
        }
        return items.filter(item => {
            console.log(item);
            return item.id.includes(filter);
        });
    }
}
