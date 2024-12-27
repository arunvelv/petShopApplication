import { Routes } from '@angular/router';
import { IndexComponent } from '../index/index.component';
import { UserLoginComponent } from '../user-login/user-login.component';
import { UserRegisterComponent } from '../user-register/user-register.component';
import { AdminLoginComponent } from '../admin-login/admin-login.component';
import { PetsComponent } from '../UserModule/components/pets/pets.component';
import { PetFoodsComponent } from '../UserModule/components/pet-foods/pet-foods.component';
import { ServicesComponent } from '../UserModule/components/services/services.component';
import { VaccinationsComponent } from '../UserModule/components/vaccinations/vaccinations.component';

export const routes: Routes = [
    {path: '', component: IndexComponent},
    {path: 'user-login', component: UserLoginComponent},
    {path: 'user-register', component: UserRegisterComponent},
    {path: 'admin-login', component: AdminLoginComponent},
    { path: 'pets', component: PetsComponent },
    { path: 'pet-foods', component: PetFoodsComponent },
    { path: 'services', component: ServicesComponent },
    { path: 'vaccinations', component: VaccinationsComponent }
];
