import { Contact } from "../../contact/contact/contact";

export interface PersonCreate {
    name: String;
    cpf: String;
    dateBirth: Date;
    contacts: Array<Contact>
}