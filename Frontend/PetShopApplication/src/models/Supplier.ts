import { Address } from "./Address";
import { Customer } from "./Customer";

export interface Supplier {
  supplierId: number;
  name: string;
  contactPerson: string;
  phoneNumber: string;
  email: string;
  address: Address;
}