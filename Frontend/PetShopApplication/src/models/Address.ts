import { Customer } from "./Customer";
import { Employee } from "./Employee";
import { Supplier } from "./Supplier";

export interface Address {
    addressId: number;
    street: string;
    city: string;
    state: string;
    zipCode: string;
    customer: Customer[],
    employee: Employee[],
    supplier: Supplier[]
  }
  