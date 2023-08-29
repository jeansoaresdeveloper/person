import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from 'src/app/service/person/person.service';
import { Person } from '../person/person';

@Component({
  selector: 'app-update-person',
  templateUrl: './update-person.component.html',
  styleUrls: ['./update-person.component.css']
})
export class UpdatePersonComponent {

    form!: FormGroup
    person!: Person

    constructor (
        private service: PersonService,
        private router: Router,
        private builder: FormBuilder,
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {
        this.initializeForm();

        const id = this.route.snapshot.paramMap.get('id');
        this.service.getById(parseInt(id!)).subscribe((response: Person) => {
            this.form.patchValue({
                name: response.name,
                dateBirth: response.dateBirth,
                cpf: response.cpf
            });
        })
    }

    initializeForm() {
        this.form = this.builder.group({
            name: ['', Validators.required],
            dateBirth: ['', Validators.required],
            cpf: ['', Validators.pattern('^\\d{3}.\\d{3}.\\d{3}-\\d{2}$')]
        });
    }

    send() {
        if(!this.form.valid) { return; }
        const id = this.route.snapshot.paramMap.get('id');
        this.service.update(parseInt(id!), this.form.value).subscribe(() => {
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
