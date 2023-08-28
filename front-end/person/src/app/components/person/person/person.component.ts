import { Component, Input, OnInit } from '@angular/core';
import { Person } from './person';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

    @Input() person: Person = {
        id: 0,
        name: '',
        cpf: '',
        dateBirth: new Date,
        contacts: []
    }

    constructor() {}
    
    ngOnInit(): void {}

    addContact() {
        console.log(this.person.id);
    }


}
