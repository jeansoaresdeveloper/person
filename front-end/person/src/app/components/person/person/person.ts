import { Contact } from "../../contact/contact/contact";

export interface Person {
    id?: number;
    name: String;
    cpf: String;
    dateBirth: Date;
    contacts: Array<Contact>
}