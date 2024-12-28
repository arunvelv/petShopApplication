import { Pets } from "./Pets";

export interface GroomingService {
    serviceId: number;
    name: string;
    description: string;
    price: number;
    available: boolean;
    pets: Pets[];
  }
  