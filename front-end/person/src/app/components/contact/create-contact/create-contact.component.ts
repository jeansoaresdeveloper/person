import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PersonService } from 'src/app/service/person/person.service';

@Component({
  selector: 'app-create-contact',
  templateUrl: './create-contact.component.html',
  styleUrls: ['./create-contact.component.css']
})
export class CreateContactComponent implements OnInit {
    
    form!: FormGroup

    constructor(
        private service: PersonService,
        private router: Router,
        private builder: FormBuilder,
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
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
        const id = this.route.snapshot.paramMap.get('id');
        this.service.createContact(parseInt(id!), this.form.value).subscribe(() => {
            this.router.navigate(['/list-people'])
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
