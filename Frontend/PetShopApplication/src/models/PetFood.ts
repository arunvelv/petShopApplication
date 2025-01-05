import { Pets } from "./Pets";

export interface PetFood {
  foodId: number;
  name: string;
  brand: string;
  type: string;
  quantity: number;
  price: number;
  imageURL: string;
  pets: Pets[];
  }
  