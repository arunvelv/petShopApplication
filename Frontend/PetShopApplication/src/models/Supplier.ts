import { Address } from "./Address";
import { Pets } from "./Pets";

export interface Supplier {
    suppliersId: number;
    name: string;
    contactPerson: string;
    phoneNumber: string;
    email: string;
    address: Address;
    pets: Pets[];
  }
  