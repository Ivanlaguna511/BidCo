import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { HomeLoggedComponent } from './home-logged/home-logged.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProductComponent } from './product/product.component';
import { ProductLoggedComponent } from './product-logged/product-logged.component';
import { CreateAuctionComponent } from './create-auction/create-auction.component';
import { MyBidsComponent } from './my-bids/my-bids.component';
import { ProfileComponent } from './profile/profile.component';
import { ExpertCommentsComponent } from './expert-comments/expert-comments.component';

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
  { path: 'profile', component: ProfileComponent },
  { path: 'expert-comments', component: ExpertCommentsComponent },
  { path: '**', redirectTo: '/home' }
];
