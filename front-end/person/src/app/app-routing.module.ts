import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePersonComponent } from './components/person/create-person/create-person.component';
import { ListPeopleComponent } from './components/person/list-people/list-people.component';
import { CreateContactComponent } from './components/contact/create-contact/create-contact.component';
import { DeletePersonComponent } from './components/person/delete-person/delete-person.component';
import { UpdatePersonComponent } from './components/person/update-person/update-person.component';
import { DeleteContactComponent } from './components/contact/delete-contact/delete-contact.component';
import { UpdateContactComponent } from './components/contact/update-contact/update-contact.component';

const routes: Routes = [
    {
        path: '',
        redirectTo: 'list-people',
        pathMatch: 'full'
    },
    {
        path: 'create-person',
        component: CreatePersonComponent
    },
    {
        path: 'list-people',
        component: ListPeopleComponent
    },
    {
        path: 'person/create-contact/:id',
        component: CreateContactComponent
    },
    {
        path: 'person/delete-person/:id',
        component: DeletePersonComponent
    },
    {
        path: 'person/update-person/:id',
        component: UpdatePersonComponent
    },
    {
        path: 'person/delete-contact/:id',
        component: DeleteContactComponent
    },
    {
        path: 'person/update-contact/:id',
        component: UpdateContactComponent
    } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
