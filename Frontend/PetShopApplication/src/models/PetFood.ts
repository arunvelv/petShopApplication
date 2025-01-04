import { Pets } from "./Pets";

export interface PetFood {
imageURL: string;
    foodId: number;
    name: string;
    brand: string;
    type: string;
    quantity: number;
    price: number;
    pets: Pets[];
  }
  