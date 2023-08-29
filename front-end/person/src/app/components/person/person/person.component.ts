import { Component, Input, OnInit } from '@angular/core';
import { Person } from './person';
import { Contact } from '../../contact/contact/contact';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

    contactVisibility: Boolean = false;
    imageEye: String = 'iconEyeClosed';

    @Input() person: Person = {
        id: 0,
        name: '',
        cpf: '',
        dateBirth: new Date,
        contacts: []
    }

    constructor() {}
    
    ngOnInit(): void {}

    changeContactVisibility(): void
    {
        this.contactVisibility = !this.contactVisibility;

        if (this.contactVisibility) {
            this.imageEye = 'iconEye';
            return;
        }

        this.imageEye = 'iconEyeClosed';

    }

    getNumberOfContacts(): number
    {
        return this.person.contacts.length;
    }


}
