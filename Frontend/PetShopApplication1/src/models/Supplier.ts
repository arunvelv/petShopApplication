import { Address } from "./Address";

export interface Supplier {
  supplierId: number;
  name: string;
  contactPerson: string;
  phoneNumber: string;
  email: string;
  address: Address;
}