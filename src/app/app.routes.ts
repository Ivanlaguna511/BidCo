import { Routes } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { HomeLoggedComponent } from './home-logged/home-logged.component';
import { RegisterComponent } from './views/register/register.component';
import { LoginComponent } from './views/login/login.component';
import { ProductComponent } from './views/product/product.component';
import { ProductLoggedComponent } from './product-logged/product-logged.component';
import { CreateAuctionComponent } from './views/create-auction/create-auction.component';
import { MyBidsComponent } from './views/my-bids/my-bids.component';
import { ProfileComponent } from './views/profile/profile.component';
import { ExpertCommentsComponent } from './components/expert-comments/expert-comments.component';
import { BlindAuctionComponent } from './views/blind-auction/blind-auction.component';
import { ProfileDatosComponent } from './views/profile/profile-datos/profile-datos.component';
import { ProfilePrivacidadComponent } from './views/profile/profile-privacidad/profile-privacidad.component';
import { ProfileEstadisticasComponent } from './views/profile/profile-estadisticas/profile-estadisticas.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'home-logged', component: HomeLoggedComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'product', component: ProductComponent },
  { path: 'product-logged', component: ProductLoggedComponent },
  { path: 'create-auction', component: CreateAuctionComponent },
  { path: 'my-bids', component: MyBidsComponent },
  {
    path: 'profile',
    component: ProfileComponent,
    children: [
      // Cuando entras a /profile, redirige a /profile/mis-datos
      { path: '', redirectTo: 'mis-datos', pathMatch: 'full' },

      // /profile/mis-datos -> carga ProfileDatosComponent
      { path: 'mis-datos', component: ProfileDatosComponent },

      // /profile/privacidad -> carga ProfilePrivacidadComponent
      { path: 'privacidad', component: ProfilePrivacidadComponent },

      // /profile/estadisticas -> carga ProfileEstadisticasComponent
      { path: 'estadisticas', component: ProfileEstadisticasComponent }
    ]
  },
  { path: 'expert-comments', component: ExpertCommentsComponent },
  { path: 'blind-auctions', component: BlindAuctionComponent },
  { path: '**', redirectTo: '/home' }
];
