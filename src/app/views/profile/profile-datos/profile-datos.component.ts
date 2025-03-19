import { Component } from '@angular/core';

import { DATA_USER } from '../../../datos_estaticos/user_estadisticas';

@Component({
  selector: 'app-profile-datos',
  standalone: true,
  imports: [],
  templateUrl: './profile-datos.component.html',
  styleUrl: './profile-datos.component.css'
})
export class ProfileDatosComponent {
    user = DATA_USER;
}
