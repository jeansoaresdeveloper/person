import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Contact } from 'src/app/components/contact/contact/contact';
import { ContactCreate } from 'src/app/components/contact/contact/contactCreate';
import { Person } from 'src/app/components/person/person/person';
import { PersonCreate } from 'src/app/components/person/person/personCreate';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

    private readonly API = `http://localhost:8080/person`;
    private readonly API_CONTACT = `http://localhost:8080/contact`

  constructor(private httpClient: HttpClient) { }

    listPeople(page: number): any
    {
        return this.httpClient.get(`${this.API}?page=${page}`);
    }

    filterByName(name: String, page: number): Observable<Person>
    {
        return this.httpClient.get<Person>(`${this.API}?name=${name}&page=${page}`);
    }

    getById(id: number): any
    {
        return this.httpClient.get(`${this.API}/${id}`);
    }

    getContactById(id: number): any
    {
        return this.httpClient.get(`${this.API_CONTACT}/${id}`);
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

    update(id: number, person: Person): Observable<Person>
    {
        const url = `${this.API}/${id}`;
        return this.httpClient.put<Person>(url, person);
    }

    updateContact(id: number, contact: Contact): Observable<Contact>
    {
        const url = `${this.API_CONTACT}/${id}`
        return this.httpClient.put<Contact>(url, contact);
    }

    delete(id: number)
    {
        const url = `${this.API}/${id}`;
        return this.httpClient.delete<Person>(url);
    }

    deleteContact(id: number): Observable<Contact>
    {
        return this.httpClient.delete<Contact>(`${this.API_CONTACT}/${id}`);
    }

}
