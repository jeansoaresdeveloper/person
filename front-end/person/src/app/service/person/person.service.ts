import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ContactCreate } from 'src/app/components/contact/contact/contactCreate';
import { Person } from 'src/app/components/person/person/person';
import { PersonCreate } from 'src/app/components/person/person/personCreate';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

    private readonly API = `http://localhost:8080/person`;

  constructor(private httpClient: HttpClient) { }

    listPeople(): any
    {
        return this.httpClient.get(this.API);
    }

    create(form: any): Observable<Person>
    {
        const contact: ContactCreate = {
            name: form.nameContact,
            phone: form.phone,
            email: form.email
        }

        const person: PersonCreate = {
            name: form.name,
            dateBirth: form.dateBirth,
            cpf: form.cpf,
            contacts: [contact]
        }
        return this.httpClient.post<Person>(this.API, person);
    }

    createContact(id: number, contact: ContactCreate): Observable<Person>
    {
        const url = `${this.API}/${id}/contact`;
        return this.httpClient.post<Person>(url, contact);
    }

}
