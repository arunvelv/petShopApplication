import { Pets } from "./Pets";

export interface Vaccination {
    vaccinationId: number;
    name: string;
    description: string;
    price: number;
    available: boolean;
    pets: Pets[];
  }
  