import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ListPeopleComponent } from './components/person/list-people/list-people.component';
import { CreatePersonComponent } from './components/person/create-person/create-person.component';
import { PersonComponent } from './components/person/person/person.component';
import { ContactComponent } from './components/contact/contact/contact.component';
import { RouterModule } from '@angular/router';
import { CreateContactComponent } from './components/contact/create-contact/create-contact.component';
import { DeletePersonComponent } from './components/person/delete-person/delete-person.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ListPeopleComponent,
    CreatePersonComponent,
    PersonComponent,
    ContactComponent,
    CreateContactComponent,
    DeletePersonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
