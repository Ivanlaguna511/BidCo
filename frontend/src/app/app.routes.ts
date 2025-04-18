import { Routes } from '@angular/router';

import { RegisterComponent } from './views/register/register.component';
import { LoginComponent } from './views/login/login.component';
import { ProductComponent } from './views/product/product.component';
import { CreateAuctionComponent } from './views/create-auction/create-auction.component';
import { MyBidsComponent } from './views/my-bids/my-bids.component';
import { MyAuctionsComponent } from './views/my-auctions/my-auctions.component';
import { ProfileComponent } from './views/profile/profile.component';
import { BlindAuctionComponent } from './views/blind-auction/blind-auction.component';
import { ProfileDatosComponent } from './views/profile/profile-datos/profile-datos.component';
import { ProfilePrivacidadComponent } from './views/profile/profile-privacidad/profile-privacidad.component';
import { ProfileEstadisticasComponent } from './views/profile/profile-estadisticas/profile-estadisticas.component';
import { AuctionComponent } from './views/auction/auction.component';
import { RaffleComponent } from './views/raffle/raffle.component';

export const routes: Routes = [
  { path: '', redirectTo: '/auction', pathMatch: 'full' },
  { path: 'auction', component: AuctionComponent },
  { path: 'blind-auctions', component: BlindAuctionComponent },
  { path: 'raffle', component: RaffleComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'auction/product/:id', component: ProductComponent, data: { tipo: 'subasta' } },
  { path: 'blind-auctions/product/:id', component: ProductComponent, data: { tipo: 'ciega' } },
  { path: 'raffle/product/:id', component: ProductComponent, data: { tipo: 'sorteo' } },
  { path: 'create-auction', component: CreateAuctionComponent },
  { path: 'my-bids', component: MyBidsComponent },
  { path: 'my-auctions', component: MyAuctionsComponent},
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
  { path: '**', redirectTo: '/auction' }
];
