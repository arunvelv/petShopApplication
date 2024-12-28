import { Routes } from '@angular/router';
import { IndexComponent } from '../index/index.component';
import { UserLoginComponent } from '../AuthenticationModule/user-login/user-login.component';
import { UserRegisterComponent } from '../AuthenticationModule/user-register/user-register.component';
import { AdminLoginComponent } from '../AuthenticationModule/admin-login/admin-login.component';
import { PetsComponent } from '../UserModule/components/pets/pets.component';
import { PetFoodsComponent } from '../UserModule/components/pet-foods/pet-foods.component';
import { VaccinationsComponent } from '../UserModule/components/vaccinations/vaccinations.component';
import { GroomingServicesComponent } from '../UserModule/components/grooming-services/grooming-services.component';

export const routes: Routes = [
    {path: '', component: IndexComponent},
    {path: 'user-login', component: UserLoginComponent},
    {path: 'user-register', component: UserRegisterComponent},
    {path: 'admin-login', component: AdminLoginComponent},
    { path: 'pets', component: PetsComponent },
    { path: 'pet-foods', component: PetFoodsComponent },
    {path: 'services', component: GroomingServicesComponent}, 
    { path: 'vaccinations', component: VaccinationsComponent }
];
