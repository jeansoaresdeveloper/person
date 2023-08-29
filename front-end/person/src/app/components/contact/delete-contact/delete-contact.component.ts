import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from 'src/app/service/person/person.service';

@Component({
  selector: 'app-delete-contact',
  templateUrl: './delete-contact.component.html',
  styleUrls: ['./delete-contact.component.css']
})
export class DeleteContactComponent {

    constructor(
        private service: PersonService,
        private router: Router,
        private route: ActivatedRoute
    ) {}

    ngOnInit(): void {}

    delete(): void
    {
        const id = this.route.snapshot.paramMap.get('id');
        this.service.deleteContact(parseInt(id!)).subscribe(() => {
            this.router.navigate(['/list-people'])
        });
    }

    cancel(): void
    {
        this.router.navigate(['/list-people'])
    }

}
