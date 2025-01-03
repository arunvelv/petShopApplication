import { Employee } from "./Employee";
import { GroomingService } from "./Grooming-Services";
import { PetCategory } from "./PetCategory";
import { PetFood } from "./PetFood";
import { Supplier } from "./Supplier";
import { Vaccination } from "./Vaccination";

export interface Pets {
    petId: number;
    name: string;
    breed: string;
    age: number;
    price: number;
    description: string;
    imageUrl: string;
    category: PetCategory;
    // groomingServices: GroomingService[];
    // suppliers: Supplier[];
    // employees: Employee[];
    // petFood: PetFood[];
    // vaccinations: Vaccination[];
  }

  