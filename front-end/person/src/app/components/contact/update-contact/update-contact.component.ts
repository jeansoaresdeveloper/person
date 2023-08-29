import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Contact } from '../contact/contact';
import { PersonService } from 'src/app/service/person/person.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-contact',
  templateUrl: './update-contact.component.html',
  styleUrls: ['./update-contact.component.css']
})
export class UpdateContactComponent implements OnInit {

    form!: FormGroup
    contact!: Contact

    constructor (
        private service: PersonService,
        private router: Router,
        private builder: FormBuilder,
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
        this.initializeForm();

        const id = this.route.snapshot.paramMap.get('id');
        this.service.getContactById(parseInt(id!)).subscribe((response: Contact) => {
            this.form.patchValue({
                name: response.name,
                email: response.email,
                phone: response.phone
            });
        })
    }

    initializeForm() {
        this.form = this.builder.group({
            name: ['', Validators.required],
            email: ['', Validators.compose([
                Validators.email,
                Validators.required
            ])],
            phone: ['', Validators.compose([
                Validators.pattern('^\\(\\d{2}\\)\\s?9?\\d{4}-\\d{4}$'),
                Validators.required
            ])],
        });
    }

    send() {
        if(!this.form.valid) { return; }
        const id = this.route.snapshot.paramMap.get('id');
        this.service.updateContact(parseInt(id!), this.form.value).subscribe(() => {
            this.router.navigate(['/list-people']);
        });
    } 

    cancel() {
        this.router.navigate(['/list-people'])
    }

    formIsValid():string {
        if(this.form.valid) {
            return 'submit';
        }
        return 'disable';
    }


}
