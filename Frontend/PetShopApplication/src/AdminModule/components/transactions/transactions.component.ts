import { Component, OnInit } from '@angular/core';
import { Transaction, TransactionStatus } from '../../../models/Transaction';
import { TransactionsService } from '../../services/transactions/transactions.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

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
  customerId: number | null = null;
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

  addTransaction(transaction: Transaction): void {
    if (!transaction.customer) {
      transaction.customer = { customerId: 0 } as any;  // Ensure customer is initialized
    }
    if (!transaction.pet) {
      transaction.pet = { petId: 0 } as any;  // Ensure pet is initialized
    }


    this.transactionsService.addTransaction(transaction).subscribe(
      (addedTransaction: Transaction) => {
        this.transactions.push(addedTransaction); // Add the new transaction to the list
        this.newTransaction = {
          transactionId: 0,
          transactionDate: '',
          amount: 0,
          transactionStatus: TransactionStatus.SUCCESS,
          customer: { customerId: 1} as any,
          pet: { petId: 1} as any
        };
        this.isAddTransactionFormVisible = false;
      },
      (error) => {
        console.error('Error adding transaction:', error);
        this.errorMessage = 'Failed to add transaction.';
      }
    );
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
    if (this.customerId != null) {
      this.transactionsService.getTransactionsByCustomerId(this.customerId).subscribe(
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
