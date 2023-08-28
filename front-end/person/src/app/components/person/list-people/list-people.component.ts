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

    constructor(private service: PersonService) {}

    ngOnInit(): void {
        this.service.listPeople().subscribe((response: any) => {    
            this.listPeople = response.content;
        });
    }

}
