import { Customer } from "./Customer";
import { Pets } from "./Pets";

export interface Transaction {
    transactionId: number;
    transactionDate: Date;
    amount: number;
    transactionStatus: TransactionStatus;
    customer: Customer;
    pet: Pets;
  }
  
  export enum TransactionStatus {
    SUCCESS = 'SUCCESS',
    FAILED = 'FAILED',
  }
  