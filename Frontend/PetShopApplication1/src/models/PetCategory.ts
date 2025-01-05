import { Pets } from "./Pets";

export interface PetCategory {
    categoryId: number;
    name: string;
    petsList: Pets[];
  }
  