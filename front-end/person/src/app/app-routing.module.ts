import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePersonComponent } from './components/person/create-person/create-person.component';
import { ListPeopleComponent } from './components/person/list-people/list-people.component';
import { CreateContactComponent } from './components/contact/create-contact/create-contact.component';

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
    } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
