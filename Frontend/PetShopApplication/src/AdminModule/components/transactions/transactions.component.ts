import { Component, OnInit } from '@angular/core';
import { Transaction, TransactionStatus } from '../../../models/Transaction';
import { TransactionsService } from '../../services/transactions/transactions.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Customer } from '../../../models/Customer';
import { Pets } from '../../../models/Pets';

@Component({
  selector: 'app-transactions',
  imports: [CommonModule, FormsModule],
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  transactions: Transaction[] = [];
  newTransaction: Transaction = {} as Transaction;
  editingTransaction: Transaction | null = null;
customers: Customer = {} as Customer;
pets: Pets = {} as Pets;
  errorMessage: string | null = null;
  isAddTransactionFormVisible = false;

  constructor(private transactionsService: TransactionsService) {}

  ngOnInit(): void {
    this.getAllTransactions();
  }

  toggleAddTransactionForm(): void {
    this.isAddTransactionFormVisible = !this.isAddTransactionFormVisible;
  }

  getAllTransactions(): void {
    this.transactionsService.getAllTransactions().subscribe(
      (data: Transaction[]) => {
        this.transactions = data;
      },
      (error) => {
        console.error('Error fetching transactions:', error);
        this.errorMessage = 'Failed to load transactions.';
      }
    );
  }

  addTransaction(): void {
    console.log(this.newTransaction);
    if (!this.newTransaction.customer || !this.newTransaction.customer.customerId) {
      console.error('Customer ID is required.');
      this.errorMessage = 'Please provide a valid Customer ID.';
      return;
    }
  
    if (!this.newTransaction.pet || !this.newTransaction.pet.petId) {
      console.error('Pet ID is required.');
      this.errorMessage = 'Please provide a valid Pet ID.';
      return;
    }
  
    // Ensure the transaction date is formatted as yyyy-MM-dd
    this.newTransaction.transactionDate = new Date(this.newTransaction.transactionDate)
      .toISOString()
      .split('T')[0]; // Format the date
    
    this.transactionsService.addTransaction(this.newTransaction).subscribe(
      (addedTransaction: Transaction) => {
        console.log('Transaction added successfully:', addedTransaction);
        this.transactions.push(addedTransaction); // Update the table with the new transaction
        this.resetTransactionForm(); // Reset the form after submission
        this.isAddTransactionFormVisible = false; // Close the form
      },
      (error) => {
        console.error('Error adding transaction:', error);
        this.errorMessage = 'Failed to add transaction. Please try again.';
      }
    );
  }
  

  resetTransactionForm(): void {
    this.newTransaction = {
      transactionId: 0,
      transactionDate: new Date().toISOString().split('T')[0], // Default to today's date in yyyy-MM-dd
      amount: 0,
      transactionStatus: TransactionStatus.SUCCESS,
      customer: { customerId: 1 } as any,  // Ensure customer object is initialized
      pet: { petId: 1 } as any,            // Ensure pet object is initialized
    };
  }
  
  editTransaction(transaction: Transaction): void {
    this.editingTransaction = {
      ...transaction,
      customer: transaction.customer || { customerId: 0, name: '', address: '' },
      pet: transaction.pet || { petId: 0, petName: '' }
    };
  }

  

  getSuccessfulTransactions(): void {
    this.transactionsService.getSuccessfulTransactions().subscribe(
      (data: Transaction[]) => {
        this.transactions = data;
      },
      (error) => {
        console.error('Error fetching successful transactions:', error);
        this.errorMessage = 'Failed to load successful transactions.';
      }
    );
  }

  getFailedTransactions(): void {
    this.transactionsService.getFailedTransactions().subscribe(
      (data: Transaction[]) => {
        this.transactions = data;
      },
      (error) => {
        console.error('Error fetching failed transactions:', error);
        this.errorMessage = 'Failed to load failed transactions.';
      }
    );
  }

  getTransactionsByCustomerId(): void {
    if (this.customers != null) {
      this.transactionsService.getTransactionsByCustomerId(this.customers.customerId).subscribe(
        (data: Transaction[]) => {
          this.transactions = data;
        },
        (error) => {
          console.error('Error fetching transactions by customer ID:', error);
          this.errorMessage = 'Failed to load transactions for this customer.';
        }
      );
    }
  }
}
