import { Routes } from '@angular/router';
import { IndexComponent } from '../index/index.component';
import { UserLoginComponent } from '../AuthenticationModule/user-login/user-login.component';
import { UserRegisterComponent } from '../AuthenticationModule/user-register/user-register.component';
import { AdminLoginComponent } from '../AuthenticationModule/admin-login/admin-login.component';
import { PetsComponent } from '../UserModule/components/pets/pets.component';
import { PetFoodsComponent } from '../UserModule/components/pet-foods/pet-foods.component';
import { VaccinationsComponent } from '../UserModule/components/vaccinations/vaccinations.component';
import { GroomingServicesComponent } from '../UserModule/components/grooming-services/grooming-services.component';
import { AdminDashboardComponent } from '../AdminModule/components/admin-dashboard/admin-dashboard.component';
import { CustomersComponent } from '../AdminModule/components/customers/customers.component';
import { EmployeesComponent } from '../AdminModule/components/employees/employees.component';
import { AddressComponent } from '../AdminModule/components/address/address.component';
import { TransactionsComponent } from '../AdminModule/components/transactions/transactions.component';
import { SuppliersComponent } from '../AdminModule/components/suppliers/suppliers.component';
import { AdminPetsComponent } from '../AdminModule/components/admin-pets/admin-pets.component';
import { AdminPetFoodsComponent } from '../AdminModule/components/admin-pet-foods/admin-pet-foods.component';
import { AdminGroomingServicesComponent } from '../AdminModule/components/admin-grooming-services/admin-grooming-services.component';
import { AdminVaccinationsComponent } from '../AdminModule/components/admin-vaccinations/admin-vaccinations.component';

export const routes: Routes = [
    {path: '', component: IndexComponent},
    {path: 'user-login', component: UserLoginComponent},
    {path: 'user-register', component: UserRegisterComponent},
    {path: 'admin-login', component: AdminLoginComponent},


    {path: 'pets', component: PetsComponent },
    {path: 'pet-foods', component: PetFoodsComponent },
    {path: 'services', component: GroomingServicesComponent}, 
    {path: 'vaccinations', component: VaccinationsComponent },

    {path: 'admin-dashboard', component: AdminDashboardComponent},
    {path: 'customers', component: CustomersComponent},
    {path: 'employees', component: EmployeesComponent},
    {path: 'address', component: AddressComponent},
    {path: 'transactions', component: TransactionsComponent},
    {path: 'suppliers', component: SuppliersComponent},
    {path: 'admin-pets', component: AdminPetsComponent},
    {path: 'admin-pet-foods', component: AdminPetFoodsComponent},
    {path: 'admin-grooming-services', component: AdminGroomingServicesComponent},
    {path: 'admin-vaccinations' , component: AdminVaccinationsComponent}

];
