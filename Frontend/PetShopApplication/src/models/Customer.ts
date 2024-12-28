import { Address } from "./Address";
import { Transaction } from "./Transaction";

export interface Customer {
    customerId: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    address: Address;
    transactions: Transaction[];
  }
  