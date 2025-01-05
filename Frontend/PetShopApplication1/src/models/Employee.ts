import { Address } from "./Address";
import { Pets } from "./Pets";

export interface Employee {
    employeeId: number;
    name: string;
    position: string;
    hireDate: Date;
    phoneNumber: string;
    email: string;
    address: Address;
    pets: Pets[];
  }
  