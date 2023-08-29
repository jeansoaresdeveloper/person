import { Component } from '@angular/core';
import { PersonService } from 'src/app/service/person/person.service';
import { Person } from '../person/person';

@Component({
  selector: 'app-list-people',
  templateUrl: './list-people.component.html',
  styleUrls: ['./list-people.component.css']
})
export class ListPeopleComponent {

    listPeople: Person[] = [];
    filter: String = '';
    currentPage: number = 0;
    pages!: number;

    constructor(private service: PersonService) {}

    ngOnInit(): void {
        this.listPeopleArray();     
    }

    searchPerson()
    {
        if (this.filter.length < 3) {
            this.listPeopleArray();
            return;
        }

        this.currentPage = 0;

        this.service.filterByName(this.filter, this.currentPage).subscribe((response: any) => {
            this.listPeople = response.content;
            this.currentPage = response.number;
            this.pages = response.totalPages;
        });
    }

    listPeopleArray(): void
    {
        this.service.listPeople(this.currentPage).subscribe((response: any) => {    
            this.listPeople = response.content;            
            this.currentPage = response.number;
            this.pages = response.totalPages;
        });
    }

    previousPage() {
        this.currentPage--;
        if (this.filter.length < 3) {
            this.listPeopleArray();
            return;
        }

        this.searchPerson();
    }

    nextPage() {
        this.currentPage++;
        if (this.filter.length < 3) {
            this.listPeopleArray();
            return;
        }

        this.searchPerson();
    }

    firstPage(): string
    {
        if (this.currentPage == 0) {
            return 'disable'
        }

        return 'active'

    }

    lastPage(): string
    {        
        if (this.currentPage + 1 == this.pages) {
            return 'disable'
        }

        return 'active'

    }

}
