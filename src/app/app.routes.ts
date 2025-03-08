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
  { path: 'blind-auctions', component: BlindAuctionComponent },
  { path: '**', redirectTo: '/home' }
];
