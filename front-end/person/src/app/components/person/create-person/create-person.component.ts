import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PersonService } from 'src/app/service/person/person.service';

@Component({
  selector: 'app-create-person',
  templateUrl: './create-person.component.html',
  styleUrls: ['./create-person.component.css']
})
export class CreatePersonComponent implements OnInit {

    form!: FormGroup

    constructor (
        private service: PersonService,
        private router: Router,
        private builder: FormBuilder
    ) {}

    ngOnInit(): void {
        this.form = this.builder.group({
            name: ['', Validators.required],
            dateBirth: [new Date, Validators.required],
            cpf: ['', Validators.pattern('^\\d{3}.\\d{3}.\\d{3}-\\d{2}$')],
            nameContact: ['', Validators.required],
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
        this.service.create(this.form.value).subscribe(() => {
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
