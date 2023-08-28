import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePersonComponent } from './components/person/create-person/create-person.component';
import { ListPeopleComponent } from './components/person/list-people/list-people.component';

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
    }    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
