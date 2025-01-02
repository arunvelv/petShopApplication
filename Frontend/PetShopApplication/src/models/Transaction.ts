import { Customer } from "./Customer";
import { Pets } from "./Pets";

export interface Transaction {
  transactionId: number;
  transactionDate: string;  // Date string
  amount: number;
  transactionStatus: string;
  customer: Customer;
  pet: Pets;
}
  
  export enum TransactionStatus {
    SUCCESS = 'SUCCESS',
    FAILED = 'FAILED',
  }

  